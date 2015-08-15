package org.domainobject.animation.sp.arrayobject;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
abstract class AbstractMemory<T extends ArrayObject> {

	final FloatBuffer objBuf;
	final float[] raw;
	final T[] objects;
	final int objSize;

	int numObjs;
	int numElems;


	AbstractMemory(T[] objects, int objSize)
	{
		this.objects = objects;
		this.objSize = objSize;
		raw = new float[objects.length * objSize];
		objBuf = BufferUtils.createFloatBuffer(raw.length);
	}


	public T newInstance()
	{
		T object = construct(raw, numElems);
		objects[numObjs++] = object;
		numElems += objSize;
		return object;
	}

	public T next()
	{
		if (objects[numObjs] == null)
			return newInstance();
		return objects[numObjs++];
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
		numElems = 0;
		numObjs = 0;
	}


	public abstract ShaderInput burn();

}
