package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

import org.domainobject.animation.sp.util.Array;

/**
 * @author Ayco Holleman
 *
 */
public final class Texture extends ArrayObject implements _Texture {

	public static final int COMPONENT_COUNT = 2;

	public Texture()
	{
		super();
	}

	Texture(float[] components, int offset)
	{
		super(components, offset);
	}

	Texture(ArrayObject embedder, int offset)
	{
		super(embedder, offset);
	}

	@Override
	int objSize()
	{
		return COMPONENT_COUNT;
	}

	public Texture set(float s, float t)
	{
		Array.set2(components, offset, s, t);
		return this;
	}

	public Texture set(float[] st)
	{
		memcpy2(components, offset, st, 0);
		return this;
	}

	public Texture s(float s)
	{
		components[offset + 0] = s;
		return this;
	}

	public Texture t(float t)
	{
		components[offset + 1] = t;
		return this;
	}

	@Override
	public Texture texture()
	{
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Texture other = ((_Texture) obj).texture();
		return same2(components, offset, other.components, other.offset);
	}

	@Override
	public int hashCode()
	{
		return hash4(components, offset);
	}

	@Override
	public void copyTo(float[] target, int offset)
	{
		memcpy2(target, offset, components, this.offset);
	}

	@Override
	void copyTo(ArrayObject other)
	{
		memcpy2(other.components, other.offset, components, offset);
	}

}
