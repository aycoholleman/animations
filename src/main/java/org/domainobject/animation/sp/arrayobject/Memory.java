package org.domainobject.animation.sp.arrayobject;

/**
 * @author Ayco Holleman
 *
 */
public abstract class Memory<T extends ArrayObject> extends AbstractMemory<T> {

	Memory(T[] objects, int numComponents)
	{
		super(objects, numComponents);
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
	 * {@code VertexArray}
	 * 
	 * @throws MemoryException
	 * If the {@code VertexArray} is empty.
	 * 
	 * @see #clear()
	 */
	@Override
	public ShaderInput burn()
	{
		if (numElements == 0) {
			MemoryException.cannotBurnWhenEmpty();
		}
		buf.clear();
		buf.put(raw, 0, numElements);
		buf.flip();
		numElements = 0;
		numObjects = 0;
		return new ShaderInput(buf);
	}
}
