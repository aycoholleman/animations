package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;

class FastByteIndexer<ARRAY_OBJECT extends Vertex> extends FastIndexer<ARRAY_OBJECT, Byte> {

	/*
	 * The indices of the array objects. Will get burnt to the
	 * GL_ELEMENT_ARRAY_BUFFER.
	 */
	private final byte[] indices;
	private final ByteBuffer idxBuf;
	private byte numObjs;
	
	FastByteIndexer(int maxNumObjs)
	{
		super(maxNumObjs);
		indices = new byte[maxNumObjs];
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
		indices[numIndices++] = idx;
		return true;
	}

	@Override
	public void add(ARRAY_OBJECT newObject)
	{
		indices[numIndices] = numObjs;
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
		vertices = new LinkedHashMap<>(maxNumVertices, 1.0f);
	}
}
