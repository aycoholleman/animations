package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;

class LazyShortIndexer implements _LazyIndexer {

	private final short[] indices;

	public LazyShortIndexer(int maxNumObjs)
	{
		indices = new short[maxNumObjs];
	}

	@Override
	public Class<?> getIndexType()
	{
		return short.class;
	}

	@Override
	public ByteBuffer createIndicesBuffer()
	{
		return createByteBuffer(indices.length * Short.BYTES);
	}

	@Override
	public void assignIndex(int objNum, Integer index)
	{
		indices[objNum] = index.shortValue();
	}

	@Override
	public void assignIndex(int objNum, int index)
	{
		indices[objNum] = (short) index;
	}

	@Override
	public void burnIndices(ByteBuffer idxBuf, int numObjs)
	{
		idxBuf.asShortBuffer().put(indices, 0, numObjs);
	}

}
