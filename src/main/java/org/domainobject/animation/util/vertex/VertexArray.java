package org.domainobject.animation.util.vertex;

public class VertexArray {

	private float[] raw;
	private int offset;
	private int numVertices;


	public VertexArray(int maxSize)
	{
		raw = new float[maxSize];
	}


	public Pos3 newPos3()
	{
		return newVertex(new Pos3());
	}


	public Pos4 newPos4()
	{
		return newVertex(new Pos4());
	}


	public Color3 newColor3()
	{
		return newVertex(new Color3());
	}

	public Color4 newColor4()
	{
		return newVertex(new Color4());
	}


	public VertexArray position(float x, float y, float z)
	{
		newVertex(new Pos3()).set(x, y, z);
		return this;
	}


	public VertexArray position(float x, float y, float z, float w)
	{
		newVertex(new Pos4()).set(x, y, z, w);
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
		return offset;
	}


	/**
	 * Get the remaining capacity of this {@code VertexArray} (i.e. the number
	 * of elements still unused in the internally maintained float array).
	 * 
	 * @return
	 */
	public int available()
	{
		return raw.length - offset;
	}


	private <T extends Vertex> T newVertex(T vertex)
	{
		++numVertices;
		vertex.components = raw;
		vertex.offset = offset;
		offset += vertex.size();
		return vertex;
	}

}
