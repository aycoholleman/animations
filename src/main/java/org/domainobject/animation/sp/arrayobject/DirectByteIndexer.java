package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;

class DirectByteIndexer<VERTEX extends Vertex> extends FastIndexer<VERTEX, Byte> {

	private final ByteBuffer idxBuf;
	
	DirectByteIndexer(int maxNumVertices)
	{
		super(maxNumVertices);
		idxBuf = createByteBuffer(maxNumVertices);
	}

	@Override
	public IndexType getIndexType()
	{
		return IndexType.BYTE;
	}

	@Override
	public boolean index(VERTEX vertex)
	{
		Byte index = vertices.get(vertex);
		if (index == null)
			return false;
		idxBuf.put(index);
		return true;
	}

	@Override
	public void add(VERTEX vertex)
	{
		byte index = (byte)vertices.size();
		idxBuf.put(index);
		vertices.put(vertex, index);
		numIndices++;
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
		numIndices = 0;
		idxBuf.clear();
		vertices = new LinkedHashMap<>(maxNumVertices, 1.0f);
	}
}
