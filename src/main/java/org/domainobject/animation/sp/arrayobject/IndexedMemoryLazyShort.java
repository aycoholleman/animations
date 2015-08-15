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
public abstract class IndexedMemoryLazyShort<T extends ArrayObject> implements Iterable<T> {

	private final T[] objs;
	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final short[] indices;
	private final ByteBuffer idxBuf;

	private boolean pack;
	private short numObjs;
	private int numElems;

	IndexedMemoryLazyShort(T[] objects, int objSize)
	{
		this.objs = objects;
		this.objSize = objSize;
		raw = new float[objects.length * objSize];
		objBuf = createFloatBuffer(raw.length);
		indices = new short[objects.length];
		idxBuf = createByteBuffer(objects.length * Short.BYTES);
	}

	abstract T construct(float[] raw, int offset);

	public Class<?> getIndexType()
	{
		return short.class;
	}

	public int size()
	{
		return numElems;
	}

	public int countObjects()
	{
		return numObjs;
	}

	public boolean pack()
	{
		return pack;
	}

	public void pack(boolean pack)
	{
		this.pack = pack;
	}

	public T newInstance()
	{
		T obj = construct(raw, numElems);
		objs[numObjs++] = obj;
		numElems += objSize;
		return obj;
	}

	public ShaderInput burn()
	{
		if (numElems == 0)
			MemoryException.cannotBurnWhenEmpty();
		int uniqObjs = createIndex();
		objBuf.clear();
		if (uniqObjs == numObjs)
			objBuf.put(raw, 0, numElems);
		else if (pack)
			packAndPut();
		else
			copyAndPut(uniqObjs);
		objBuf.flip();
		idxBuf.clear();
		idxBuf.asShortBuffer().put(indices, 0, numObjs);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);
	}

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

	private void packAndPut()
	{
		/*
		 * Skip 1st object because it will never evaporate or relocate because
		 * of packing
		 */
		numElems = objSize;
		for (numObjs = 1; numObjs < indices.length; ++numObjs) {
			short idx = indices[numObjs];
			if (idx == numObjs)
				continue;
			objs[idx].copyTo(raw, numElems);
			numElems += objSize;
		}
		objBuf.put(raw, 0, numElems);
	}

	private void copyAndPut(int uniqueObjects)
	{
		float[] copy = new float[uniqueObjects * objSize];
		int offset = 0;
		for (short idx : indices) {
			objs[idx].copyTo(copy, offset);
			offset += objSize;
		}
		objBuf.put(copy, 0, copy.length);
	}

	private int createIndex()
	{
		HashMap<T, Short> tbl = new HashMap<>(numObjs, 1.0f);
		for (int i = 0; i < numObjs; ++i) {
			Short idx = tbl.get(objs[i]);
			if (idx == null)
				tbl.put(objs[i], (indices[i] = (short) i));
			else
				indices[i] = idx;
		}
		return tbl.size();
	}

}
