package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;

class IntIndexer<T> implements _Indexer<T> {

	/*
	 * The indices of the array objects. Will get burnt to the
	 * GL_ELEMENT_ARRAY_BUFFER.
	 */
	private final int[] indices;
	private final int maxNumObjs;

	/*
	 * A reverse lookup table that allows us to quickly find the index of an
	 * array object in the indices array. When an array object is added or
	 * committed to memory, its uniqueness is first checked through a lookup on
	 * this map. If it is not unique, it is discarded and only the index of the
	 * array object of which it is a duplicate is appended to the indices array.
	 */
	private LinkedHashMap<T, Integer> objs;

	private int numObjs;

	public IntIndexer(int maxNumObjs)
	{
		this.maxNumObjs = maxNumObjs;
		indices = new int[maxNumObjs];
		objs = new LinkedHashMap<>(maxNumObjs, 1.0f);
	}

	@Override
	public Class<?> getIndexType()
	{
		return int.class;
	}

	@Override
	public boolean index(T object)
	{
		Integer idx = objs.get(object);
		if (idx == null)
			return false;
		indices[numObjs++] = idx;
		return true;
	}

	@Override
	public void add(T newObject)
	{
		indices[numObjs] = numObjs;
		objs.put(newObject, numObjs);
		numObjs++;
	}

	@Override
	public int countObjects()
	{
		return numObjs;
	}

	@Override
	public void write(ByteBuffer idxBuf)
	{
		idxBuf.asIntBuffer().put(indices, 0, numObjs);
	}

	@Override
	public Iterator<T> iterator()
	{
		return objs.keySet().iterator();
	}

	@Override
	public boolean contains(T object)
	{
		return objs.containsKey(object);
	}

	@Override
	public void clear()
	{
		numObjs = 0;
		objs = new LinkedHashMap<>(maxNumObjs, 1.0f);
	}


}
