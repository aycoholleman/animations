package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

/**
 * An 8-component vertex class suitable for specifying a position (first four
 * slots) and a color (last four slots).
 * 
 * @author Ayco Holleman
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

	public static Memory<Pos4Color4> reserve(int maxNumObjects)
	{
		return new Memory<Pos4Color4>(maxNumObjects, OBJ_SIZE) {
			_Constructor<Pos4Color4> getConstructor()
			{
				return constructor;
			}
		};
	}
	
	public static LazyIndexedMemory<Pos4Color4> reserveLazy(int maxNumObjs, boolean useIntIndices)
	{
		return new LazyIndexedMemory<Pos4Color4>(maxNumObjs, OBJ_SIZE, useIntIndices) {
			@Override
			_Constructor<Pos4Color4> getConstructor()
			{
				return constructor;
			}
		};
	}
	
	public static FastIndexedMemory<Pos4Color4> reserveFast(int maxNumObjs, boolean useIntIndices)
	{
		return new FastIndexedMemory<Pos4Color4>(maxNumObjs, OBJ_SIZE, useIntIndices) {
			@Override
			_Constructor<Pos4Color4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static final int OBJ_SIZE = 8;
	public static final int BYTE_SIZE = OBJ_SIZE * SIZE_OF_FLOAT;
	public static final int[] strides = new int[] { 0, sizeof(4) };

	public final Pos4 pos;
	public final Color4 color;

	public Pos4Color4()
	{
		super();
		pos = new Pos4(this, 0);
		color = new Color4(this, 4);
	}

	private Pos4Color4(float[] components, int offset)
	{
		super(components, offset);
		pos = new Pos4(this, 0);
		color = new Color4(this, 4);
	}


	@Override
	int objSize()
	{
		return OBJ_SIZE;
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

	public Pos4Color4 xyzw(float x, float y, float z, float w)
	{
		pos.xyzw(x, y, z, w);
		return this;
	}

	public Pos4Color4 rgba(float r, float g, float b, float a)
	{
		color.rgba(r, g, b, a);
		return this;
	}

	@Override
	public Pos4 position()
	{
		return pos;
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
