package org.domainobject.animation.util.vertex;

public class VertexArray {

	private float[] raw;
	private int size;
	private int numVertices;


	public VertexArray(int maxSize)
	{
		raw = new float[maxSize];
	}


	public Pos3 newPos3()
	{
		return newVertex(new Pos3(raw, size));
	}


	public Pos4 newPos4()
	{
		return newVertex(new Pos4(raw, size));
	}


	public Color3 newColor3()
	{
		return newVertex(new Color3(raw, size));
	}


	public Color4 newColor4()
	{
		return newVertex(new Color4(raw, size));
	}


	public VertexArray position(float x, float y, float z)
	{
		set(x, y, z);
		return this;
	}


	public VertexArray position(float x, float y, float z, float w)
	{
		set(x, y, z, w);
		return this;
	}


	public VertexArray color(float red, float green, float blue)
	{
		set(red, green, blue);
		return this;
	}


	public VertexArray color(float red, float green, float blue, float alpha)
	{
		set(red, green, blue, alpha);
		return this;
	}


	/**
	 * Get the number of vertices added to this {@code VertexArray}.
	 * 
	 * @return
	 */
	public int getNumVertices()
	{
		return numVertices;
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


	private <T extends Vertex> T newVertex(T vertex)
	{
		++numVertices;
		size += vertex.size();
		return vertex;
	}


	private void set(float c0, float c1, float c2)
	{
		raw[size++] = c0;
		raw[size++] = c1;
		raw[size++] = c2;
	}


	private void set(float c0, float c1, float c2, float c3)
	{
		raw[size++] = c0;
		raw[size++] = c1;
		raw[size++] = c2;
		raw[size++] = c3;
	}

}
