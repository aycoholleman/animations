package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

/**
 * A 6-component vertex class suitable for specifying a position (first 3 slots)
 * and a color (last three slots).
 * 
 * @author Ayco Holleman
 * @created Jul 26, 2015
 *
 */
public class Pos3Color3 extends Vertex implements IPos3, IColor3 {

	public static final int COMPONENT_COUNT = 6;

	public final Pos3 position;
	public final Color3 color;

	public Pos3Color3()
	{
		super();
		position = new Pos3(this, 0);
		color = new Color3(this, 3);
	}

	Pos3Color3(float[] components, int offset)
	{
		super(components, offset);
		position = new Pos3(this, 0);
		color = new Color3(this, 3);
	}


	@Override
	int size()
	{
		return COMPONENT_COUNT;
	}

	@Override
	public Pos3 position()
	{
		return position;
	}


	@Override
	public Color3 color()
	{
		return color;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Pos3Color3 other = (Pos3Color3) obj;
		return same3(components, offset, other.components, other.offset)
				&& same3(components, offset + 3, other.components, other.offset + 3);
	}


	@Override
	public int hashCode()
	{
		return hash6(components, offset);
	}

	@Override
	public void copyTo(float[] array, int offset)
	{
		memcpy6(array, offset, components, this.offset);
	}


	@Override
	void copyTo(Vertex other)
	{
		memcpy6(other.components, other.offset, components, offset);
	}
}
