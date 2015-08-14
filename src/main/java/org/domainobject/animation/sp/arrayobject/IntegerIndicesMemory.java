package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
public abstract class IntegerIndicesMemory<T extends ArrayObject> extends LazilyIndexedMemory<T> {

	private final int[] indices;
	private final ByteBuffer indicesBuf;

	public IntegerIndicesMemory(T[] objects, int numComponents)
	{
		super(objects, numComponents);
		indices = new int[objects.length];
		indicesBuf = BufferUtils.createByteBuffer(objects.length * Integer.BYTES);
	}


	@Override
	public Class<?> getIndexType()
	{
		return int.class;
	}

	@Override
	public ShaderInput burn()
	{
		if (numElements == 0)
			MemoryException.cannotBurnWhenEmpty();
		int uniqueObjects = fillIndices();
		indicesBuf.clear();
		indicesBuf.asIntBuffer().put(indices, 0, numObjects);
		indicesBuf.flip();
		buf.clear();
		if (uniqueObjects == numObjects)
			buf.put(raw, 0, numElements);
		else if (shuffle) {
			reshuffle();
			buf.put(raw, 0, numElements);
		}
		else {
			float[] compressed = compress(uniqueObjects);
			buf.put(compressed, 0, compressed.length);
		}
		buf.flip();
		numElements = 0;
		numObjects = 0;
		return new ShaderInput(buf, indicesBuf);
	}

	private float[] compress(int uniqueObjects)
	{
		float[] compressed = new float[uniqueObjects * numComponents];
		int offset = 0;
		for (int index : indices) {
			objects[index].copyTo(compressed, offset);
			offset += numComponents;
		}
		return compressed;
	}

	private void reshuffle()
	{
		numElements = 0;
		for (int i = 1; i < indices.length; ++i) {
			if (i == indices[i])
				continue;
			objects[indices[i]].copyTo(raw, numElements);
			numElements += numComponents;
		}
	}

	private int fillIndices()
	{
		HashMap<T, Integer> table = new HashMap<>(numObjects, 1.0f);
		for (int i = 0; i < numObjects; ++i) {
			Integer index = table.get(objects[i]);
			if (index == null)
				table.put(objects[i], (indices[i] = i));
			else
				indices[i] = index;
		}
		return table.size();
	}

}
