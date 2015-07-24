package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.C2J.*;

/**
 * A {@code Vertex} class that lets you specify either a position or a color.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Color extends FixedVertex {

	public static final int COMPONENT_COUNT = 3;


	Color()
	{

	}


	@Override
	int getComponentCount()
	{
		return COMPONENT_COUNT;
	}


	/**
	 * Set the x, y and z coordinate.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color xyz(float x, float y, float z)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the x and y coordinate.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color xy(float x, float y)
	{
		components[offset + 0] = x;
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the x and z coordinate.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color xz(float x, float z)
	{
		components[offset + 0] = x;
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the y and z coordinate.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color yz(float y, float z)
	{
		components[offset + 1] = y;
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the x coordinate.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Get the x, y and z coordinate.
	 */
	public float[] xyz()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x, y, z and w coordinate. The value of w will be
	 * {@link #DEFAULT_W}.
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = DEFAULT_W;
		return result;
	}


	/**
	 * Get the x, y, z and w coordinate, using the specified w coordinate.
	 */
	public float[] xyzw(float w)
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = w;
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
	 * Set the red, green and blue channel.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color rgb(float red, float green, float blue)
	{
		components[offset + 0] = red;
		components[offset + 1] = green;
		components[offset + 2] = blue;
		return this;

	}


	/**
	 * Set the red channel.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color red(float r)
	{
		components[offset + 0] = r;
		return this;
	}


	/**
	 * Set the green channel.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color green(float g)
	{
		components[offset + 1] = g;
		return this;
	}


	/**
	 * Set the blue channel.
	 * 
	 * @return This {@code Vec3} instance
	 */
	public Color blue(float b)
	{
		components[offset + 2] = b;
		return this;
	}


	/**
	 * Get the red, green and blue channel.
	 */
	public float[] rgb()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the red, green, blue and alpha channel. The value of the alpha
	 * channel will be {@link #DEFAULT_A}.
	 */
	public float[] rgba()
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = DEFAULT_A;
		return result;
	}


	/**
	 * Get the red, green, blue and alpha channel, using the specified value for
	 * the alpha channel.
	 */
	public float[] rgba(float alpha)
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = alpha;
		return result;
	}


	/**
	 * Get the red channel.
	 */
	public float red()
	{
		return components[offset + 0];
	}


	/**
	 * Get the green channel.
	 */
	public float green()
	{
		return components[offset + 1];
	}


	/**
	 * Get the blue channel.
	 */
	public float blue()
	{
		return components[offset + 2];
	}

}
