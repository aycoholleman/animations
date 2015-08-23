package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.*;
import static org.domainobject.animation.sp.util.Comparators.*;


/**
 * A 9-component vertex class suitable for specifying a position (first four
 * slots) a normal (next three slots) and two texture coordinates (last two
 * slots).
 * 
 * @author Ayco Holleman
 */
public final class Pos4NormalTexture extends ArrayObject implements _Pos4, _Normal, _Texture {

	public static NonIndexedMemory<Pos4NormalTexture> allocate(int maxNumObjects)
	{
		return new NonIndexedMemory<Pos4NormalTexture>(new Pos4NormalTexture[maxNumObjects], NUM_COMPONENTS) {
			@Override
			Pos4NormalTexture construct(float[] raw, int offset)
			{
				return new Pos4NormalTexture(raw, offset);
			}
		};
	}

	public static _IndexedMemoryLazy<Pos4NormalTexture> indexLazy(int maxNumObjects, boolean useIntIndices)
	{
		if (useIntIndices || maxNumObjects > Short.MAX_VALUE) {
			return new IndexedMemoryLazyInt<Pos4NormalTexture>(new Pos4NormalTexture[maxNumObjects],
					NUM_COMPONENTS) {
				@Override
				Pos4NormalTexture construct(float[] raw, int offset)
				{
					return new Pos4NormalTexture(raw, offset);
				}
			};
		}
		if (maxNumObjects > Byte.MAX_VALUE) {
			return new IndexedMemoryLazyShort<Pos4NormalTexture>(new Pos4NormalTexture[maxNumObjects],
					NUM_COMPONENTS) {
				@Override
				Pos4NormalTexture construct(float[] raw, int offset)
				{
					return new Pos4NormalTexture(raw, offset);
				}
			};
		}
		return new IndexedMemoryLazyByte<Pos4NormalTexture>(new Pos4NormalTexture[maxNumObjects],
				NUM_COMPONENTS) {
			@Override
			Pos4NormalTexture construct(float[] raw, int offset)
			{
				return new Pos4NormalTexture(raw, offset);
			}
		};
	}

	public static _IndexedMemoryFast<Pos4NormalTexture> indexFast(int maxNumObjects, boolean useIntIndices)
	{
		if (useIntIndices || maxNumObjects > Short.MAX_VALUE) {
			return new IndexedMemoryFastInt<Pos4NormalTexture>(maxNumObjects, NUM_COMPONENTS) {
				@Override
				Pos4NormalTexture construct(float[] raw, int offset)
				{
					return new Pos4NormalTexture(raw, offset);
				}
			};
		}
		if (maxNumObjects > Byte.MAX_VALUE) {
			return new IndexedMemoryFastShort<Pos4NormalTexture>(maxNumObjects, NUM_COMPONENTS) {
				@Override
				Pos4NormalTexture construct(float[] raw, int offset)
				{
					return new Pos4NormalTexture(raw, offset);
				}
			};
		}
		return new IndexedMemoryFastByte<Pos4NormalTexture>(maxNumObjects, NUM_COMPONENTS) {
			@Override
			Pos4NormalTexture construct(float[] raw, int offset)
			{
				return new Pos4NormalTexture(raw, offset);
			}
		};
	}

	/**
	 * The number of elements of the internal array (9).
	 */
	public static final int NUM_COMPONENTS = 9;
	/**
	 * The number of bytes occupied by the internal array.
	 */
	public static final int BYTE_SIZE = NUM_COMPONENTS * SIZE_OF_FLOAT;
	/**
	 * The byte offsets of the component groups within the array.
	 */
	public static final int[] strides = new int[] { 0, sizeof(4), sizeof(3) };

	public final Pos4 position;
	public final Normal normal;
	public final Texture texture;


	public Pos4NormalTexture()
	{
		super();
		position = new Pos4(this, 0);
		normal = new Normal(this, 4);
		texture = new Texture(this, 7);
	}

	private Pos4NormalTexture(float[] raw, int offset)
	{
		super(raw, offset);
		position = new Pos4(this, 0);
		normal = new Normal(this, 4);
		texture = new Texture(this, 7);
	}


	@Override
	int objSize()
	{
		return NUM_COMPONENTS;
	}


	/**
	 * Initialize this instance. The x, y and z coordinates are set to 0, as are
	 * the red, green and blue channels. The w coordinate is set to
	 * {@link Vertex#globalW} and the alpha channel is set to
	 * {@link Vertex#globalAlpha}. Note that until you call this method or one
	 * of the other setters, the internal state of a new instance is undefined,
	 * both in theory and in practice!
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
		components[offset + 7] = 0;
		components[offset + 8] = 0;
	}

	@Override
	public Pos4 position()
	{
		return position;
	}

	@Override
	public Normal normal()
	{
		return normal;
	}

	@Override
	public Texture texture()
	{
		return texture;
	};

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		Pos4NormalTexture other = (Pos4NormalTexture) obj;
		return same4(components, offset, other.components, other.offset)
				&& same3(components, offset + 4, other.components, other.offset + 4)
				&& same2(components, offset + 7, other.components, other.offset + 7);
	}

	public int hashCode()
	{
		return hash9(components, offset);
	}

	@Override
	public void copyTo(float[] array, int offset)
	{
		memcpy9(array, offset, components, this.offset);
	}


	@Override
	void copyTo(ArrayObject other)
	{
		memcpy9(other.components, other.offset, components, offset);
	}

}
