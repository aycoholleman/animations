package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createIntBuffer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.LinkedHashMap;

class DirectIntIndexer<VERTEX extends Vertex> extends FastIndexer<VERTEX, Integer> {

	private final IntBuffer idxBuf;
	
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
	public boolean index(VERTEX v)
	{
		Integer idx = vertices.get(v);
		if (idx == null)
			return false;
		numIndices++;
		idxBuf.put(idx);
		return true;
	}

	@Override
	public void add(VERTEX obj)
	{
		idxBuf.put(vertices.size());
		vertices.put(obj, vertices.size());
		numIndices++;
	}


	@Override
	public int numIndices()
	{
		return numIndices;
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
		vertices = new LinkedHashMap<>(maxNumIndices, 1.0f);
	}
}
