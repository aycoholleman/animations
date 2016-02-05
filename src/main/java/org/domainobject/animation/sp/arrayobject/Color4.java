package org.domainobject.animation.sp.arrayobject;

import static org.domainobject.animation.sp.util.C2J.SIZE_OF_FLOAT;
import static org.domainobject.animation.sp.util.C2J.memcpy3;
import static org.domainobject.animation.sp.util.C2J.memcpy4;
import static org.domainobject.animation.sp.util.Comparators.hash4;
import static org.domainobject.animation.sp.util.Comparators.same4;

import org.domainobject.animation.sp.util.Array;

/**
 * A 4-component vertex class suitable for specifying colors.
 * 
 * @author Ayco Holleman
 * @created Jul 20, 2015
 */
public class Color4 extends Vertex implements IColor4 {

	public static final int COMPONENT_COUNT = 4;

	private static final IConstructor<Color4> constructor = new IConstructor<Color4>() {

		public Color4 make(float[] raw, int offset)
		{
			return new Color4(raw, offset);
		}

		public Color4[] array(int length)
		{
			return new Color4[length];
		}
	};

	public static Memory<Color4> reserve(int maxNumObjects)
	{
		return new Memory<Color4>(maxNumObjects, OBJ_SIZE) {

			IConstructor<Color4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static IndexedMemoryLazy<Color4> reserveLazy(int maxNumObjs, boolean useIntIndices)
	{
		return new IndexedMemoryLazy<Color4>(maxNumObjs, OBJ_SIZE, useIntIndices) {

			@Override
			IConstructor<Color4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static IndexedMemoryFast<Color4> reserveFast(int maxNumIndices, boolean useIntIndices)
	{
		IFastIndexer<Color4> indexer;
		if (useIntIndices || maxNumIndices > Short.MAX_VALUE)
			indexer = new FastIntIndexer<>(maxNumIndices);
		else if (maxNumIndices > Byte.MAX_VALUE)
			indexer = new FastShortIndexer<>(maxNumIndices);
		else
			indexer = new FastByteIndexer<>(maxNumIndices);
		return new IndexedMemoryFast<Color4>(indexer, OBJ_SIZE) {

			@Override
			IConstructor<Color4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public static final int OBJ_SIZE = 4;
	public static final int BYTE_SIZE = OBJ_SIZE * SIZE_OF_FLOAT;
	public static final int[] strides = new int[] { 0 };

	public static Memory<Color4> allocate(int maxNumObjects)
	{
		return new Memory<Color4>(maxNumObjects, COMPONENT_COUNT) {

			IConstructor<Color4> getConstructor()
			{
				return constructor;
			}
		};
	}

	public Color4()
	{
		super();
	}

	Color4(float[] components, int offset)
	{
		super(components, offset);
	}

	Color4(Vertex embedder, int offset)
	{
		super(embedder, offset);
	}

	@Override
	int objSize()
	{
		return COMPONENT_COUNT;
	}

	public Color4 rgba(float red, float green, float blue, float alpha)
	{
		Array.set4(components, offset, red, green, blue, alpha);
		return this;
	}

	public Color4 rgba(float[] rgba)
	{
		memcpy4(components, offset, rgba, 0);
		return this;
	}

	public Color4 rgba(IColor4 other)
	{
		memcpy4(components, offset, other.color().components, other.color().offset);
		return this;
	}

	public Color4 rgb(float r, float g, float b)
	{
		Array.set4(components, offset, r, g, b, VertexDefaults.globalAlpha);
		return this;
	}

	public Color4 rgb(float[] rgb)
	{
		memcpy3(components, offset, rgb, 0);
		return a(VertexDefaults.globalAlpha);
	}

	/**
	 * Set red channel.
	 * 
	 * @return This instance
	 */
	public Color4 r(float r)
	{
		components[offset + 0] = r;
		return this;
	}

	/**
	 * Set green channel.
	 * 
	 * @return This instance
	 */
	public Color4 g(float g)
	{
		components[offset + 1] = g;
		return this;
	}

	/**
	 * Set blue channel.
	 * 
	 * @return This instance
	 */
	public Color4 b(float b)
	{
		components[offset + 2] = b;
		return this;
	}

	/**
	 * Set alpha channel.
	 * 
	 * @return This instance
	 */
	public Color4 a(float a)
	{
		components[offset + 3] = a;
		return this;
	}

	/**
	 * Get red, green, blue and alpha channel
	 */
	public float[] rgba()
	{
		float[] result = new float[4];
		memcpy4(result, 0, components, offset);
		return result;
	}

	/**
	 * Get red channel.
	 */
	public float r()
	{
		return components[offset + 0];
	}

	/**
	 * Get green channel.
	 */
	public float g()
	{
		return components[offset + 1];
	}

	/**
	 * Get blue channel.
	 */
	public float b()
	{
		return components[offset + 2];
	}

	/**
	 * Get alpha channel.
	 */
	public float a()
	{
		return components[offset + 3];
	}

	@Override
	public Color4 color()
	{
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj.getClass() == Color4.class) {
			Color4 other = (Color4) obj;
			return same4(components, offset, other.components, other.offset);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return hash4(components, offset);
	}

	@Override
	public void copyTo(float[] target, int offset)
	{
		memcpy4(target, offset, components, this.offset);
	}

	@Override
	void copyTo(Vertex other)
	{
		memcpy4(other.components, other.offset, components, offset);
	}

}
