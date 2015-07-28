package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.C2JSinglePrecision.*;

import org.domainobject.animation.util.Array;

/**
 * An 8-component vertex class suitable for specifying a position (first four
 * slots) and a color (last four slots).
 * 
 * @author Ayco Holleman
 * @created Jul 26, 2015
 *
 */
public class Pos4Color4 extends TypedVertex {

	public static final int COMPONENT_COUNT = 8;


	Pos4Color4(float[] components, int offset)
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
	 * Set all components of this {@code Vertex}.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param r
	 * @param g
	 * @param b
	 */
	public void set(float x, float y, float z, float w, float r, float g, float b, float a)
	{
		Array.set(components, offset, x, y, z, w);
		Array.set(components, offset + 4, r, g, b, a);
	}


	/**
	 * Set all components of this {@code Vertex}.
	 * 
	 * @param xyzrgb
	 */
	public void set(float[] xyzwrgba)
	{
		memcpy8(components, offset, xyzwrgba, 0);
	}


	/**
	 * Copy the components of the specified instance to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos4Color4 other)
	{
		memcpy8(components, offset, other.components, other.offset);
	}


	/**
	 * Set the x, y, z and w coordinate.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 * 
	 * @return This instance
	 */
	public Pos4Color4 xyzw(float x, float y, float z, float w)
	{
		Array.set(components, offset, x, y, z, w);
		return this;
	}


	/**
	 * Set the x, y, z and w coordinate.
	 * 
	 * @param xyzw
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos4Color4 xyzw(float[] xyzw)
	{
		memcpy4(components, offset, xyzw, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified instance to this instance.
	 * 
	 * @param pos4
	 * 
	 * @return This instance
	 */
	public Pos4Color4 xyzw(Pos4 pos4)
	{
		memcpy4(components, offset, pos4.components, pos4.offset);
		return this;
	}


	/**
	 * Set the x, y and z coordinate.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * 
	 * @return This instance
	 */
	public Pos4Color4 xyz(float x, float y, float z)
	{
		Array.set(components, offset, x, y, z);
		return this;
	}


	/**
	 * Set the x, y, and z coordinate.
	 * 
	 * @param xyz
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos4Color4 xyz(float[] xyz)
	{
		memcpy3(components, offset, xyz, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified instance to this instance.
	 * 
	 * @param pos3
	 * 
	 * @return This instance
	 */
	public Pos4Color4 xyz(Pos3 pos3)
	{
		memcpy3(components, offset, pos3.components, pos3.offset);
		return this;
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 rgb(float red, float green, float blue)
	{
		Array.set(components, offset + 3, red, green, blue);
		return this;
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @param rgb
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos4Color4 rgb(float[] rgb)
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
	public Pos4Color4 rgb(Color3 color3)
	{
		memcpy3(components, offset, color3.components, color3.offset);
		return this;
	}


	/**
	 * Set the x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the red channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 red(float r)
	{
		components[offset + 3] = r;
		return this;
	}


	/**
	 * Set the green channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 green(float g)
	{
		components[offset + 4] = g;
		return this;
	}


	/**
	 * Set the blue channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 blue(float b)
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

}
