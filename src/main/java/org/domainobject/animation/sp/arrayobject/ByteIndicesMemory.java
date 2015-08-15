package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
public abstract class ByteIndicesMemory<T extends ArrayObject> extends IndexedMemoryLazy<T> {

	private final byte[] indices;
	private final ByteBuffer indicesBuf;

	public ByteIndicesMemory(T[] objects, int numComponents)
	{
		super(objects, numComponents);
		indices = new byte[objects.length];
		indicesBuf = BufferUtils.createByteBuffer(objects.length * Byte.BYTES);
	}

	@Override
	public Class<?> getIndexType()
	{
		return byte.class;
	}

	@Override
	public ShaderInput burn()
	{
		if (numElems == 0)
			MemoryException.cannotBurnWhenEmpty();
		int uniqueObjects = fillIndices();
		indicesBuf.clear();
		indicesBuf.put(indices, 0, numObjs);
		indicesBuf.flip();
		objBuf.clear();
		if (uniqueObjects == numObjs)
			objBuf.put(raw, 0, numElems);
		else if (shuffle) {
			reshuffle();
			objBuf.put(raw, 0, numElems);
		}
		else {
			float[] compressed = compress(uniqueObjects);
			objBuf.put(compressed, 0, compressed.length);
		}
		objBuf.flip();
		numElems = 0;
		numObjs = 0;
		return new ShaderInput(objBuf, indicesBuf);
	}

	private float[] compress(int uniqueObjects)
	{
		float[] compressed = new float[uniqueObjects * objSize];
		int offset = 0;
		for (short index : indices) {
			objects[index].copyTo(compressed, offset);
			offset += objSize;
		}
		return compressed;
	}

	private void reshuffle()
	{
		numElems = 0;
		for (byte b = 1; b < indices.length; ++b) {
			if (b == indices[b])
				continue;
			objects[indices[b]].copyTo(raw, numElems);
			numElems += objSize;
		}
	}
	
	private int fillIndices()
	{
		HashMap<T, Byte> table = new HashMap<>(numObjs, 1.0f);
		for (int i = 0; i < numObjs; ++i) {
			Byte index = table.get(objects[i]);
			if (index == null)
				table.put(objects[i], (indices[i] = (byte) i));
			else
				indices[i] = index;
		}
		return table.size();
	}

}
