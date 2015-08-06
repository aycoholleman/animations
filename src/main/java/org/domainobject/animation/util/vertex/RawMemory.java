package org.domainobject.animation.util.vertex;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class RawMemory {

	private FloatBuffer buf;
	private float[] raw;
	private int size;
	private int numArrays;


	public RawMemory(int maxSize)
	{
		raw = new float[maxSize];
		buf = BufferUtils.createFloatBuffer(maxSize);
	}


	/**
	 * Erases all vertex data from this {@code VertexArray} and puts it in a
	 * state as though it was just instantiated.
	 */
	public void clear()
	{
		size = 0;
		numArrays = 0;
	}


	/**
	 * Burns the vertex data to a {@code FloatBuffer} and then clears the
	 * {@code VertexArray}. The {@code FloatBuffer} is flipped before being
	 * returned to the caller. You cannot burn an empty {@code VertexArray}
	 * (i.e. when {@link #getSize()} returns 0). Doing so triggers a
	 * {@link MemoryException}. Since burning implicitly clears the
	 * {@code VertexArray}, this means you cannot burn a {@code VertexArray}
	 * twice without adding some vertex data in between.
	 * 
	 * @return A {@code FloatBuffer} containing all vertices added to this
	 *         {@code VertexArray}
	 * 
	 * @throws MemoryException
	 *             If the {@code VertexArray} is empty.
	 * 
	 * @see #clear()
	 */
	public FloatBuffer burn()
	{
		if (size == 0) {
			MemoryException.cannotBurnWhenEmpty();
		}
		buf.clear();
		buf.put(raw, 0, size);
		buf.flip();
		size = 0;
		numArrays = 0;
		return buf;
	}


	/**
	 * Adds the specified coordinates to this {@code VertexArray}.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * 
	 * @return This instance
	 */
	public RawMemory xyz(float x, float y, float z)
	{
		add(x, y, z);
		return this;
	}


	/**
	 * Adds the specified coordinates to this {@code VertexArray} <i>plus</i>
	 * the value of {@link TypedVertex#DEFAULT_W}. In other words, this method
	 * adds <i>four</i> components to this {@code VertexArray}.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * 
	 * @return This instance
	 */
	public RawMemory xyzw(float x, float y, float z)
	{
		add(x, y, z, TypedVertex.DEFAULT_W);
		return this;
	}


	/**
	 * Adds the specified coordinates to this {@code VertexArray}.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 * 
	 * @return This instance
	 */
	public RawMemory xyzw(float x, float y, float z, float w)
	{
		add(x, y, z, w);
		return this;
	}


	/**
	 * Adds the specified color to this {@code VertexArray}.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * 
	 * @return This instance
	 */
	public RawMemory rgb(float red, float green, float blue)
	{
		add(red, green, blue);
		return this;
	}


	/**
	 * Adds the specified color to this {@code VertexArray} <i>plus</i> the
	 * value of {@link TypedVertex#DEFAULT_ALPHA}. In other words, this method
	 * adds <i>four</i> components to this {@code VertexArray}.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * 
	 * @return This instance
	 */
	public RawMemory rgba(float red, float green, float blue)
	{
		add(red, green, blue, TypedVertex.DEFAULT_ALPHA);
		return this;
	}


	/**
	 * Adds the specified coordinates to this {@code VertexArray}.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 * @return This instance
	 */
	public RawMemory rgba(float red, float green, float blue, float alpha)
	{
		add(red, green, blue, alpha);
		return this;
	}


	public void add(float c0, float c1)
	{
		raw[size++] = c0;
		raw[size++] = c1;
	}


	public void add(float c0, float c1, float c2)
	{
		raw[size++] = c0;
		raw[size++] = c1;
		raw[size++] = c2;
	}


	public void add(float c0, float c1, float c2, float c3)
	{
		raw[size++] = c0;
		raw[size++] = c1;
		raw[size++] = c2;
		raw[size++] = c3;
	}


	/**
	 * Get the number of vertices added to this {@code VertexArray}.
	 * 
	 * @return
	 */
	public int getNumVertices()
	{
		return numArrays;
	}


	/**
	 * Get the total size of all vertices stored in this {@code VertexArray}
	 * (i.e. the number of elements occupied by the vertices in the internally
	 * maintained raw float array).
	 * 
	 * @return
	 */
	public int getSize()
	{
		return size;
	}


	/**
	 * Get the remaining capacity of this {@code VertexArray} (i.e. the number
	 * of elements still unused in the internally maintained float array).
	 * 
	 * @return
	 */
	public int available()
	{
		return raw.length - size;
	}

}
