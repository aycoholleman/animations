package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;

class DirectByteIndexer<ARRAY_OBJECT extends ArrayObject> extends FastIndexer<ARRAY_OBJECT, Byte> {

	private final ByteBuffer idxBuf;
	private byte numObjs;
	
	DirectByteIndexer(int maxNumObjs)
	{
		super(maxNumObjs);
		idxBuf = createByteBuffer(maxNumObjs);
	}

	@Override
	public Class<?> getIndexType()
	{
		return byte.class;
	}

	@Override
	public boolean index(ARRAY_OBJECT object)
	{
		Byte idx = objs.get(object);
		if (idx == null)
			return false;
		idxBuf.put(idx);
		return true;
	}

	@Override
	public void add(ARRAY_OBJECT newObject)
	{
		idxBuf.put(numObjs);
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