package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;

/**
 * A 6-component vertex class suitable for specifying a position (first 3 slots)
 * and a color (last three slots).
 * 
 * @author Ayco Holleman
 * @created Jul 26, 2015
 *
 */
public class Pos3Color3 extends Vertex {

	public static final int COMPONENT_COUNT = 6;


	Pos3Color3(float[] components, int offset)
	{
		super(components, offset);
	}


	@Override
	int size()
	{
		return COMPONENT_COUNT;
	}


	///////////////////////////////////
	// SETTERS
	///////////////////////////////////

	/**
	 * Set all components of this {@code Array}.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param r
	 * @param g
	 * @param b
	 */
	public void set(float x, float y, float z, float r, float g, float b)
	{
		Array.set6(components, offset, x, y, z, r, g, b);
	}


	/**
	 * Set all components of this {@code Array}.
	 * 
	 * @param xyzrgb
	 */
	public void set(float[] xyzrgb)
	{
		memcpy6(components, offset, xyzrgb, 0);
	}


	/**
	 * Copy the components of the specified instance to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos3Color3 other)
	{
		memcpy6(components, offset, other.components, other.offset);
	}


	/**
	 * Set the x, y, and z coordinates of this instance.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * 
	 * @return This instance
	 */
	public Pos3Color3 xyz(float x, float y, float z)
	{
		Array.set3(components, offset, x, y, z);
		return this;
	}


	/**
	 * Set the x, y, and z coordinates of this instance.
	 * 
	 * @param xyz
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
	 */
	public Pos3Color3 xyz(float[] xyz)
	{
		memcpy3(components, offset, xyz, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified {@link Pos3} instance to this
	 * instance.
	 * 
	 * @param pos3
	 * 
	 * @return This instance
	 */
	public Pos3Color3 xyz(Pos3 pos3)
	{
		memcpy3(components, offset, pos3.components, pos3.offset);
		return this;
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @return This instance
	 */
	public Pos3Color3 rgb(float red, float green, float blue)
	{
		Array.set3(components, offset + 3, red, green, blue);
		return this;
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @param rgb
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
	 */
	public Pos3Color3 rgb(float[] rgb)
	{
		memcpy3(components, offset + 3, rgb, 0);
		return this;
	}


	/**
	 * Copy the channels of the specified {@link Color3} instance to this
	 * instance.
	 * 
	 * @param color3
	 * 
	 * @return This instance
	 */
	public Pos3Color3 rgb(Color3 color3)
	{
		memcpy3(components, offset, color3.components, color3.offset);
		return this;
	}


	/**
	 * Set the x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos3Color3 x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos3Color3 y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos3Color3 z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the red channel.
	 * 
	 * @return This instance
	 */
	public Pos3Color3 red(float r)
	{
		components[offset + 3] = r;
		return this;
	}


	/**
	 * Set the green channel.
	 * 
	 * @return This instance
	 */
	public Pos3Color3 green(float g)
	{
		components[offset + 4] = g;
		return this;
	}


	/**
	 * Set the blue channel.
	 * 
	 * @return This instance
	 */
	public Pos3Color3 blue(float b)
	{
		components[offset + 5] = b;
		return this;
	}


	///////////////////////////////////
	// GETTERS
	///////////////////////////////////

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
	 * Get the red, green and blue channel.
	 */
	public float[] rgb()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset + 3);
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
	 * Get the red channel.
	 */
	public float red()
	{
		return components[offset + 3];
	}


	/**
	 * Get the green channel.
	 */
	public float green()
	{
		return components[offset + 4];
	}


	/**
	 * Get the blue channel.
	 */
	public float blue()
	{
		return components[offset + 5];
	}


	///////////////////////////////////
	// CONVERSIONS
	///////////////////////////////////

	/**
	 * Narrow this instance to a {@link Pos3} instance.
	 * 
	 * @return
	 */
	public Pos3 position()
	{
		return new Pos3(components, offset);
	}


	/**
	 * Narrow this instance to a {@link Color3} instance.
	 * 
	 * @return
	 */
	public Color3 color()
	{
		return new Color3(components, offset + 3);
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Pos3Color3 other = (Pos3Color3) obj;
		return same3(components, offset, other.components, other.offset)
				&& same3(components, offset + 3, other.components, other.offset + 3);
	}

}
