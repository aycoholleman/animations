package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
public abstract class ShortIndicesMemory<T extends ArrayObject> extends AbstractMemory<T> {

	private final short[] indices;
	private final ByteBuffer indicesBuf;

	public ShortIndicesMemory(T[] objects, int numComponents)
	{
		super(objects, numComponents);
		indices = new short[objects.length];
		indicesBuf = BufferUtils.createByteBuffer(objects.length * Short.BYTES);
	}

	@Override
	public ShaderInput burn()
	{
		if (numElements == 0) {
			MemoryException.cannotBurnWhenEmpty();
		}

		int uniqueObjects = fillIndices();

		indicesBuf.clear();
		indicesBuf.asShortBuffer().put(indices, 0, numObjects);
		indicesBuf.flip();
		
		buf.clear();
		if (uniqueObjects == numObjects) {
			buf.put(raw, 0, numElements);
		}
		else {
			float[] compressed = compress(uniqueObjects);
			buf.put(compressed, 0, compressed.length);
		}
		buf.flip();
		
		numElements = 0;
		numObjects = 0;
		
		return null;
	}

	private float[] compress(int uniqueObjects)
	{
		float[] compressed = new float[uniqueObjects * numComponents];
		int offset = 0;
		for (short index : indices) {
			objects[index].copyTo(compressed, offset);
			offset += numComponents;
		}
		return compressed;
	}

	private int fillIndices()
	{
		HashMap<T, Short> table = new HashMap<>(numObjects, 1.0f);
		for (int i = 0; i < numObjects; ++i) {
			Short index = table.get(objects[i]);
			if (index == null)
				table.put(objects[i], (indices[i] = (short) i));
			else
				indices[i] = index;
		}
		return table.size();
	}

}
