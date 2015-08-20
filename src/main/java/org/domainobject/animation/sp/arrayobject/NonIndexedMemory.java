package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.FloatBuffer;

/**
 * @author Ayco Holleman
 *
 */
public abstract class NonIndexedMemory<T extends ArrayObject> {

	private final T[] objs;
	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private int numObjs;
	private int numElems;


	NonIndexedMemory(T[] objects, int objSize)
	{
		this.objs = objects;
		this.objSize = objSize;
		raw = new float[objects.length * objSize];
		objBuf = createFloatBuffer(raw.length);
	}

	public T newInstance()
	{
		T object = construct(raw, numElems);
		objs[numObjs++] = object;
		numElems += objSize;
		return object;
	}

	abstract T construct(float[] raw, int offset);

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
	 * {@code VertexArray}
	 * 
	 * @throws MemoryException
	 * If the {@code VertexArray} is empty.
	 * 
	 * @see #clear()
	 */
	public ShaderInput burn()
	{
		if (numElems == 0) {
			MemoryException.cannotBurnWhenEmpty();
		}
		objBuf.clear();
		objBuf.put(raw, 0, numElems);
		objBuf.flip();
		return new ShaderInput(objBuf);
	}
	
	public void clear()
	{
		numElems = 0;
		numObjs = 0;
	}
	
}