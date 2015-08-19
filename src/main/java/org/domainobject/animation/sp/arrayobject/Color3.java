package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;
import static org.domainobject.animation.sp.arrayobject.Vertex.*;

import org.domainobject.animation.sp.util.Array;

/**
 * A 3-component vertex class suitable for specifying colors without an alpha
 * component.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Color3 extends ArrayObject implements _Color3 {

	public static final int COMPONENT_COUNT = 3;

	public static NonIndexedMemory<Color3> allocate(int maxNumObjects)
	{
		return new NonIndexedMemory<Color3>(new Color3[maxNumObjects], COMPONENT_COUNT) {
			@Override
			Color3 construct(float[] raw, int offset)
			{
				return new Color3(raw, offset);
			}
		};
	}


	public Color3()
	{
		super();
	}

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
	 * Copy the coordinates of the specified {@code Color3} to this instance.
	 * 
	 * @param other
	 */
	public void set(Color3 other)
	{
		memcpy3(components, offset, other.components, other.offset);
	}


	public Color3 rgb(float red, float green, float blue)
	{
		Array.set3(components, offset, red, green, blue);
		return this;
	}


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
	 * {@link #globalAlpha} for the alpha channel. Useful for converting a
	 * 3-component color to a 4-component color.
	 */
	public float[] rgba()
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = globalAlpha;
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


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Color3 other = (Color3) obj;
		return same3(components, offset, other.components, other.offset);
	}


	@Override
	public int hashCode()
	{
		return hash3(components, offset);
	}

}
