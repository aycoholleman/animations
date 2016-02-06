package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;

class DirectByteIndexer<ARRAY_OBJECT extends Vertex> extends FastIndexer<ARRAY_OBJECT, Byte> {

	private final ByteBuffer idxBuf;
	private byte numObjs;
	
	DirectByteIndexer(int maxNumObjs)
	{
		super(maxNumObjs);
		idxBuf = createByteBuffer(maxNumObjs);
	}

	@Override
	public IndexType getIndexType()
	{
		return IndexType.BYTE;
	}

	@Override
	public boolean index(ARRAY_OBJECT object)
	{
		Byte idx = vertices.get(object);
		if (idx == null)
			return false;
		idxBuf.put(idx);
		return true;
	}

	@Override
	public void add(ARRAY_OBJECT newObject)
	{
		idxBuf.put(numObjs);
		vertices.put(newObject, numObjs);
		numIndices++;
		numObjs++;
	}

	@Override
	public int numVertices()
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
		vertices = new LinkedHashMap<>(maxNumVertices, 1.0f);
	}
}
