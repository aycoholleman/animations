package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createShortBuffer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import java.util.LinkedHashMap;

class FastShortIndexer<ARRAY_OBJECT extends Vertex> extends FastIndexer<ARRAY_OBJECT, Short> {

	/*
	 * The indices of the array objects. Will get burnt to the
	 * GL_ELEMENT_ARRAY_BUFFER.
	 */
	private final short[] indices;
	private final ShortBuffer idxBuf;
	
	private short numObjs;
	
	FastShortIndexer(int maxNumObjs)
	{
		super(maxNumObjs);
		indices = new short[maxNumObjs];
		idxBuf = createShortBuffer(maxNumObjs);
	}

	@Override
	public IndexType getIndexType()
	{
		return IndexType.SHORT;
	}

	@Override
	public boolean index(ARRAY_OBJECT object)
	{
		Short idx = vertices.get(object);
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
