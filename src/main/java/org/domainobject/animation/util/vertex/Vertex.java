package org.domainobject.animation.util.vertex;

public abstract class Vertex {

	float[] components;
	int offset;


	/**
	 * Get the number of components (array elements) of this {@code Vertex} .
	 * 
	 * @return The number of components of this {@code Vertex}
	 */
	protected abstract int size();

}
