package org.domainobject.animation.sp.arrayobject;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
abstract class AbstractMemory<T extends ArrayObject> {

	final FloatBuffer buf;
	final float[] raw;
	final T[] objects;
	final int numComponents;

	int numObjects;
	int numElements;


	AbstractMemory(T[] objects, int numComponents)
	{
		this.objects = objects;
		this.numComponents = numComponents;
		raw = new float[objects.length * numComponents];
		buf = BufferUtils.createFloatBuffer(raw.length);
	}


	public T newInstance()
	{
		T object = construct(raw, numElements);
		objects[numObjects++] = object;
		numElements += numComponents;
		return object;
	}

	public T next()
	{
		if (objects[numObjects] == null)
			return newInstance();
		return objects[numObjects++];
	}

	/**
	 * Get the {@link ArrayObject} at the specified position within the
	 * internally maintained array of {@code ArrayObjects}s.
	 * 
	 * @param index
	 * The index of the {@link ArrayObject} within the internally maintained
	 * array of {@code ArrayObjects}s
	 * 
	 * @return The {@link ArrayObject}
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * No array index checking in this method!
	 */
	public T get(int index)
	{
		return objects[index];
	}


	abstract T construct(float[] raw, int offset);


	public void clear()
	{
		numElements = 0;
		numObjects = 0;
	}


	public abstract ShaderInput burn();

}
