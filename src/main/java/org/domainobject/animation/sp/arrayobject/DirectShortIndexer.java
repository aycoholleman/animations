package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createShortBuffer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import java.util.LinkedHashMap;

class DirectShortIndexer<ARRAY_OBJECT extends Vertex> extends FastIndexer<ARRAY_OBJECT, Short> {

	private final ShortBuffer idxBuf;

	private short numObjs;

	DirectShortIndexer(int maxNumObjs)
	{
		super(maxNumObjs);
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
