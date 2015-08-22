package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.arrayobject.Vertex.*;
import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;

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

	@Override
	public Normal normal(float x, float y, float z)
	{
		Array.set3(components, offset, x, y, z);
		return this;
	}

	@Override
	public Normal normal(float[] normal)
	{
		memcpy3(components, offset, normal, 0);
		return this;
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
		Normal other = (Normal) obj;
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
