package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
public abstract class IndexedMemory<T extends ArrayObject> extends AbstractMemory<T> {

	private ByteBuffer indices;
	private Class<?> indexType;

	public IndexedMemory(T[] objects, int numComponents)
	{
		super(objects, numComponents);
		int bytes;
		if (objects.length < Byte.MAX_VALUE) {
			indexType = byte.class;
			bytes = Byte.BYTES;
		}
		else if (objects.length < Short.MAX_VALUE) {
			indexType = short.class;
			bytes = Short.BYTES;
		}
		else {
			indexType = int.class;
			bytes = Integer.BYTES;
		}
		indices = BufferUtils.createByteBuffer(objects.length * bytes);
	}

	@Override
	public ShaderInput burn()
	{
		if (numElements == 0) {
			MemoryException.cannotBurnWhenEmpty();
		}
		buf.clear();
		if (indexType == short.class) {
			HashMap<T, Short> temp = new HashMap<>(numObjects + 4, 1.0f);
			short[] elements = new short[numObjects];
			for (int i = 0; i < numObjects; ++i) {
				Short index = temp.get(objects[i]);
				if (index == null) {
					short s = (short) i;
					elements[i] = s;
					temp.put(objects[i], Short.valueOf(s));
				}
				else {
					elements[i] = index.shortValue();
				}
			}

			if (temp.size() == numObjects) {
				/*
				 * All vertices in memory are distinguishable from eachother.
				 * Might as well use the original raw float array.
				 */
				buf.put(raw, 0, numElements);
			}
			else {
				float[] compressed = new float[temp.size() * numComponents];
				int offset = 0;
				for (int i = 0; i < elements.length; ++i) {
					objects[elements[i]].copyTo(compressed, offset);
					offset += numComponents;
				}
				buf.put(compressed, 0, compressed.length);
			}

			buf.flip();
			numElements = 0;
			numObjects = 0;

			indices.clear();
			indices.asShortBuffer().put(elements);
			indices.flip();
		}
		return null;
	}

}
