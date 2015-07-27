package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.C2J.*;

import org.domainobject.animation.util.Array;

/**
 * A 3-component vertex class suitable for specifying colors without an alpha
 * component.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Color3 extends TypedVertex {

	public static final int COMPONENT_COUNT = 3;


	Color3(float[] components, int offset)
	{
		super(components, offset);
	}


	@Override
	int size()
	{
		return COMPONENT_COUNT;
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void set(float red, float green, float blue)
	{
		Array.set(components, offset, red, green, blue);
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @param rgb
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public void set(float[] rgb)
	{
		memcpy3(components, offset, rgb, 0);
	}


	/**
	 * Copy the coordinates of the specified {@code Color3} to this instance.
	 * 
	 * @param other
	 */
	public void set(Color3 other)
	{
		memcpy3(components, offset, other.components, other.offset);
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @return This instance
	 */
	public Color3 rgb(float red, float green, float blue)
	{
		Array.set(components, offset, red, green, blue);
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
	public Color3 rgb(float[] rgb)
	{
		memcpy3(components, offset, rgb, 0);
		return this;
	}


	/**
	 * Copy the channels of the specified instance to this instance.
	 * 
	 * @param other
	 * 
	 * @return This instance
	 */
	public Color3 rgb(Color3 other)
	{
		memcpy3(components, offset, other.components, other.offset);
		return this;
	}


	/**
	 * Set the red channel.
	 * 
	 * @return This instance
	 */
	public Color3 red(float r)
	{
		components[offset + 0] = r;
		return this;
	}


	/**
	 * Set the green channel.
	 * 
	 * @return This instance
	 */
	public Color3 green(float g)
	{
		components[offset + 1] = g;
		return this;
	}


	/**
	 * Set the blue channel.
	 * 
	 * @return This instance
	 */
	public Color3 blue(float b)
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
	 * Get red, green, blue and alpha channels, using the value of
	 * {@link #DEFAULT_ALPHA} for the alpha channel. Useful for converting a
	 * 3-component color to a 4-component color.
	 */
	public float[] rgba()
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = DEFAULT_ALPHA;
		return result;
	}


	/**
	 * Get red, green, blue and alpha channel, using the specified value for the
	 * alpha channel. Useful for converting a 3-component color to a 4-component
	 * color.
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
