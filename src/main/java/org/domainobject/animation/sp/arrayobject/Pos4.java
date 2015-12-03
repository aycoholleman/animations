package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;

/**
 * A 4-component vertex class suitable for specifying 4D coordinates.
 * 
 * @author Ayco Holleman
 */
public final class Pos4 extends ArrayObject implements _Pos4 {

	public static final int COMPONENT_COUNT = 4;

	private static final _Constructor<Pos4> constructor = new _Constructor<Pos4>() {

		public Pos4 make(float[] raw, int offset)
		{
			return new Pos4(raw, offset);
		}

		public Pos4[] array(int length)
		{
			return new Pos4[length];
		}
	};

	public static Memory<Pos4> reserve(int maxNumObjects)
	{
		return new Memory<Pos4>(maxNumObjects, OBJ_SIZE) {

			_Constructor<Pos4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static LazyIndexedMemory<Pos4> reserveLazy(int maxNumObjs, boolean useIntIndices)
	{
		return new LazyIndexedMemory<Pos4>(maxNumObjs, OBJ_SIZE, useIntIndices) {

			@Override
			_Constructor<Pos4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static FastIndexedMemory<Pos4> reserveFast(int maxNumObjs, boolean useIntIndices)
	{
		return new FastIndexedMemory<Pos4>(maxNumObjs, OBJ_SIZE, useIntIndices) {

			@Override
			_Constructor<Pos4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static final int OBJ_SIZE = 4;
	public static final int BYTE_SIZE = OBJ_SIZE * SIZE_OF_FLOAT;
	public static final int[] strides = new int[] { 0 };

	public static Memory<Pos4> allocate(int maxNumObjects)
	{
		return new Memory<Pos4>(maxNumObjects, COMPONENT_COUNT) {

			_Constructor<Pos4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public Pos4()
	{
		super();
	}

	Pos4(float[] components, int offset)
	{
		super(components, offset);
	}

	Pos4(ArrayObject embedder, int offset)
	{
		super(embedder, offset);
	}

	@Override
	int objSize()
	{
		return COMPONENT_COUNT;
	}

	/**
	 * Sets the coordinates of this instance.
	 * 
	 * @return This instance
	 */
	public Pos4 xyzw(float x, float y, float z, float w)
	{
		Array.set4(components, offset, x, y, z, w);
		return this;
	}

	/**
	 * Set the x, y, and z coordinates to the specified values while the w
	 * coordinate is set to the {@link Vertex#globalW global w coordinate}.
	 * 
	 * @return This instance
	 */
	public Pos4 xyzw(float x, float y, float z)
	{
		Array.set4(components, offset, x, y, z, Vertex.globalW);
		return this;
	}
	
	/**
	 * Sets the coordinates of this instance.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 4 elements
	 * @return This instance
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 4 elements
	 */
	public Pos4 set(float[] coordinates)
	{
		memcpy4(components, offset, coordinates, 0);
		return this;
	}

	/**
	 * Copies the coordinates of the specified {@link _Pos4} instance to this
	 * instance.
	 * 
	 * @param other
	 */
	public Pos4 set(_Pos4 other)
	{
		memcpy4(components, offset, other.position().components, other.position().offset);
		return this;
	}


	/**
	 * Set the x, y, and z coordinates as specified by the array argument while
	 * the w coordinate is set to {@link Vertex#globalW globalW}.
	 * 
	 * @param coordinates
	 *            A {@code float} array containing at least 3 elements
	 * 
	 * @return This instance
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the specified array contains less than 3 elements
	 */
	public Pos4 xyz(float[] coordinates)
	{
		memcpy3(components, offset, coordinates, 0);
		return w(Vertex.globalW);
	}

	/**
	 * Set x coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 x(float x)
	{
		components[offset + 0] = x;
		return this;
	}

	/**
	 * Set y coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 y(float y)
	{
		components[offset + 1] = y;
		return this;
	}

	/**
	 * Set z coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 z(float z)
	{
		components[offset + 2] = z;
		return this;
	}

	/**
	 * Set w coordinate.
	 * 
	 * @return This instance
	 */
	public Pos4 w(float w)
	{
		components[offset + 3] = w;
		return this;
	}

	/**
	 * Get the x, y, z and w coordinates.
	 */
	public float[] xyzw()
	{
		float[] result = new float[4];
		memcpy4(result, 0, components, offset);
		return result;
	}

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

	/**
	 * Get the w coordinate.
	 */
	public float w()
	{
		return components[offset + 3];
	}

	@Override
	public Pos4 position()
	{
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Pos4 other = (Pos4) obj;
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
