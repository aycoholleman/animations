package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.arrayobject.Vertex.*;
import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;

/**
 * A 4-component vertex class suitable for specifying colors.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 */
public class Color4 extends ArrayObject implements _Color4 {

	public static final int COMPONENT_COUNT = 4;

	public static NonIndexedMemory<Color4> allocate(int maxNumObjects)
	{
		return new NonIndexedMemory<Color4>(new Color4[maxNumObjects], COMPONENT_COUNT) {
			@Override
			Color4 construct(float[] raw, int offset)
			{
				return new Color4(raw, offset);
			}
		};
	}

	public Color4()
	{
		super();
	}

	Color4(float[] components, int offset)
	{
		super(components, offset);
	}

	@Override
	int size()
	{
		return COMPONENT_COUNT;
	}


	/**
	 * Set the red, green, blue and alpha channel.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void set(float red, float green, float blue, float alpha)
	{
		Array.set4(components, offset, red, green, blue, alpha);
	}


	/**
	 * Set the red, green, blue and alpha channel.
	 * 
	 * @param rgba
	 * A {@code float} array containing at least 4 elements
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 4 elements
	 */
	public void set(float[] rgba)
	{
		memcpy4(components, offset, rgba, 0);
	}


	/**
	 * Copy the color channels of the specified {@code Color4} to this instance.
	 * 
	 * @param other
	 */
	public void set(Color4 other)
	{
		memcpy4(components, offset, other.components, other.offset);
	}


	@Override
	public Color4 rgba(float red, float green, float blue, float alpha)
	{
		Array.set4(components, offset, red, green, blue, alpha);
		return this;
	}

	@Override
	public Color4 rgba(float[] rgba)
	{
		memcpy4(components, offset, rgba, 0);
		return this;
	}

	@Override
	public Color4 rgb(float red, float green, float blue)
	{
		Array.set3(components, offset, red, green, blue);
		return this;
	}


	@Override
	public Color4 rgb(float[] rgb)
	{
		memcpy3(components, offset, rgb, 0);
		return this;
	}


	/**
	 * Set the red channel.
	 * 
	 * @return This instance
	 */
	public Color4 red(float r)
	{
		components[offset + 0] = r;
		return this;
	}


	/**
	 * Set the green channel.
	 * 
	 * @return This instance
	 */
	public Color4 green(float g)
	{
		components[offset + 1] = g;
		return this;
	}


	/**
	 * Set the blue channel.
	 * 
	 * @return This instance
	 */
	public Color4 blue(float b)
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
	 * {@link #globalAlpha} for the alpha channel.
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
	 * alpha channel.
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
		Color4 other = (Color4) obj;
		return same4(components, offset, other.components, other.offset);
	}

	@Override
	public int hashCode()
	{
		return hash4(components, offset);
	}

	@Override
	public void copyTo(float[] array, int offset)
	{
		memcpy4(array, offset, components, this.offset);
	}

	@Override
	void copyTo(ArrayObject other)
	{
		memcpy4(other.components, other.offset, components, offset);
	}

}
