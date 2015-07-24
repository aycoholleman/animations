package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.C2J.*;

/**
 * A 3-component vertex class whose name and method names suggest you are
 * dealing with the 4D position of a vertex.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Pos4D extends FixedVertex {

	public static final int COMPONENT_COUNT = 4;


	Pos4D()
	{

	}


	@Override
	int getComponentCount()
	{
		return COMPONENT_COUNT;
	}


	/**
	 * Set the x, y, z and w coordinates of this {@code Pos4D}.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void set(float x, float y, float z, float w)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		components[offset + 2] = z;
		components[offset + 3] = w;
	}


	/**
	 * Set the x, y, z and w coordinates of this {@code Pos4D}.
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
	 * Copy the coordinates of the specified {@code Pos4D} to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos4D other)
	{
		memcpy4(components, offset, other.components, other.offset);
	}


	/**
	 * Set the x, y, z and w coordinates of this {@code Pos4D} and return it.
	 * 
	 * @return This {@code Pos4D}
	 */
	public Pos4D xyzw(float x, float y, float z, float w)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		components[offset + 2] = z;
		components[offset + 3] = w;
		return this;
	}


	/**
	 * Set the x, y, z and w coordinates of this {@code Pos4D} and return it.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 4 elements
	 * 
	 * @return This {@code Pos4D}
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 4 elements
	 */
	public Pos4D xyzw(float[] coordinates)
	{
		memcpy4(components, offset, coordinates, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified {@code Pos4D} to this instance.
	 * 
	 * @param other
	 */
	public Pos4D xyzw(Pos4D other)
	{
		memcpy4(components, offset, other.components, other.offset);
		return this;
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos4D}.
	 * 
	 * @return This {@code Pos4D}
	 */
	public Pos4D xyz(float x, float y, float z)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the x, y, and z coordinates of this {@code Pos4D}.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos4D xyz(float[] coordinates)
	{
		memcpy3(components, offset, coordinates, 0);
		return this;
	}


	/**
	 * Set the x coordinate of this {@code Pos4D}.
	 * 
	 * @return This {@code Pos4D}
	 */
	public Pos4D x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate of this {@code Pos4D}.
	 * 
	 * @return This {@code Pos4D}
	 */
	public Pos4D y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate of this {@code Pos4D}.
	 * 
	 * @return This {@code Pos4D}
	 */
	public Pos4D z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the w coordinate of this {@code Pos4D}.
	 * 
	 * @return This {@code Pos4D}
	 */
	public Pos4D w(float w)
	{
		components[offset + 3] = w;
		return this;
	}


	/**
	 * Get the x, y, z and w coordinates of this {@code Pos4D}.
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy4(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x, y and z coordinates of this {@code Pos4D}.
	 */
	public float[] xyz()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x coordinate of this {@code Pos4D}.
	 */
	public float x()
	{
		return components[offset + 0];
	}


	/**
	 * Get the y coordinate of this {@code Pos4D}.
	 */
	public float y()
	{
		return components[offset + 1];
	}


	/**
	 * Get the z coordinate of this {@code Pos4D}.
	 */
	public float z()
	{
		return components[offset + 2];
	}


	/**
	 * Get the w coordinate of this {@code Pos4D}.
	 */
	public float w()
	{
		return components[offset + 3];
	}

}
