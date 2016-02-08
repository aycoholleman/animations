package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createShortBuffer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import java.util.LinkedHashMap;

class DirectShortIndexer<VERTEX extends Vertex> extends FastIndexer<VERTEX, Short> {

	private final ShortBuffer idxBuf;

	DirectShortIndexer(int maxNumVertices)
	{
		super(maxNumVertices);
		idxBuf = createShortBuffer(maxNumVertices);
	}

	@Override
	public IndexType getIndexType()
	{
		return IndexType.SHORT;
	}

	@Override
	public boolean index(VERTEX vertex)
	{
		Short index = vertices.get(vertex);
		if (index == null)
			return false;
		idxBuf.put(index);
		return true;
	}

	@Override
	public void add(VERTEX vertex)
	{
		short index = (short)vertices.size();
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
