package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createIntBuffer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.LinkedHashMap;

class DirectIntIndexer<ARRAY_OBJECT extends ArrayObject> extends FastIndexer<ARRAY_OBJECT, Integer> {

	private final IntBuffer idxBuf;
	
	private int numObjs;
	
	DirectIntIndexer(int maxNumObjs)
	{
		super(maxNumObjs);
		idxBuf = createIntBuffer(maxNumObjs);
	}

	@Override
	public Class<?> getIndexType()
	{
		return int.class;
	}

	@Override
	public boolean index(ARRAY_OBJECT obj)
	{
		Integer idx = objs.get(obj);
		if (idx == null)
			return false;
		numIndices++;
		idxBuf.put(idx);
		return true;
	}

	@Override
	public void add(ARRAY_OBJECT obj)
	{
		idxBuf.put(numObjs);
		objs.put(obj, numObjs);
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
		idxBuf.flip();
		return idxBuf;
	}

	@Override
	public void clear()
	{
		numObjs = 0;
		numIndices = 0;
		idxBuf.clear();
		objs = new LinkedHashMap<>(maxNumIndices, 1.0f);
	}
}
