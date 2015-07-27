package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.C2J.*;

import org.domainobject.animation.util.Array;

/**
 * A 4-component vertex class suitable for specifying 4D coordinates.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Pos4 extends TypedVertex {

	public static final int COMPONENT_COUNT = 4;


	Pos4(float[] components, int offset)
	{
		super(components, offset);
		components[offset + 3] = DEFAULT_W;
	}


	@Override
	int size()
	{
		return COMPONENT_COUNT;
	}


	/**
	 * Set the x, y, z and w coordinates.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void set(float x, float y, float z, float w)
	{
		Array.set(components, offset, x, y, z, w);
	}


	/**
	 * Set the x, y, z and w coordinates.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 4 elements
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 4 elements
	 */
	public void set(float[] coordinates)
	{
		memcpy4(components, offset, coordinates, 0);
	}


	/**
	 * Copy the coordinates of the specified instance to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos4 other)
	{
		memcpy4(components, offset, other.components, other.offset);
	}


	/**
	 * Set the x, y, z and w coordinates and return it.
	 * 
	 * @return This instance
	 */
	public Pos4 xyzw(float x, float y, float z, float w)
	{
		Array.set(components, offset, x, y, z, w);
		return this;
	}


	/**
	 * Set the x, y, z and w coordinates and return it.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 4 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 4 elements
	 */
	public Pos4 xyzw(float[] coordinates)
	{
		memcpy4(components, offset, coordinates, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified instance to this instance.
	 * 
	 * @param other
	 */
	public Pos4 xyzw(Pos4 other)
	{
		memcpy4(components, offset, other.components, other.offset);
		return this;
	}


	/**
	 * Set the x, y, and z coordinates.
	 * 
	 * @return This instance
	 */
	public Pos4 xyz(float x, float y, float z)
	{
		Array.set(components, offset, x, y, z);
		return this;
	}


	/**
	 * Set the x, y, and z coordinates.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos4 xyz(float[] coordinates)
	{
		memcpy3(components, offset, coordinates, 0);
		return this;
	}


	/**
	 * Set the x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the w coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 w(float w)
	{
		components[offset + 3] = w;
		return this;
	}


	/**
	 * Get the x, y, z and w coordinates.
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy4(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x, y and z coordinates.
	 */
	public float[] xyz()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x coordinate.
	 */
	public float x()
	{
		return components[offset + 0];
	}


	/**
	 * Get the y coordinate.
	 */
	public float y()
	{
		return components[offset + 1];
	}


	/**
	 * Get the z coordinate.
	 */
	public float z()
	{
		return components[offset + 2];
	}


	/**
	 * Get the w coordinate.
	 */
	public float w()
	{
		return components[offset + 3];
	}

}