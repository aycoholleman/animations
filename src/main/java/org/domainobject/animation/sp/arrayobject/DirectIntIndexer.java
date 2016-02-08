package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createIntBuffer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.LinkedHashMap;

class DirectIntIndexer<VERTEX extends Vertex> extends FastIndexer<VERTEX, Integer> {

	private final IntBuffer idxBuf;
	
	DirectIntIndexer(int maxNumVertices)
	{
		super(maxNumVertices);
		idxBuf = createIntBuffer(maxNumVertices);
	}

	@Override
	public IndexType getIndexType()
	{
		return IndexType.INT;
	}

	@Override
	public boolean index(VERTEX vertex)
	{
		Integer index = vertices.get(vertex);
		if (index == null)
			return false;
		numIndices++;
		idxBuf.put(index);
		return true;
	}

	@Override
	public void add(VERTEX vertex)
	{
		int index = vertices.size();
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
