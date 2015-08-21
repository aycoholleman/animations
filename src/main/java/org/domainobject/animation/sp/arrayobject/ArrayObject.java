package org.domainobject.animation.sp.arrayobject;

/**
 * {@link ArrayObject} is the abstract base class for all OpenGL objects that
 * wrap a small array of float elements, like vectors, vertices and matrices.
 * 
 * @author Ayco Holleman
 */
public abstract class ArrayObject {

	final float[] components;
	final int offset;

	_Commitable commitable;

	ArrayObject()
	{
		this.components = new float[size()];
		this.offset = 0;
	}


	ArrayObject(float[] components, int offset)
	{
		this.components = components;
		this.offset = offset;
	}

	public abstract void copyTo(float[] array, int offset);

	public void commit()
	{
		if (commitable != null)
			commitable.commit(this);
	}

	/**
	 * Get the size of the array wrapped by this instance.
	 * 
	 * @return The size of the array wrapped by this instance
	 */
	abstract int size();

	/*
	 * NB In spite of the ArrayObject argument, this method really expects this
	 * array object and the other array object to be instances of exactly the
	 * same subclass of ArrayObject.
	 */
	abstract void copyTo(ArrayObject other);


}
