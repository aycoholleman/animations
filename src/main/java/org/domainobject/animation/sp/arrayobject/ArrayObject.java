package org.domainobject.animation.sp.arrayobject;

/**
 * {@link ArrayObject} is the abstract base class for all OpenGL objects that
 * basically wrap a small array of float elements, like vectors, vertices and
 * matrices.
 * 
 * @author Ayco Holleman
 * @created Aug 5, 2015
 */
abstract class ArrayObject {

	final float[] components;
	final int offset;

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

	/**
	 * Get the size of the array wrapped by this instance.
	 * 
	 * @return The size of the array wrapped by this instance
	 */
	abstract int size();
	
	public void copyTo(float[] array, int offset) {
		
	}
	
	<T extends ArrayObject> void copyTo(T other) {
		
	}


}
