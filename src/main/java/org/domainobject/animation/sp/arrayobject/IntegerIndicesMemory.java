package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
public abstract class IntegerIndicesMemory<T extends ArrayObject> extends IndexedMemoryLazy<T> {

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
		if (numElems == 0)
			MemoryException.cannotBurnWhenEmpty();
		int uniqueObjects = fillIndices();
		indicesBuf.clear();
		indicesBuf.asIntBuffer().put(indices, 0, numObjs);
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
		for (int index : indices) {
			objects[index].copyTo(compressed, offset);
			offset += objSize;
		}
		return compressed;
	}

	private void reshuffle()
	{
		numElems = 0;
		for (int i = 1; i < indices.length; ++i) {
			if (i == indices[i])
				continue;
			objects[indices[i]].copyTo(raw, numElems);
			numElems += objSize;
		}
	}

	private int fillIndices()
	{
		HashMap<T, Integer> table = new HashMap<>(numObjs, 1.0f);
		for (int i = 0; i < numObjs; ++i) {
			Integer index = table.get(objects[i]);
			if (index == null)
				table.put(objects[i], (indices[i] = i));
			else
				indices[i] = index;
		}
		return table.size();
	}

}
