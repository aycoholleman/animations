package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * @author Ayco Holleman
 *
 */
abstract class IndexedMemoryLazyInt<T extends ArrayObject> extends IndexedMemoryLazy<T> {

	private final int[] indices;
	private final ByteBuffer idxBuf;

	IndexedMemoryLazyInt(int maxNumObjects, int objSize)
	{
		super(maxNumObjects, objSize);
		indices = new int[maxNumObjects];
		idxBuf = createByteBuffer(maxNumObjects * Integer.BYTES);
	}

	@Override
	public Class<?> getIndexType()
	{
		return int.class;
	}

	@Override
	public void pack()
	{
		// Skip 1st object because it will never evaporate or relocate
		int uniqObjs = createIndex();
		numElems = objSize;
		for (int i = 1; i < numObjs; ++i) {
			if (indices[i] != i)
				continue;
			objs[indices[i]].copyTo(raw, numElems);
			numElems += objSize;
		}
		numObjs = uniqObjs;
		objBuf.put(raw, 0, numElems);
	}

	@Override
	public ShaderInput burn()
	{
		if (numElems == 0)
			MemoryException.cannotBurnWhenEmpty();
		int uniqObjs = createIndex();
		objBuf.clear();
		if (uniqObjs == numObjs)
			objBuf.put(raw, 0, numElems);
		else if (destructive)
			packAndPut(uniqObjs);
		else
			copyAndPut(uniqObjs);
		objBuf.flip();
		idxBuf.clear();
		idxBuf.asIntBuffer().put(indices, 0, numObjs);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);
	}

	public ShaderInput burnUnique()
	{
		if (numElems == 0)
			MemoryException.cannotBurnWhenEmpty();
		// Create brainless index
		for (int i = 0; i < numObjs; ++i) {
			indices[i] = i;
		}
		objBuf.clear();
		objBuf.put(raw, 0, numElems);
		objBuf.flip();
		idxBuf.clear();
		idxBuf.asIntBuffer().put(indices, 0, numObjs);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);

	}

	private void packAndPut(int uniqObjs)
	{
		// Skip 1st object because it will never evaporate or relocate
		numElems = objSize;
		for (int i = 1; i < numObjs; ++i) {
			if (indices[i] != i)
				continue;
			objs[indices[i]].copyTo(raw, numElems);
			numElems += objSize;
		}
		numObjs = uniqObjs;
		objBuf.put(raw, 0, numElems);
	}

	private void copyAndPut(int uniqObjs)
	{
		float[] copy = new float[uniqObjs * objSize];
		int offset = 0;
		for (int i = 1; i < numObjs; ++i) {
			if (indices[i] != i)
				continue;
			objs[indices[i]].copyTo(copy, offset);
			offset += objSize;
		}
		objBuf.put(copy, 0, copy.length);
	}

	private int createIndex()
	{
		HashMap<T, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		for (int i = 0; i < numObjs; ++i) {
			Integer idx = tbl.get(objs[i]);
			if (idx == null)
				tbl.put(objs[i], (indices[i] = i));
			else
				indices[i] = idx;
		}
		return tbl.size();
	}

}
