package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

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

	public Color3()
	{
		super();
	}

	Color3(float[] components, int offset)
	{
		super(components, offset);
	}

	Color3(ArrayObject embedder, int offset)
	{
		super(embedder, offset);
	}

	@Override
	int objSize()
	{
		return COMPONENT_COUNT;
	}

	public Color3 rgb(float r, float g, float b)
	{
		Array.set3(components, offset, r, g, b);
		return this;
	}

	public Color3 rgb(float[] rgb)
	{
		memcpy3(components, offset, rgb, 0);
		return this;
	}

	/**
	 * Copies the channels of the specified {@link _Color3} instance to this
	 * instance.
	 * 
	 * @param other
	 * 
	 * @return This instance
	 */
	public Color3 rgb(_Color3 other)
	{
		memcpy3(components, offset, other.color().components, other.color().offset);
		return this;
	}

	/**
	 * Set red channel.
	 * 
	 * @return This instance
	 */
	public Color3 r(float r)
	{
		components[offset + 0] = r;
		return this;
	}

	/**
	 * Set green channel.
	 * 
	 * @return This instance
	 */
	public Color3 g(float g)
	{
		components[offset + 1] = g;
		return this;
	}

	/**
	 * Set blue channel.
	 * 
	 * @return This instance
	 */
	public Color3 b(float b)
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
	 * Get red channel.
	 */
	public float r()
	{
		return components[offset + 0];
	}

	/**
	 * Get green channel.
	 */
	public float g()
	{
		return components[offset + 1];
	}

	/**
	 * Get blue channel.
	 */
	public float b()
	{
		return components[offset + 2];
	}

	@Override
	public Color3 color()
	{
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Color3 other = ((_Color3) obj).color();
		return same3(components, offset, other.components, other.offset);
	}

	@Override
	public int hashCode()
	{
		return hash3(components, offset);
	}

	@Override
	public void copyTo(float[] array, int offset)
	{
		memcpy3(array, offset, components, this.offset);
	}

	@Override
	void copyTo(ArrayObject other)
	{
		memcpy3(other.components, other.offset, components, offset);
	}

}
