package org.domainobject.animation.sp.util.vertex;

/**
 * An {@code Array} is an object that wraps an a float array.
 * 
 * @author Ayco Holleman
 * @created Aug 5, 2015
 *
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
	 * Get the number of components (array elements) of this {@code Array}
	 * .
	 * 
	 * @return The number of components of this {@code Array}
	 */
	abstract int size();

}
