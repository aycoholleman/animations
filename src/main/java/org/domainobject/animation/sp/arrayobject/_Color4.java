package org.domainobject.animation.sp.arrayobject;

/**
 * Interface to be implemented by classes capable of representing a 4-component
 * color (rgba).
 * 
 * @author Ayco Holleman
 * @created Aug 9, 2015
 *
 */
public interface _Color4 {

	/**
	 * Set the red, green and blue channel of this {@link _Color4} instance.
	 * 
	 * @return This instance
	 */
	_Color4 rgb(float red, float green, float blue);

	/**
	 * Set the red, green and blue channel of this {@link _Color4} instance.
	 * 
	 * @param rgb
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
	 */
	_Color4 rgb(float[] rgb);

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
