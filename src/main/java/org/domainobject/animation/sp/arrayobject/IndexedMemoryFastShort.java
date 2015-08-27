package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;


/**
 * @author Ayco Holleman
 *
 */
abstract class IndexedMemoryFastShort<T extends ArrayObject> extends IndexedMemoryFast<T, Short> {

	private final short[] indices;
	private final ByteBuffer idxBuf;

	IndexedMemoryFastShort(int maxNumObjects, int objSize)
	{
		super(maxNumObjects, objSize);
		indices = new short[maxNumObjects];
		idxBuf = createByteBuffer(maxNumObjects * Short.BYTES);
	}

	@Override
	public Class<?> getIndexType()
	{
		return short.class;
	}

	@Override
	void addNewArrayObject(LinkedHashMap<T, Short> objs, T obj, int numObjs)
	{
		short index = (short) numObjs;
		indices[numObjs] = index;
		objs.put(obj, Short.valueOf(index));
	}

	@Override
	void indexDuplicate(int numObjs, Short index)
	{
		indices[numObjs] = index;
	}

	@Override
	ByteBuffer bufferIndices(int numIndices)
	{
		idxBuf.clear();
		idxBuf.asShortBuffer().put(indices, 0, numIndices);
		idxBuf.flip();
		return idxBuf;
	}

	@Override
	void clearIndicesBuffer()
	{
		idxBuf.clear();
	}
}
