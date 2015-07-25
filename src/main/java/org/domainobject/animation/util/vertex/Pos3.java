package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.C2J.*;

/**
 * A 3-component vertex class suitable for specifying 3D coordinates.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Pos3 extends TypedVertex {

	public static final int COMPONENT_COUNT = 3;


	Pos3()
	{

	}


	@Override
	protected int size()
	{
		return COMPONENT_COUNT;
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos3}.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void set(float x, float y, float z)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		components[offset + 2] = z;
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos3}.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public void set(float[] coordinates)
	{
		memcpy3(components, offset, coordinates, 0);
	}


	/**
	 * Copy the coordinates of the specified {@code Pos3} to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos3 other)
	{
		memcpy3(components, offset, other.components, other.offset);
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos3} and return it.
	 * 
	 * @return This {@code Pos3}
	 */
	public Pos3 xyz(float x, float y, float z)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos3} and return it.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos3 xyz(float[] coordinates)
	{
		memcpy3(components, offset, coordinates, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified {@code Pos3} to this instance and
	 * return it.
	 * 
	 * @param other
	 */
	public Pos3 xyz(Pos3 other)
	{
		memcpy3(components, offset, other.components, other.offset);
		return this;
	}


	/**
	 * Set the x and y coordinates of this {@code Pos3}.
	 * 
	 * @return This {@code Pos3}
	 */
	public Pos3 xy(float x, float y)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the x coordinate of this {@code Pos3}.
	 * 
	 * @return This {@code Pos3}
	 */
	public Pos3 x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate of this {@code Pos3}.
	 * 
	 * @return This {@code Pos3}
	 */
	public Pos3 y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate of this {@code Pos3}.
	 * 
	 * @return This {@code Pos3}
	 */
	public Pos3 z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Get x, y, z and w coordinates, using the value of {@link #DEFAULT_W} for
	 * the w coordinate.
	 * 
	 * @return The x, y, z and w coordinates of this {@code Pos3}
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = DEFAULT_W;
		return result;
	}


	/**
	 * Get x, y, z and w coordinates, using the specified value for the w
	 * coordinate.
	 * 
	 * @param w
	 *            The value to use for the w coordinate.
	 * 
	 * @return The x, y, z and w coordinates of this {@code Pos3}
	 * 
	 */
	public float[] xyzw(float w)
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = w;
		return result;
	}


	/**
	 * Get the x, y and z coordinates of this {@code Pos3}.
	 */
	public float[] xyz()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x coordinate of this {@code Pos3}.
	 */
	public float x()
	{
		return components[offset + 0];
	}


	/**
	 * Get the y coordinate of this {@code Pos3}.
	 */
	public float y()
	{
		return components[offset + 1];
	}


	/**
	 * Get the z coordinate of this {@code Pos3}.
	 */
	public float z()
	{
		return components[offset + 2];
	}

}
