package org.domainobject.animation.util.vertex;

abstract class Vertex {

	final float[] components;
	final int offset;


	Vertex(float[] components, int offset)
	{
		this.components = components;
		this.offset = offset;
	}


	/**
	 * Get the number of components (array elements) of this {@code Vertex} .
	 * 
	 * @return The number of components of this {@code Vertex}
	 */
	abstract int size();

}
