package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.arrayobject.Vertex.*;
import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;
import org.domainobject.animation.sp.util.Math3D;

public final class Normal extends ArrayObject implements _Normal {

	public static final int COMPONENT_COUNT = 3;


	public static NonIndexedMemory<Normal> allocate(int maxNumObjects)
	{
		return new NonIndexedMemory<Normal>(new Normal[maxNumObjects], COMPONENT_COUNT) {
			@Override
			Normal construct(float[] raw, int offset)
			{
				return new Normal(raw, offset);
			}
		};
	}

	public Normal()
	{
		super();
	}

	Normal(float[] components, int offset)
	{
		super(components, offset);
	}

	Normal(ArrayObject embedder, int offset)
	{
		super(embedder, offset);
	}

	public Normal init()
	{
		components[offset + 0] = 0;
		components[offset + 1] = 0;
		components[offset + 2] = 0;
		return this;
	}

	public Normal global()
	{
		components[offset + 0] = globalNX;
		components[offset + 1] = globalNY;
		components[offset + 2] = globalNZ;
		return this;
	}

	@Override
	int objSize()
	{
		return COMPONENT_COUNT;
	}

	public Normal xyz(float x, float y, float z)
	{
		Array.set3(components, offset, x, y, z);
		return this;
	}

	public Normal xyz(float[] xyz)
	{
		memcpy3(components, offset, xyz, 0);
		return this;
	}

	public Normal xyz(_Normal other)
	{
		memcpy3(components, offset, other.normal().components, other.normal().offset);
		return this;
	}
	
	/**
	 * Set x component.
	 * 
	 * @return This instance
	 */
	public Normal x(float x)
	{
		components[offset + 0] = x;
		return this;
	}

	/**
	 * Set y component.
	 * 
	 * @return This instance
	 */
	public Normal y(float y)
	{
		components[offset + 1] = y;
		return this;
	}

	/**
	 * Set z component.
	 * 
	 * @return This instance
	 */
	public Normal z(float z)
	{
		components[offset + 2] = z;
		return this;
	}	

	public Normal normalize()
	{
		Math3D.m3dNormalizeVector3(components, offset);
		return this;
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
	 * Get x component.
	 */
	public float x()
	{
		return components[offset + 0];
	}

	/**
	 * Get y component.
	 */
	public float y()
	{
		return components[offset + 1];
	}

	/**
	 * Get z component.
	 */
	public float z()
	{
		return components[offset + 2];
	}

	@Override
	public Normal normal()
	{
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Normal other = ((_Normal) obj).normal();
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
