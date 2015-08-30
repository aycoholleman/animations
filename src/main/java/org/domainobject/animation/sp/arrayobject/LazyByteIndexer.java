package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;

class LazyByteIndexer implements _LazyIndexer {

	private final byte[] indices;

	public LazyByteIndexer(int maxNumObjs)
	{
		indices = new byte[maxNumObjs];
	}

	@Override
	public Class<?> getIndexType()
	{
		return byte.class;
	}

	@Override
	public ByteBuffer createIndicesBuffer()
	{
		return createByteBuffer(indices.length * Byte.BYTES);
	}

	@Override
	public void index(int objNum, Integer index)
	{
		indices[objNum] = index.byteValue();
	}

	@Override
	public void index(int objNum, int index)
	{
		indices[objNum] = (byte) index;
	}

	@Override
	public void burnIndices(ByteBuffer idxBuf, int numObjs)
	{
		idxBuf.put(indices, 0, numObjs);
	}

	@Override
	public void burnDummy(ByteBuffer idxBuf, int numObjs)
	{
		for (int i = 0; i < numObjs; i++)
			indices[i] = (byte) i;
		idxBuf.put(indices, 0, numObjs);
	}

}
