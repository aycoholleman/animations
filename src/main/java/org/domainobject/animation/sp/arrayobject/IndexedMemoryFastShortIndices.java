package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.LinkedHashMap;

/**
 * @author Ayco Holleman
 *
 */
public abstract class IndexedMemoryFastShortIndices<T extends ArrayObject> {

	private float[] raw;
	private final FloatBuffer buf;
	private final int objSize;

	private final short[] indices;
	private final ByteBuffer indicesBuf;
	private final LinkedHashMap<T, Short> tbl;

	private short numObjects;
	private int numElements;

	IndexedMemoryFastShortIndices(int maxNumObjects, int objSize)
	{
		this.objSize = objSize;
		raw = new float[maxNumObjects * objSize];
		buf = createFloatBuffer(raw.length);
		indices = new short[maxNumObjects];
		indicesBuf = createByteBuffer(maxNumObjects * Short.BYTES);
		tbl = new LinkedHashMap<>(maxNumObjects, 1.0f);
	}

	abstract T construct(float[] raw, int offset);

	public void append(T arrayObject)
	{
		Short index = tbl.get(arrayObject);
		if (index == null) {
			T copy = construct(raw, numElements);
			arrayObject.copyTo(copy);
			indices[numObjects] = numObjects;
			tbl.put(copy, numObjects);
			numElements += objSize;
		}
		else {
			indices[numObjects] = index;
		}
		numObjects++;
	}

}
