package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.LinkedHashMap;

/**
 * @author Ayco Holleman
 *
 */
abstract class IndexedMemoryInt<T extends ArrayObject> {

	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final int[] indices;
	private final ByteBuffer idxBuf;
	private final LinkedHashMap<T, Integer> tbl;

	private int numObjs;
	private int numElems;

	IndexedMemoryInt(int maxNumObjects, int objSize)
	{
		this.objSize = objSize;
		raw = new float[maxNumObjects * objSize];
		objBuf = createFloatBuffer(raw.length);
		indices = new int[maxNumObjects];
		idxBuf = createByteBuffer(maxNumObjects * Integer.BYTES);
		tbl = new LinkedHashMap<>(maxNumObjects, 1.0f);
	}

	abstract T construct(float[] raw, int offset);

	public Class<?> getIndexType()
	{
		return byte.class;
	}

	public void append(T arrayObject)
	{
		Integer index = tbl.get(arrayObject);
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

	public ShaderInput burn()
	{
		if (numObjs == 0)
			MemoryException.cannotBurnWhenEmpty();
		objBuf.clear();
		objBuf.put(raw, 0, numElems);
		objBuf.flip();
		idxBuf.clear();
		idxBuf.asIntBuffer().put(indices, 0, numObjs);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);
	}

	public void clear()
	{
		numObjs = 0;
		numElems = 0;
		tbl.clear();
	}
}
