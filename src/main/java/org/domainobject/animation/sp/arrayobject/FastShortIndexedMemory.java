package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author Ayco Holleman
 *
 */
abstract class FastShortIndexedMemory<T extends ArrayObject> {

	private float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final short[] indices;
	private final ByteBuffer idxBuf;

	private LinkedHashMap<T, Short> tbl;
	private short numObjs;
	private int numElems;

	FastShortIndexedMemory(int maxNumObjects, int objSize)
	{
		this.objSize = objSize;
		raw = new float[maxNumObjects * objSize];
		objBuf = createFloatBuffer(raw.length);
		indices = new short[maxNumObjects];
		idxBuf = createByteBuffer(maxNumObjects * Short.BYTES);
		tbl = new LinkedHashMap<>(maxNumObjects, 1.0f);
	}

	abstract T construct(float[] raw, int offset);

	public Class<?> getIndexType()
	{
		return short.class;
	}

	public void add(T arrayObject)
	{
		Short index = tbl.get(arrayObject);
		if (index == null) {
			T copy = construct(raw, numElems);
			arrayObject.copyTo(copy);
			indices[numObjs] = numObjs;
			tbl.put(copy, numObjs);
			numElems += objSize;
		}
		else {
			indices[numObjs] = index;
		}
		numObjs++;
	}

	public void addUnique(T arrayObject)
	{
		T copy = construct(raw, numElems);
		arrayObject.copyTo(copy);
		indices[numObjs] = numObjs;
		tbl.put(copy, numObjs);
		numElems += objSize;
		numObjs++;
	}

	public boolean contains(T arrayObject)
	{
		return tbl.keySet().contains(arrayObject);
	}

	public ShaderInput burn()
	{
		if (numObjs == 0)
			MemoryException.cannotBurnWhenEmpty();
		objBuf.clear();
		objBuf.put(raw, 0, numElems);
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
		tbl = new LinkedHashMap<>(indices.length, 1.0f);
	}

	public Iterator<T> iterator()
	{
		return tbl.keySet().iterator();
	}
}
