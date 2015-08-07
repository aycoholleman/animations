package org.domainobject.animation.sp.util.vertex;

import static org.domainobject.animation.sp.util.C2JSP.*;

import org.domainobject.animation.sp.util.Array;

/**
 * An 10-component vertex class suitable for specifying a position (first four
 * slots) a color (next four slots) and two texture coordinates (last two
 * slots).
 * 
 * @author Ayco Holleman
 * @created Jul 26, 2015
 *
 */
public class Pos4Color4Texture extends TypedVertex {

	public static final Memory<Pos4Color4Texture> allocate(int maxNumObjects)
	{
		return new Memory<Pos4Color4Texture>(new Pos4Color4Texture[maxNumObjects], SIZE) {
			@Override
			Pos4Color4Texture construct(float[] raw, int offset)
			{
				return new Pos4Color4Texture(raw, offset);
			}
		};
	}

	/**
	 * The number of elements of the internal array (10).
	 */
	public static final int SIZE = 10;
	/**
	 * The number of bytes occupied by the internal array.
	 */
	public static final int BYTE_SIZE = SIZE * SIZE_OF_FLOAT;
	/**
	 * The start points (in bytes) of the component groups within the array.
	 */
	public static final int[] strides = new int[] { 0, sizeof(4), sizeof(4) };


	private Pos4Color4Texture(float[] raw, int offset)
	{
		super(raw, offset);
	}


	@Override
	int size()
	{
		return SIZE;
	}


	///////////////////////////////////
	// SETTERS
	///////////////////////////////////

	/**
	 * Initialize this instance. The x, y and z coordinates are set to 0, as are
	 * the red, green and blue channels. The w coordinate is set to
	 * {@link TypedVertex#DEFAULT_W} and the alpha channel is set to
	 * {@link TypedVertex#DEFAULT_ALPHA}. Note that until you call this method
	 * or one of the other setters, the internal state of a new instance is
	 * undefined, both in theory and in practice!
	 */
	public void init()
	{
		components[offset + 0] = 0;
		components[offset + 1] = 0;
		components[offset + 2] = 0;
		components[offset + 3] = DEFAULT_W;
		components[offset + 4] = 0;
		components[offset + 5] = 0;
		components[offset + 6] = 0;
		components[offset + 7] = DEFAULT_ALPHA;
		components[offset + 8] = 0;
		components[offset + 9] = 0;
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
	 * @param s
	 * @param t
	 */
	public void set(float x, float y, float z, float w, float r, float g, float b, float a, float s, float t)
	{
		Array.set(components, offset, x, y, z, w);
		Array.set(components, offset + 4, r, g, b, a);
		Array.set(components, offset + 8, s, t);
	}


	/**
	 * Set all components of this {@code Array}.
	 * 
	 * @param xyzrgb
	 */
	public void set(float[] xyzwrgbast)
	{
		memcpy10(components, offset, xyzwrgbast, 0);
	}


	/**
	 * Copy the components of the specified instance to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos4Color4Texture other)
	{
		memcpy10(components, offset, other.components, other.offset);
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
	public Pos4Color4Texture xyzw(float x, float y, float z, float w)
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
	public Pos4Color4Texture xyzw(float[] xyzw)
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
	public Pos4Color4Texture xyzw(Pos4 pos4)
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
	public Pos4Color4Texture xyz(float x, float y, float z)
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
	public Pos4Color4Texture xyz(float[] xyz)
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
	public Pos4Color4Texture xyz(Pos3 pos3)
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
	public Pos4Color4Texture rgba(float red, float green, float blue, float alpha)
	{
		Array.set(components, offset + 4, red, green, blue, alpha);
		return this;
	}


	/**
	 * Set the red, green, blue and alpha channel.
	 * 
	 * @param rgba
	 *            A {@code float} array containing at least 4 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 4 elements
	 */
	public Pos4Color4Texture rgba(float[] rgba)
	{
		memcpy4(components, offset + 4, rgba, 0);
		return this;
	}


	/**
	 * Set the red, green and blue channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture rgb(float red, float green, float blue)
	{
		Array.set(components, offset + 4, red, green, blue);
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
	public Pos4Color4Texture rgb(float[] rgb)
	{
		memcpy3(components, offset + 4, rgb, 0);
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
	public Pos4Color4Texture rgb(Color3 color3)
	{
		memcpy3(components, offset + 4, color3.components, color3.offset);
		return this;
	}


	/**
	 * Set texture coordinates
	 * 
	 * @param s
	 *            The s texel
	 * @param t
	 *            The t texel
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture st(float s, float t)
	{
		Array.set(components, 8, s, t);
		return this;
	}


	/**
	 * Set texture coordinates
	 * 
	 * @param s
	 *            The s texel
	 * @param t
	 *            The t texel
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture st(float[] st)
	{
		memcpy2(components, offset + 8, st, 0);
		return this;
	}


	/**
	 * Set the x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture w(float w)
	{
		components[offset + 3] = w;
		return this;
	}


	/**
	 * Set the red channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture red(float r)
	{
		components[offset + 4] = r;
		return this;
	}


	/**
	 * Set the green channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture green(float g)
	{
		components[offset + 5] = g;
		return this;
	}


	/**
	 * Set the blue channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture blue(float b)
	{
		components[offset + 6] = b;
		return this;
	}


	/**
	 * Set the alpha channel.
	 * 
	 * @return This instance
	 */
	public Pos4Color4Texture alpha(float a)
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
	 * Get the texture coordinates.
	 */
	public float[] st()
	{
		float[] result = new float[2];
		memcpy2(result, 0, components, offset + 8);
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


	/**
	 * Get the s texel.
	 */
	public float s()
	{
		return components[offset + 8];
	}


	/**
	 * Get the t texel.
	 */
	public float t()
	{
		return components[offset + 9];
	}


	///////////////////////////////////
	// CONVERSIONS
	///////////////////////////////////

	/**
	 * Narrow this instance to a {@link Pos4} instance.
	 * 
	 * @return
	 */
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

}
