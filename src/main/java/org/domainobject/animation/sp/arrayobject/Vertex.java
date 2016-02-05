package org.domainobject.animation.sp.arrayobject;

/**
 * Abstract base class for all OpenGL objects that wrap a small array of float
 * elements, like vectors, vertices and matrices. The elements of the array are
 * often referred to as components, e.g. the x component or the red component.
 * 
 * @author Ayco Holleman
 */
public abstract class Vertex {

	final float[] components;
	final int offset;

	ICommittable memory;

	Vertex()
	{
		this.components = new float[objSize()];
		this.offset = 0;
	}

	/*
	 * Embeds this instance within a large {@code float} array at the specified
	 * offset.
	 */
	Vertex(float[] components, int offset)
	{
		this.components = components;
		this.offset = offset;
	}

	/*
	 * Embeds another, smaller type of array object within the specified array
	 * object at the specified position. The specified offset is relative to the
	 * start of the embedding instance!
	 */
	Vertex(Vertex embedder, int offset)
	{
		this.components = embedder.components;
		this.offset = embedder.offset + offset;
	}

	/**
	 * Copies the components of this array object to the specified array.
	 * 
	 * @param target
	 * The arget array
	 * @param offset
	 * The offset within the target array
	 * 
	 * @throw {@link ArrayIndexOutOfBoundsException} If there are not enough
	 * elements in the target array (after {@code offset})
	 */
	public abstract void copyTo(float[] target, int offset);

	/**
	 * When you obtained this instance through the {@link _Memory#alloc()
	 * newInstance} method of a {@link _IndexedMemoryFast "fast"} type of
	 * memory, you <b>must</b> call {@code commit} to commit the array object to
	 * memory. Otherwise it will not be visible to the burn process. For the
	 * other types of memory this method is a no-op. This method will throw a
	 * {@link MemoryException} if you call it on an instance that has meanwhile
	 * been overwritten in memory because subsequent calls to
	 * {@link _Memory#alloc() newInstance}.
	 */
	public void commit()
	{
		if (memory != null)
			memory.commit(this);
	}

	/**
	 * Get the number of components in this array object.
	 * 
	 * @return The size of the array wrapped by this instance
	 */
	abstract int objSize();

	/*
	 * NB In spite of the ArrayObject argument, this method really expects this
	 * array object and the other array object to be instances of exactly the
	 * same subclass of ArrayObject.
	 */
	abstract void copyTo(Vertex other);


}
