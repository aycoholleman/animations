package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.FloatBuffer;

/**
 * @author Ayco Holleman
 *
 */
public abstract class Memory<T extends ArrayObject> {

	private final T[] objs;
	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final _Constructor<T> constructor;

	private int numObjs;
	private int numElems;


	Memory(int maxNumObjects, int objSize)
	{
		this.constructor = getConstructor();
		this.objs = constructor.array(maxNumObjects);
		this.objSize = objSize;
		raw = new float[maxNumObjects * objSize];
		objBuf = createFloatBuffer(raw.length);
	}

	public T make()
	{
		T object = constructor.make(raw, numElems);
		objs[numObjs++] = object;
		numElems += objSize;
		return object;
	}

	abstract _Constructor<T> getConstructor();

	/**
	 * Burns the vertex data to a {@code FloatBuffer} and then clears the
	 * {@code VertexArray}. The {@code FloatBuffer} is flipped before being
	 * returned to the caller. You cannot burn an empty {@code VertexArray} (i.e.
	 * when {@link #getSize()} returns 0). Doing so triggers a
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
