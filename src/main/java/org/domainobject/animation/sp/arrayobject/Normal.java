package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;

import org.domainobject.animation.sp.util.Array;

public class Normal extends Vertex implements _Normal {

	public static final int COMPONENT_COUNT = 3;


	Normal(float[] components, int offset)
	{
		super(components, offset);
	}


	@Override
	int size()
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

}
