package org.domainobject.animation.sp.arrayobject;

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

	public Color4()
	{
		super();
	}

	Color4(float[] components, int offset)
	{
		super(components, offset);
	}

	Color4(ArrayObject embedder, int offset)
	{
		super(embedder, offset);
	}

	@Override
	int objSize()
	{
		return COMPONENT_COUNT;
	}

	public Color4 rgba(float red, float green, float blue, float alpha)
	{
		Array.set4(components, offset, red, green, blue, alpha);
		return this;
	}

	public Color4 rgba(float[] rgba)
	{
		memcpy4(components, offset, rgba, 0);
		return this;
	}

	public Color4 rgba(_Color4 other)
	{
		memcpy4(components, offset, other.color().components, other.color().offset);
		return this;
	}

	public Color4 rgb(float r, float g, float b)
	{
		Array.set4(components, offset, r, g, b, Vertex.globalAlpha);
		return this;
	}

	public Color4 rgb(float[] rgb)
	{
		memcpy3(components, offset, rgb, 0);
		return a(Vertex.globalAlpha);
	}

	/**
	 * Set red channel.
	 * 
	 * @return This instance
	 */
	public Color4 r(float r)
	{
		components[offset + 0] = r;
		return this;
	}

	/**
	 * Set green channel.
	 * 
	 * @return This instance
	 */
	public Color4 g(float g)
	{
		components[offset + 1] = g;
		return this;
	}

	/**
	 * Set blue channel.
	 * 
	 * @return This instance
	 */
	public Color4 b(float b)
	{
		components[offset + 2] = b;
		return this;
	}

	/**
	 * Set alpha channel.
	 * 
	 * @return This instance
	 */
	public Color4 a(float a)
	{
		components[offset + 3] = a;
		return this;
	}

	/**
	 * Get red, green, blue and alpha channel
	 */
	public float[] rgba()
	{
		float[] result = new float[4];
		memcpy4(result, 0, components, offset);
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

	/**
	 * Get alpha channel.
	 */
	public float a()
	{
		return components[offset + 3];
	}

	@Override
	public Color4 color()
	{
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Color4 other = ((_Color4) obj).color();
		return same4(components, offset, other.components, other.offset);
	}

	@Override
	public int hashCode()
	{
		return hash4(components, offset);
	}

	@Override
	public void copyTo(float[] target, int offset)
	{
		memcpy4(target, offset, components, this.offset);
	}

	@Override
	void copyTo(ArrayObject other)
	{
		memcpy4(other.components, other.offset, components, offset);
	}

}
