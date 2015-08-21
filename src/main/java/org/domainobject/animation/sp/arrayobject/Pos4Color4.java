package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;

/**
 * An 8-component vertex class suitable for specifying a position (first four
 * slots) and a color (last four slots).
 * 
 * @author Ayco Holleman
 * @created Jul 26, 2015
 *
 */
public final class Pos4Color4 extends Vertex implements _Pos4 {

	public static NonIndexedMemory<Pos4Color4> allocate(int maxNumObjects)
	{
		return new NonIndexedMemory<Pos4Color4>(new Pos4Color4[maxNumObjects], COMPONENT_COUNT) {
			@Override
			Pos4Color4 construct(float[] raw, int offset)
			{
				return new Pos4Color4(raw, offset);
			}
		};
	}

	public static final int COMPONENT_COUNT = 8;
	public static final int BYTE_SIZE = COMPONENT_COUNT * SIZE_OF_FLOAT;
	public static final int[] strides = new int[] { 0, sizeof(4) };


	private Pos4Color4(float[] components, int offset)
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
	 * Initialize this instance. The x, y and z coordinates are set to 0, as are
	 * the red, green and blue channels. The w coordinate is set to
	 * {@link Vertex#globalW} and the alpha channel is set to
	 * {@link Vertex#globalAlpha}. Note that until you call this method or one
	 * of the other setters, the internal state of a new instance is undefined,
	 * both in theory and in practice!
	 */
	public void init()
	{
		components[offset + 0] = 0;
		components[offset + 1] = 0;
		components[offset + 2] = 0;
		components[offset + 3] = globalW;
		components[offset + 4] = 0;
		components[offset + 5] = 0;
		components[offset + 6] = 0;
		components[offset + 7] = globalAlpha;
	}


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
	public void set(float x, float y, float z, float w, float r, float g, float b, float a)
	{
		Array.set4(components, offset, x, y, z, w);
		Array.set4(components, offset + 4, r, g, b, a);
	}


	/**
	 * Set all components of this {@code Array}.
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
		Array.set4(components, offset, x, y, z, w);
		return this;
	}


	/**
	 * Set the x, y, z and w coordinate.
	 * 
	 * @param xyzw
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
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
		Array.set3(components, offset, x, y, z);
		return this;
	}


	/**
	 * Set the x, y, and z coordinate.
	 * 
	 * @param xyz
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
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
	 * Set the red, green, blue and alpha channel.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 * 
	 * @return This instance
	 */
	public Pos4Color4 rgba(float red, float green, float blue, float alpha)
	{
		Array.set4(components, offset + 4, red, green, blue, alpha);
		return this;
	}


	/**
	 * Set the red, green, blue and alpha channel.
	 * 
	 * @param rgba
	 * A {@code float} array containing at least 4 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 4 elements
	 */
	public Pos4Color4 rgba(float[] rgba)
	{
		memcpy4(components, offset + 4, rgba, 0);
		return this;
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 rgb(float red, float green, float blue)
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
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 w(float w)
	{
		components[offset + 3] = w;
		return this;
	}


	/**
	 * Set the red channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 red(float r)
	{
		components[offset + 4] = r;
		return this;
	}


	/**
	 * Set the green channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 green(float g)
	{
		components[offset + 5] = g;
		return this;
	}


	/**
	 * Set the blue channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 blue(float b)
	{
		components[offset + 6] = b;
		return this;
	}


	/**
	 * Set the alpha channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4 alpha(float a)
	{
		components[offset + 7] = a;
		return this;
	}


	///////////////////////////////////
	// GETTERS
	///////////////////////////////////

	/**
	 * Get a copy of this instance's components.
	 * 
	 * @return xyzwrgba
	 */
	public float[] copy()
	{
		float[] result = new float[8];
		memcpy8(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the x, y, z and w coordinate.
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy4(result, 0, components, offset);
		return result;
	}


	/**
	 * Get the red, green, blue and alpha channel.
	 */
	public float[] rgba()
	{
		float[] result = new float[4];
		memcpy4(result, 0, components, offset + 4);
		return result;
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
	 * Get the red, green and blue channel.
	 */
	public float[] rgb()
	{
		float[] result = new float[3];
		memcpy3(result, 0, components, offset + 4);
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
	 * Get the w coordinate.
	 */
	public float w()
	{
		return components[offset + 3];
	}


	/**
	 * Get the red channel.
	 */
	public float red()
	{
		return components[offset + 4];
	}


	/**
	 * Get the green channel.
	 */
	public float green()
	{
		return components[offset + 5];
	}


	/**
	 * Get the blue channel.
	 */
	public float blue()
	{
		return components[offset + 6];
	}


	/**
	 * Get the alpha channel.
	 */
	public float alpha()
	{
		return components[offset + 7];
	}


	///////////////////////////////////
	// CONVERSIONS
	///////////////////////////////////

	@Override
	public Pos4 pos4()
	{
		return new Pos4(components, offset);
	}


	/**
	 * Narrow this instance to a {@link Color4} instance.
	 * 
	 * @return
	 */
	public Color4 color4()
	{
		return new Color4(components, offset + 4);
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Pos4Color4 other = (Pos4Color4) obj;
		return same4(components, offset, other.components, other.offset)
				&& same4(components, offset + 4, other.components, other.offset + 4);
	}


	@Override
	public int hashCode()
	{
		return hash8(components, offset);
	}


	@Override
	public Pos3 pos3()
	{
		return new Pos3(components, offset);
	}



	@Override
	public void copyTo(float[] array, int offset)
	{
		memcpy8(array, offset, components, this.offset);
	}


	@Override
	void copyTo(ArrayObject other)
	{
		memcpy8(other.components, other.offset, components, offset);
	}
}
