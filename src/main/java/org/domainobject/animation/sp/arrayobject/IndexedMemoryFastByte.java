package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author Ayco Holleman
 *
 */
abstract class IndexedMemoryFastByte<T extends ArrayObject> implements _IndexedMemoryFast<T> {

	private final LinkedHashMap<T, Byte> objs;
	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final _Constructor<T> constructor;

	private final byte[] indices;
	private final ByteBuffer idxBuf;

	private int numObjs;
	private int numElems;

	private T pending;

	IndexedMemoryFastByte(int maxNumObjects, int objSize)
	{
		this.constructor = getConstructor();
		this.objSize = objSize;
		objs = new LinkedHashMap<>(maxNumObjects, 1.0f);
		raw = new float[maxNumObjects * objSize];
		indices = new byte[maxNumObjects];
		objBuf = createFloatBuffer(raw.length);
		idxBuf = createByteBuffer(maxNumObjects * Byte.BYTES);
	}


	@Override
	public int size()
	{
		return numObjs;
	}


	abstract _Constructor<T> getConstructor();


	@Override
	public void add(T arrayObject)
	{
		Byte idx = objs.get(arrayObject);
		if (idx == null) {
			T copy = constructor.make(raw, numElems);
			arrayObject.copyTo(copy);
			objs.put(copy, (indices[numObjs] = (byte) numObjs));
			numElems += objSize;
		}
		else {
			indices[numObjs] = idx;
		}
		numObjs++;
	}

	@Override
	public void addUnique(T arrayObject)
	{
		T copy = constructor.make(raw, numElems);
		arrayObject.copyTo(copy);
		objs.put(copy, (indices[numObjs] = (byte) numObjs));
		numElems += objSize;
		numObjs++;
	}

	@Override
	public T make()
	{
		pending = constructor.make(raw, numElems);
		pending.commitable = new _Commitable() {
			public void commit(ArrayObject caller)
			{
				if (caller != pending)
					MemoryException.commitWindowClosed();
				Byte idx = objs.get(pending);
				if (idx == null) {
					objs.put(pending, (indices[numObjs] = (byte) numObjs));
					numElems += objSize;
				}
				else {
					indices[numObjs] = idx;
				}
				numObjs++;
			}
		};
		return pending;
	}

	@Override
	public Class<?> getIndexType()
	{
		return byte.class;
	}

	@Override
	public boolean contains(T arrayObject)
	{
		return objs.keySet().contains(arrayObject);
	}

	@Override
	public ShaderInput burn()
	{
		if (numObjs == 0)
			MemoryException.cannotBurnWhenEmpty();
		objBuf.clear();
		objBuf.put(raw, 0, numElems);
		objBuf.flip();
		idxBuf.clear();
		idxBuf.put(indices, 0, numObjs);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);
	}

	@Override
	public void clear()
	{
		numObjs = 0;
		numElems = 0;
		objs.clear();
	}

	public Iterator<T> iterator()
	{
		return objs.keySet().iterator();
	}
}
