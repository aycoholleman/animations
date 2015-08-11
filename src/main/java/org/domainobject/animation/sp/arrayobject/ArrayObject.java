package org.domainobject.animation.sp.arrayobject;

/**
 * {@link ArrayObject} is the abstract base class for all OpenGL objects that
 * basically wrap a small float array, like vectors, vertices and matrices.
 * 
 * @author Ayco Holleman
 * @created Aug 5, 2015
 */
abstract class ArrayObject {

	final float[] components;
	final int offset;


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


}
