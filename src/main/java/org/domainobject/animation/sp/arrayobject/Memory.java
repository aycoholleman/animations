package org.domainobject.animation.sp.arrayobject;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 *
 */
public abstract class Memory<T extends ArrayObject> {

	private final FloatBuffer buf;
	private final float[] raw;
	private final T[] objects;
	private final int numComponents;
	
	int numObjects;
	private int size;


	Memory(T[] objects, int numComponents)
	{
		this.objects = objects;
		this.numComponents = numComponents;
		raw = new float[objects.length * numComponents];
		buf = BufferUtils.createFloatBuffer(raw.length);
	}


	public T newInstance()
	{
		T object = construct(raw, size);
		objects[numObjects++] = object;
		size += numComponents;
		return object;
	}


	abstract T construct(float[] raw, int offset);


	public void clear()
	{
		size = 0;
		numObjects = 0;
	}


	/**
	 * Burns the vertex data to a {@code FloatBuffer} and then clears the
	 * {@code VertexArray}. The {@code FloatBuffer} is flipped before being
	 * returned to the caller. You cannot burn an empty {@code VertexArray}
	 * (i.e. when {@link #getSize()} returns 0). Doing so triggers a
	 * {@link MemoryException}. Since burning implicitly clears the
	 * {@code VertexArray}, this means you cannot burn a {@code VertexArray}
	 * twice without adding some vertex data in between.
	 * 
	 * @return A {@code FloatBuffer} containing all vertices added to this
	 *         {@code VertexArray}
	 * 
	 * @throws MemoryException
	 *             If the {@code VertexArray} is empty.
	 * 
	 * @see #clear()
	 */
	public FloatBuffer burn()
	{
		if (size == 0) {
			MemoryException.cannotBurnWhenEmpty();
		}
		buf.clear();
		buf.put(raw, 0, size);
		buf.flip();
		size = 0;
		numObjects = 0;
		return buf;
	}
}
