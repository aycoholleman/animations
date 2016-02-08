package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createIntBuffer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.LinkedHashMap;

class FastIntIndexer<VERTEX extends Vertex> extends FastIndexer<VERTEX, Integer> {

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
	public IndexType getIndexType()
	{
		return IndexType.INT;
	}

	@Override
	public boolean index(VERTEX v)
	{
		Integer idx = vertices.get(v);
		if (idx == null)
			return false;
		indices[numIndices++] = idx;
		return true;
	}

	@Override
	public void add(VERTEX obj)
	{
		indices[numIndices] = numObjs;
		vertices.put(obj, numObjs);
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
