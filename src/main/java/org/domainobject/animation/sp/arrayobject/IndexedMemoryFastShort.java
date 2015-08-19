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
abstract class IndexedMemoryFastShort<T extends ArrayObject> implements _IndexedMemoryFast<T> {

	private final LinkedHashMap<T, Short> objs;
	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final short[] indices;
	private final ByteBuffer idxBuf;

	private int numObjs;
	private int numElems;

	IndexedMemoryFastShort(int maxNumObjects, int objSize)
	{
		this.objSize = objSize;
		objs = new LinkedHashMap<>(maxNumObjects, 1.0f);
		raw = new float[maxNumObjects * objSize];
		indices = new short[maxNumObjects];
		objBuf = createFloatBuffer(raw.length);
		idxBuf = createByteBuffer(maxNumObjects * Short.BYTES);
	}

	abstract T construct(float[] raw, int offset);

	@Override
	public Class<?> getIndexType()
	{
		return short.class;
	}

	@Override
	public void add(T arrayObject)
	{
		Short index = objs.get(arrayObject);
		if (index == null) {
			T copy = construct(raw, numElems);
			arrayObject.copyTo(copy);
			objs.put(copy, (indices[numObjs] = (short) numObjs));
			numElems += objSize;
		}
		else {
			indices[numObjs] = index;
		}
		numObjs++;
	}

	@Override
	public void unique(T arrayObject)
	{
		T copy = construct(raw, numElems);
		arrayObject.copyTo(copy);
		objs.put(copy, (indices[numObjs] = (short) numObjs));
		numElems += objSize;
		numObjs++;
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
		idxBuf.asShortBuffer().put(indices, 0, numObjs);
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
