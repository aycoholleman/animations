package org.domainobject.animation.util.vertex;

public abstract class Vertex {

	/**
	 * Settable static variable used as the default value for the w coordinate
	 * if the a concrete subclass has no slot for the w coordinate.
	 */
	public static float DEFAULT_W = 1.0f;
	/**
	 * Settable static variable used as the default value for the alpha channel
	 * if the a concrete subclass has no slot for the alpha channel.
	 */
	public static float DEFAULT_A = 1.0f;

	float[] components;
	int offset;


	Vertex()
	{

	}


	abstract int getComponentCount();

}
