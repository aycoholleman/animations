package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;

/**
 * An 10-component vertex class suitable for specifying a position (first four
 * slots) a color (next four slots) and two texture coordinates (last two
 * slots).
 * 
 * @author Ayco Holleman
 * @created Jul 26, 2015
 *
 */
public final class Pos4Color4Texture extends Vertex implements IPos4, IColor4, ITexture {

	/**
	 * The number of elements of the internal array (10).
	 */
	public static final int SIZE = 10;
	/**
	 * The number of bytes occupied by the internal array.
	 */
	public static final int BYTE_SIZE = SIZE * SIZE_OF_FLOAT;
	/**
	 * The start points (in bytes) of the component groups within the array.
	 */
	public static final int[] strides = new int[] { 0, sizeof(4), sizeof(4) };

	public final Pos4 position;
	public final Color4 color;
	public final Texture texture;

	public Pos4Color4Texture()
	{
		super();
		position = new Pos4(this, 0);
		color = new Color4(this, 4);
		texture = new Texture(this, 8);
	}

	private Pos4Color4Texture(float[] raw, int offset)
	{
		super(raw, offset);
		position = new Pos4(this, 0);
		color = new Color4(this, 4);
		texture = new Texture(this, 8);
	}


	@Override
	int size()
	{
		return SIZE;
	}

	/**
	 * Initialize this instance. The x, y and z coordinates are set to 0, as are
	 * the red, green and blue channels. The w coordinate is set to
	 * {@link VertexDefaults#globalW} and the alpha channel is set to
	 * {@link VertexDefaults#globalAlpha}. Note that until you call this method or one
	 * of the other setters, the internal state of a new instance is undefined,
	 * both in theory and in practice!
	 */
	public void init()
	{
		components[offset + 0] = 0;
		components[offset + 1] = 0;
		components[offset + 2] = 0;
		components[offset + 3] = VertexDefaults.globalW;
		components[offset + 4] = 0;
		components[offset + 5] = 0;
		components[offset + 6] = 0;
		components[offset + 7] = VertexDefaults.globalAlpha;
		components[offset + 8] = 0;
		components[offset + 9] = 0;
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
	public Texture texture()
	{
		return texture;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Pos4Color4Texture other = (Pos4Color4Texture) obj;
		return same4(components, offset, other.components, other.offset)
				&& same4(components, offset + 4, other.components, other.offset + 4)
				&& same2(components, offset + 8, other.components, other.offset + 8);
	}


	@Override
	public int hashCode()
	{
		return hash10(components, offset);
	}


	@Override
	public void copyTo(float[] array, int offset)
	{
		memcpy10(array, offset, components, this.offset);
	}


	@Override
	void copyTo(Vertex other)
	{
		memcpy10(other.components, other.offset, components, offset);
	}
}
