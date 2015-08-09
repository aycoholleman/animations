package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;

import org.domainobject.animation.sp.util.Array;
import static org.domainobject.animation.sp.util.Comparators.*;

/**
 * An 9-component vertex class suitable for specifying a position (first four
 * slots) a normal (next three slots) and two texture coordinates (last two
 * slots).
 * 
 * @author Ayco Holleman
 */
public final class Pos4NormalTexture extends Vertex implements _Pos4, _Normal {

	public static Pos4NormalTexture create()
	{
		return allocate(1).newInstance();
	}

	public static Memory<Pos4NormalTexture> allocate(int maxNumObjects)
	{
		return new Memory<Pos4NormalTexture>(new Pos4NormalTexture[maxNumObjects], NUM_COMPONENTS) {
			@Override
			Pos4NormalTexture construct(float[] raw, int offset)
			{
				return new Pos4NormalTexture(raw, offset);
			}
		};
	}

	/**
	 * The number of elements of the internal array (9).
	 */
	public static final int NUM_COMPONENTS = 9;
	/**
	 * The number of bytes occupied by the internal array.
	 */
	public static final int BYTE_SIZE = NUM_COMPONENTS * SIZE_OF_FLOAT;
	/**
	 * The byte offsets of the component groups within the array.
	 */
	public static final int[] strides = new int[] { 0, sizeof(4), sizeof(3) };


	private final Pos4 pos4;
	private final Normal normal;

	private Pos4NormalTexture(float[] raw, int offset)
	{
		super(raw, offset);
		pos4 = new Pos4(raw, offset);
		normal = new Normal(raw, offset + Pos4.COMPONENT_COUNT);
	}


	@Override
	int size()
	{
		return NUM_COMPONENTS;
	}


	///////////////////////////////////
	// SETTERS
	///////////////////////////////////

	/**
	 * Initialize this instance. The x, y and z coordinates are set to 0, as are
	 * the red, green and blue channels. The w coordinate is set to
	 * {@link Vertex#defaultW} and the alpha channel is set to
	 * {@link Vertex#defaultAlpha}. Note that until you call this method or one
	 * of the other setters, the internal state of a new instance is undefined,
	 * both in theory and in practice!
	 */
	public void init()
	{
		components[offset + 0] = 0;
		components[offset + 1] = 0;
		components[offset + 2] = 0;
		components[offset + 3] = defaultW;
		components[offset + 4] = 0;
		components[offset + 5] = 0;
		components[offset + 6] = 0;
		components[offset + 7] = 0;
		components[offset + 8] = 0;
	}


	/**
	 * Set all components of this instance.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 * @param nx
	 * @param ny
	 * @param nz
	 * @param s
	 * @param t
	 */
	public void set(float x, float y, float z, float w, float nx, float ny, float nz, float s, float t)
	{
		Array.set4(components, offset, x, y, z, w);
		Array.set3(components, offset + 4, nx, ny, nz);
		Array.set2(components, offset + 7, s, t);
	}


	/**
	 * Set all components of this instance.
	 * 
	 * @param components
	 */
	public void set(float[] components)
	{
		memcpy10(this.components, offset, components, 0);
	}


	/**
	 * Copy the components of the specified instance to this instance.
	 * 
	 * @param other
	 */
	public void set(Pos4NormalTexture other)
	{
		memcpy9(components, offset, other.components, other.offset);
	}


	/**
	 * Set the x, y, z and w coordinate.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 * @return This instance
	 */
	public Pos4NormalTexture xyzw(float x, float y, float z, float w)
	{
		Array.set4(components, offset, x, y, z, w);
		return this;
	}


	/**
	 * Set the x, y, z and w coordinate.
	 * 
	 * @param xyzw
	 *        A {@code float} array containing at least 3 elements
	 * @return This instance
	 * @throws ArrayIndexOutOfBoundsException
	 *         If the specified array contains less than 3 elements
	 */
	public Pos4NormalTexture xyzw(float[] xyzw)
	{
		memcpy4(components, offset, xyzw, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified instance to this instance.
	 * 
	 * @param pos4
	 * @return This instance
	 */
	public Pos4NormalTexture xyzw(Pos4 pos4)
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
	 * @return This instance
	 */
	public Pos4NormalTexture xyz(float x, float y, float z)
	{
		Array.set3(components, offset, x, y, z);
		return this;
	}


	/**
	 * Set the x, y, and z coordinate.
	 * 
	 * @param xyz
	 *        A {@code float} array containing at least 3 elements
	 * @return This instance
	 * @throws ArrayIndexOutOfBoundsException
	 *         If the specified array contains less than 3 elements
	 */
	public Pos4NormalTexture xyz(float[] xyz)
	{
		memcpy3(components, offset, xyz, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified instance to this instance.
	 * 
	 * @param pos3
	 * @return This instance
	 */
	public Pos4NormalTexture xyz(Pos3 pos3)
	{
		memcpy3(components, offset, pos3.components, pos3.offset);
		return this;
	}


	/**
	 * Set the normal's x, y and z coordinate.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return This instance
	 */
	public Pos4NormalTexture normal(float x, float y, float z)
	{
		Array.set3(components, offset + 4, x, y, z);
		return this;
	}


	/**
	 * Set the normal's x, y, and z coordinate.
	 * 
	 * @param xyz
	 *        A {@code float} array containing at least 3 elements
	 * @return This instance
	 * @throws ArrayIndexOutOfBoundsException
	 *         If the specified array contains less than 3 elements
	 */
	public Pos4NormalTexture normal(float[] xyz)
	{
		memcpy3(components, offset + 4, xyz, 0);
		return this;
	}


	/**
	 * Set texture coordinates
	 * 
	 * @param s
	 *        The s texel
	 * @param t
	 *        The t texel
	 * @return This instance
	 */
	public Pos4NormalTexture st(float s, float t)
	{
		Array.set2(components, 7, s, t);
		return this;
	}


	/**
	 * Set texture coordinates
	 * 
	 * @param s
	 *        The s texel
	 * @param t
	 *        The t texel
	 * @return This instance
	 */
	public Pos4NormalTexture st(float[] st)
	{
		memcpy2(components, offset + 7, st, 0);
		return this;
	}


	/**
	 * Set the x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture z(float z)
	{
		components[offset + 2] = z;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture w(float w)
	{
		components[offset + 3] = w;
		return this;
	}


	/**
	 * Set the normal's x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture nx(float nx)
	{
		components[offset + 4] = nx;
		return this;
	}


	/**
	 * Set the normal's y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture ny(float ny)
	{
		components[offset + 5] = ny;
		return this;
	}


	/**
	 * Set the normal's z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture nz(float b)
	{
		components[offset + 6] = b;
		return this;
	}


	/**
	 * Set the texture's s coordinate
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture s(float s)
	{
		components[offset + 7] = s;
		return this;
	}


	/**
	 * Set the texture's t coordinate
	 * 
	 * @return This instance
	 */
	public Pos4NormalTexture t(float t)
	{
		components[offset + 8] = t;
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
		float[] result = new float[9];
		memcpy9(result, 0, components, offset);
		return result;
	}


	///////////////////////////////////
	// MISC
	///////////////////////////////////

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Pos4NormalTexture))
			return false;
		Pos4NormalTexture you = (Pos4NormalTexture) obj;
		return same4(components, offset, you.components, you.offset);
	}

	@Override
	public int hashCode()
	{
		return 0;
	};

	@Override
	public Pos4 pos4()
	{
		return pos4;
	}

	@Override
	public Normal normal()
	{
		return normal;
	}

}
