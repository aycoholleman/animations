package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.C2J.*;

/**
 * A 3-component vertex class whose name and method names suggest you are
 * dealing with the 3D position of a vertex.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Pos3D extends FixedVertex {

	public static final int COMPONENT_COUNT = 3;


	Pos3D()
	{

	}


	@Override
	int getComponentCount()
	{
		return COMPONENT_COUNT;
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos3D}.
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
	 * Set the x, y, and z coordinates of this {@code Pos3D}.
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
	 * Copy the coordinates of the specified {@code Pos3D} to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos3D other)
	{
		memcpy3(components, offset, other.components, other.offset);
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos3D} and return it.
	 * 
	 * @return This {@code Pos3D}
	 */
	public Pos3D xyz(float x, float y, float z)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos3D} and return it.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos3D xyz(float[] coordinates)
	{
		memcpy3(components, offset, coordinates, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified {@code Pos3D} to this instance and
	 * return it.
	 * 
	 * @param other
	 */
	public Pos3D xyz(Pos3D other)
	{
		memcpy3(components, offset, other.components, other.offset);
		return this;
	}


	/**
	 * Set the x and y coordinates of this {@code Pos3D}.
	 * 
	 * @return This {@code Pos3D}
	 */
	public Pos3D xy(float x, float y)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the x coordinate of this {@code Pos3D}.
	 * 
	 * @return This {@code Pos3D}
	 */
	public Pos3D x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate of this {@code Pos3D}.
	 * 
	 * @return This {@code Pos3D}
	 */
	public Pos3D y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate of this {@code Pos3D}.
	 * 
	 * @return This {@code Pos3D}
	 */
	public Pos3D z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Get the x, y, z and w coordinates of this {@code Pos3D}, using the value
	 * of {@link #DEFAULT_W} as the w coordinate.
	 * 
	 * @return The x, y, z and w coordinates of this {@code Pos3D}
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = DEFAULT_W;
		return result;
	}


	/**
	 * Get the x, y, z and w coordinates of this {@code Pos3D}, using the
	 * specified value as the w coordinate.
	 * 
	 * @param w
	 *            The value to use for the w coordinate.
	 * 
	 * @return The x, y, z and w coordinates of this {@code Pos3D}
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
	 * Get the x, y and z coordinates of this {@code Pos3D}.
	 */
	public float[] xyz()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x coordinate of this {@code Pos3D}.
	 */
	public float x()
	{
		return components[offset + 0];
	}


	/**
	 * Get the y coordinate of this {@code Pos3D}.
	 */
	public float y()
	{
		return components[offset + 1];
	}


	/**
	 * Get the z coordinate of this {@code Pos3D}.
	 */
	public float z()
	{
		return components[offset + 2];
	}

}
