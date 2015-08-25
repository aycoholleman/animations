package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

/**
 * An 8-component vertex class suitable for specifying a position (first four
 * slots) and a color (last four slots).
 * 
 * @author Ayco Holleman
 * @created Jul 26, 2015
 *
 */
public final class Pos4Color4 extends ArrayObject implements _Pos4, _Color4 {
	
	private static final _Constructor<Pos4Color4> constructor = new _Constructor<Pos4Color4>() {
		public Pos4Color4 make(float[] raw, int offset)
		{
			return new Pos4Color4(raw, offset);
		}
		public Pos4Color4[] array(int length)
		{
			return new Pos4Color4[length];
		}
	};

	public static Memory<Pos4Color4> allocate(int maxNumObjects)
	{
		return new Memory<Pos4Color4>(maxNumObjects, COMPONENT_COUNT) {
			_Constructor<Pos4Color4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static final int COMPONENT_COUNT = 8;
	public static final int BYTE_SIZE = COMPONENT_COUNT * SIZE_OF_FLOAT;
	public static final int[] strides = new int[] { 0, sizeof(4) };

	public final Pos4 position;
	public final Color4 color;

	public Pos4Color4()
	{
		super();
		position = new Pos4(this, 0);
		color = new Color4(this, 4);
	}

	private Pos4Color4(float[] components, int offset)
	{
		super(components, offset);
		position = new Pos4(this, 0);
		color = new Color4(this, 4);
	}


	@Override
	int objSize()
	{
		return COMPONENT_COUNT;
	}

	/**
	 * Initialize this instance. The x, y and z coordinates are set to 0, as are
	 * the red, green and blue channels. The w coordinate is set to
	 * {@link Vertex#globalW} and the alpha channel is set to
	 * {@link Vertex#globalAlpha}. Note that until you call this method or one
	 * of the other setters, the internal state of a new instance is undefined.
	 */
	public void init()
	{
		components[offset + 0] = 0;
		components[offset + 1] = 0;
		components[offset + 2] = 0;
		components[offset + 3] = Vertex.globalW;
		components[offset + 4] = 0;
		components[offset + 5] = 0;
		components[offset + 6] = 0;
		components[offset + 7] = Vertex.globalAlpha;
	}

	public Pos4Color4 position(float x, float y, float z, float w)
	{
		position.set(x, y, z, w);
		return this;
	}

	public Pos4Color4 color(float r, float g, float b, float a)
	{
		color.rgba(r, g, b, a);
		return this;
	}

	@Override
	public Pos4 position()
	{
		return position;
	}

	@Override
	public Color4 color()
	{
		return color;
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
