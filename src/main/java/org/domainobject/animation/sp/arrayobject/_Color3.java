package org.domainobject.animation.sp.arrayobject;

/**
 * Interface to be implemented by classes capable of representing a 3-component
 * color (rgb).
 * 
 * @author Ayco Holleman
 *
 */
public interface _Color3 {

	/**
	 * Set the red, green and blue channel of this {@link _Color3} instance.
	 * 
	 * @return This instance
	 */
	_Color3 rgb(float red, float green, float blue);

	/**
	 * Set the red, green and blue channel of this {@link _Color3} instance.
	 * 
	 * @param rgb
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
	 */
	_Color3 rgb(float[] rgb);

}
