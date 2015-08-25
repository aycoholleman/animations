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

	private final _Constructor<T> constr;

	private final short[] indices;
	private final ByteBuffer idxBuf;

	private int numObjs;
	private int numElems;

	private T[] pending;

	IndexedMemoryFastShort(int maxNumObjects, int objSize)
	{
		this.constr = getConstructor();
		this.objSize = objSize;
		objs = new LinkedHashMap<>(maxNumObjects, 1.0f);
		raw = new float[maxNumObjects * objSize];
		indices = new short[maxNumObjects];
		objBuf = createFloatBuffer(raw.length);
		idxBuf = createByteBuffer(maxNumObjects * Short.BYTES);
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
		Short idx = objs.get(arrayObject);
		if (idx == null) {
			T copy = constr.make(raw, numElems);
			arrayObject.copyTo(copy);
			objs.put(copy, (indices[numObjs] = (short) numObjs));
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
		T copy = constr.make(raw, numElems);
		arrayObject.copyTo(copy);
		objs.put(copy, (indices[numObjs] = (short) numObjs));
		numElems += objSize;
		numObjs++;
	}

	@Override
	public T make()
	{
		return make(1)[0];
	}

	private class Commitable implements _Commitable<T> {
		public void commit(T caller)
		{
			if (pending == null)
				MemoryException.commitWindowClosed();
			for (int i = 0; i < pending.length; i++) {
				if (pending[i] == caller) {
					pending[i] = null;
					index(caller);
					break;
				}
			}
			MemoryException.alreadyCommitted();
		}
	}

	public T[] make(int howmany)
	{
		pending = constr.array(howmany);
		for (int i = 0; i < howmany; i++) {
			pending[i] = constr.make(raw, numElems + (i * objSize));
			pending[i].commitable = new Commitable();
		}
		return pending;
	}

	public void commit()
	{
		T[] tmp = pending;
		pending = null;
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i] != null) {
				index(tmp[i]);
			}
		}
	}

	@Override
	public Class<?> getIndexType()
	{
		return short.class;
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

	@Override
	public Iterator<T> iterator()
	{
		return objs.keySet().iterator();
	}


	private void index(T obj)
	{
		Short idx = objs.get(obj);
		if (idx == null) {
			objs.put(obj, (indices[numObjs] = (short) numObjs));
			numElems += objSize;
		}
		else {
			indices[numObjs] = idx;
		}
		numObjs++;
	}
}
