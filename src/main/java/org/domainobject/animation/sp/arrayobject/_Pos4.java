package org.domainobject.animation.sp.arrayobject;

public interface _Pos4 {

	/**
	 * Set the x, y and z coordinate of this instance.
	 * 
	 * @param x
	 * The x coordinate
	 * @param y
	 * The y coordinate
	 * @param z
	 * The z coordinate
	 * 
	 * @return This instance
	 */
	_Pos4 xyz(float x, float y, float z);

	/**
	 * Set the x, y and z coordinate of this instance.
	 * 
	 * @param xyz
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
	 */
	_Pos4 xyz(float[] xyz);

	/**
	 * Set the coordinates of this instance.
	 * 
	 * @param x
	 * The x coordinate
	 * @param y
	 * The y coordinate
	 * @param z
	 * The z coordinate
	 * @param w
	 * The w coordinate
	 * 
	 * @return This instance
	 */
	_Pos4 xyzw(float x, float y, float z, float w);

	/**
	 * Set the coordinates of this instance.
	 * 
	 * @param xyzw
	 * A {@code float} array containing at least 4 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 4 elements
	 */
	_Pos4 xyzw(float[] xyzw);

	Pos4 pos4();

}
