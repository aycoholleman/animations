package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;

/**
 * A 3-component vertex class suitable for specifying 3D coordinates.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 *
 */
public class Pos3 extends Vertex implements _Pos3 {

	public static final int COMPONENT_COUNT = 3;

	public static NonIndexedMemory<Pos3> allocate(int maxNumObjects)
	{
		return new NonIndexedMemory<Pos3>(new Pos3[maxNumObjects], COMPONENT_COUNT) {
			@Override
			Pos3 construct(float[] raw, int offset)
			{
				return new Pos3(raw, offset);
			}
		};
	}

	public Pos3()
	{
		super();
	}

	Pos3(float[] components, int offset)
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
	 * Set the x, y, and z coordinates of this instance.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * 
	 * @return This instance
	 */
	@Override
	public Pos3 xyz(float x, float y, float z)
	{
		Array.set3(components, offset, x, y, z);
		return this;
	}


	/**
	 * Set the x, y, and z coordinates of this instance.
	 * 
	 * @param xyz
	 * A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * If the specified array contains less than 3 elements
	 */
	@Override
	public Pos3 xyz(float[] xyz)
	{
		memcpy3(components, offset, xyz, 0);
		return this;
	}


	/**
	 * Copy the coordinates of the specified instance to this instance.
	 * 
	 * @param other
	 * 
	 * @return This instance
	 */
	public Pos3 xyz(Pos3 other)
	{
		memcpy3(components, offset, other.components, other.offset);
		return this;
	}


	/**
	 * Set the x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos3 x(float x)
	{
		components[offset + 0] = x;
		return this;
	}


	/**
	 * Set the y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos3 y(float y)
	{
		components[offset + 1] = y;
		return this;
	}


	/**
	 * Set the z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos3 z(float z)
	{
		components[offset + 2] = z;
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


	///////////////////////////////////
	// CONVERSIONS
	///////////////////////////////////

	/**
	 * Get a 4-component coordinate array, using the value of {@link #globalW}
	 * for the w coordinate.
	 * 
	 * @return A 4-component coordinate array
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = globalW;
		return result;
	}


	/**
	 * Get a 4-component coordinate array, using the specified value for the w
	 * coordinate.
	 * 
	 * @param w
	 * The value to use for the w coordinate.
	 * 
	 * @return A 4-component coordinate array
	 * 
	 */
	public float[] xyzw(float w)
	{
		float[] result = new float[4];
		memcpy3(result, 0, components, offset);
		result[3] = w;
		return result;
	}


	@Override
	public Pos3 pos3()
	{
		return this;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Pos3 other = (Pos3) obj;
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
