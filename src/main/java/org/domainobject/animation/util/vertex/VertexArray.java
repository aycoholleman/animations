package org.domainobject.animation.util.vertex;

public class VertexArray {

	private float[] raw;
	private int offset;
	private int numVertices;
	private int size;


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
	 * Get the total size of all vertices add to this {@code VertexArray}.
	 * 
	 * @return
	 */
	public int getSize()
	{
		return size;
	}


	private <T extends Vertex> T newVertex(T vertex)
	{
		size += vertex.size();
		vertex.components = raw;
		vertex.offset = offset;
		offset += vertex.size();
		++numVertices;
		return vertex;
	}

}
