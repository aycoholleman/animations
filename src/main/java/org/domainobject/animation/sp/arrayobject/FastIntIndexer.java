package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createIntBuffer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.LinkedHashMap;

class FastIntIndexer<ARRAY_OBJECT extends Vertex> extends FastIndexer<ARRAY_OBJECT, Integer> {

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
	public IndexType getIndexType()
	{
		return IndexType.INT;
	}

	@Override
	public boolean index(ARRAY_OBJECT obj)
	{
		Integer idx = vertices.get(obj);
		if (idx == null)
			return false;
		indices[numIndices++] = idx;
		return true;
	}

	@Override
	public void add(ARRAY_OBJECT obj)
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
