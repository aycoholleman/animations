package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createIntBuffer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.LinkedHashMap;

class FastIntIndexer<ARRAY_OBJECT extends ArrayObject> extends FastIndexer<ARRAY_OBJECT, Integer> {

	/*
	 * The indices of the array objects. Will get burnt to the
	 * GL_ELEMENT_ARRAY_BUFFER.
	 */
	private final int[] indices;
	private final IntBuffer idxBuf;
	
	private int numObjs;
	
	FastIntIndexer(int maxNumObjs)
	{
		super(maxNumObjs);
		indices = new int[maxNumObjs];
		idxBuf = createIntBuffer(maxNumObjs);
	}

	@Override
	public Class<?> getIndexType()
	{
		return int.class;
	}

	@Override
	public boolean index(ARRAY_OBJECT object)
	{
		Integer idx = objs.get(object);
		if (idx == null)
			return false;
		indices[numIndices++] = idx;
		return true;
	}

	@Override
	public void add(ARRAY_OBJECT newObject)
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
	public Buffer burnIndices()
	{
		idxBuf.clear();
		idxBuf.put(indices, 0, numIndices);
		idxBuf.flip();
		return idxBuf;
	}

	@Override
	public void clear()
	{
		numObjs = 0;
		numIndices = 0;
		objs = new LinkedHashMap<>(maxNumIndices, 1.0f);
	}
}
