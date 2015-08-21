package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Ayco Holleman
 *
 */
abstract class IndexedMemoryLazyByte<T extends ArrayObject> implements _IndexedMemoryLazy<T> {

	private final T[] objs;
	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final byte[] indices;
	private final ByteBuffer idxBuf;

	private boolean destructive;
	private int numObjs;
	private int numElems;

	IndexedMemoryLazyByte(T[] objects, int objSize)
	{
		this.objs = objects;
		this.objSize = objSize;
		raw = new float[objects.length * objSize];
		objBuf = createFloatBuffer(raw.length);
		indices = new byte[objects.length];
		idxBuf = createByteBuffer(objects.length * Byte.BYTES);
	}

	abstract T construct(float[] raw, int offset);

	@Override
	public void add(T arrayObject)
	{
		T copy = construct(raw, numElems);
		arrayObject.copyTo(copy);
		numElems += objSize;
		numObjs++;
	}

	@Override
	public T newInstance()
	{
		T obj = construct(raw, numElems);
		objs[numObjs++] = obj;
		numElems += objSize;
		return obj;
	}

	@Override
	public Class<?> getIndexType()
	{
		return byte.class;
	}

	@Override
	public int size()
	{
		return numObjs;
	}

	@Override
	public boolean isDestructive()
	{
		return destructive;
	}

	@Override
	public void setDestructive(boolean destructive)
	{
		this.destructive = destructive;
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
		numObjs = (byte) uniqObjs;
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
		idxBuf.put(indices, 0, numObjs);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);
	}

	public ShaderInput burnUnique()
	{
		if (numElems == 0)
			MemoryException.cannotBurnWhenEmpty();
		// Create brainless index
		for (byte i = 0; i < numObjs; ++i) {
			indices[i] = i;
		}
		objBuf.clear();
		objBuf.put(raw, 0, numElems);
		objBuf.flip();
		idxBuf.clear();
		idxBuf.put(indices, 0, numObjs);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);

	}

	@Override
	public void clear()
	{
		numObjs = 0;
		numElems = 0;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>() {
			int i = 0;
			public boolean hasNext()
			{
				return i < numObjs;
			}
			public T next()
			{
				return objs[i++];
			}
		};
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
		numObjs = (byte) uniqObjs;
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
		HashMap<T, Byte> tbl = new HashMap<>(numObjs, 1.0f);
		for (int i = 0; i < numObjs; ++i) {
			Byte idx = tbl.get(objs[i]);
			if (idx == null)
				tbl.put(objs[i], (indices[i] = (byte) i));
			else
				indices[i] = idx;
		}
		return tbl.size();
	}

}
