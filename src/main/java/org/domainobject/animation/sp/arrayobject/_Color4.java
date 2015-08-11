package org.domainobject.animation.sp.arrayobject;

/**
 * Interface to be implemented by classes capable of representing a 4-component
 * color (rgba).
 * 
 * @author Ayco Holleman
 * @created Aug 9, 2015
 *
 */
public interface _Color4 extends _Color3 {

	/**
	 * Set the channels of this {@link _Color4} instance.
	 * 
	 * @return This instance
	 */
	_Color4 rgba(float red, float green, float blue, float alpha);

	/**
	 * 
	 * @param rgba
	 * A {@code float} array containing at least 4 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 4 elements
	 */
	_Color4 rgba(float[] rgba);

}
