package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.nio.ByteBuffer;

class LazyIntIndexer implements _LazyIndexer {

	private final int[] indices;

	public LazyIntIndexer(int maxNumObjs)
	{
		indices = new int[maxNumObjs];
	}

	@Override
	public Class<?> getIndexType()
	{
		return int.class;
	}

	@Override
	public ByteBuffer createIndicesBuffer()
	{
		return createByteBuffer(indices.length * Integer.BYTES);
	}
	@Override
	public void assignIndex(int objNum, Integer index)
	{
		indices[objNum] = index.intValue();
	}

	@Override
	public void assignIndex(int objNum, int index)
	{
		indices[objNum] = index;
	}

	@Override
	public void burnIndices(ByteBuffer idxBuf, int numObjs)
	{
		idxBuf.asIntBuffer().put(indices, 0, numObjs);
	}

}
