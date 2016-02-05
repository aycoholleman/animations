package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.FloatBuffer;

/**
 * @author Ayco Holleman
 *
 */
public abstract class Memory<T extends Vertex> {

	private final T[] objs;
	private final float[] raw;
	private final FloatBuffer objBuf;
	private final int objSize;

	private final IConstructor<T> constructor;

	private int numObjs;

	Memory(int maxNumObjs, int objSize)
	{
		this.objSize = objSize;
		this.constructor = getConstructor();
		this.objs = constructor.array(maxNumObjs);
		this.raw = new float[maxNumObjs * objSize];
		this.objBuf = createFloatBuffer(raw.length);
	}

	public T make()
	{
		T object = constructor.make(raw, numObjs * objSize);
		objs[numObjs++] = object;
		return object;
	}

	public T[] make(int howmany)
	{
		T[] sandbox = constructor.array(howmany);
		for (int i = 0; i < howmany; ++i) {
			T obj = constructor.make(raw, numObjs * objSize);
			objs[numObjs++] = sandbox[i] = obj;
		}
		return sandbox;
	}

	abstract IConstructor<T> getConstructor();

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
		if (numObjs == 0) {
			MemoryException.cannotBurnWhenEmpty();
		}
		objBuf.clear();
		objBuf.put(raw, 0, numObjs * objSize);
		objBuf.flip();
		return new ShaderInput(objBuf);
	}

	public void clear()
	{
		numObjs = 0;
	}

}
