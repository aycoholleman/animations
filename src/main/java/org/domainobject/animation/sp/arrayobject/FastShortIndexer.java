package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createShortBuffer;

import java.nio.ShortBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;

class FastShortIndexer<T extends ArrayObject> implements _FastIndexer<T> {

	/*
	 * The indices of the array objects. Will get burnt to the
	 * GL_ELEMENT_ARRAY_BUFFER.
	 */
	private final short[] indices;
	private final ShortBuffer idxBuf;
	private final int maxNumObjs;

	/*
	 * A reverse lookup table that allows us to quickly find the index of an
	 * array object in the indices array. When an array object is added or
	 * committed to memory, its uniqueness is first checked through a lookup on
	 * this map. If it is not unique, it is discarded and only the index of the
	 * array object of which it is a duplicate is appended to the indices array.
	 */
	private LinkedHashMap<T, Short> objs;

	private short numObjs;
	private int numIndices;

	public FastShortIndexer(int maxNumObjs)
	{
		this.maxNumObjs = maxNumObjs;
		indices = new short[maxNumObjs];
		idxBuf = createShortBuffer(indices.length);
		objs = new LinkedHashMap<>(maxNumObjs, 1.0f);
	}

	@Override
	public Class<?> getIndexType()
	{
		return short.class;
	}

	@Override
	public boolean index(T object)
	{
		Short idx = objs.get(object);
		if (idx == null)
			return false;
		indices[numIndices++] = idx;
		return true;
	}

	@Override
	public void add(T newObject)
	{
		indices[numIndices] = numObjs;
		objs.put(newObject, numObjs);
		numIndices++;
		numObjs++;
	}

	@Override
	public int numObjs()
	{
		return numObjs;
	}

	@Override
	public int numIndices()
	{
		return numIndices;
	}

	@Override
	public ShortBuffer burnIndices()
	{
		idxBuf.clear();
		idxBuf.put(indices, 0, numIndices);
		idxBuf.flip();
		return idxBuf;
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
		numIndices = 0;
		objs = new LinkedHashMap<>(maxNumObjs, 1.0f);
	}

}
