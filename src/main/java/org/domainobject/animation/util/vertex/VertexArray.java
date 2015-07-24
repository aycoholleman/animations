package org.domainobject.animation.util.vertex;

public class VertexArray {

	private int[] offsets;
	private float[] raw;
	private int numVertices;
	private int size;


	/**
	 * Creates a {@code VertexArray} that can hold up to {@code maxNumVertices}
	 * vertices and up to {@code maxSize} single {@code float} elements. Thus,
	 * if you plan to add no more than 100 vertices, and each vertex you add has
	 * 3 components (e.g. x, y and z), then you should specify 100 for
	 * {@code maxNumVertices} and 300 for {@code maxSize}.
	 * 
	 */
	public VertexArray(int maxNumVertices, int maxSize)
	{
		offsets = new int[maxNumVertices];
		raw = new float[maxSize];
	}


	public Pos3D newXYZVertex()
	{
		Pos3D vertex = new Pos3D();
		size += vertex.getComponentCount();
		vertex.components = raw;
		vertex.offset = offsets[numVertices++];
		offsets[numVertices] = vertex.offset + vertex.getComponentCount();
		return vertex;
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

}
