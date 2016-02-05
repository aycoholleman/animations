package org.domainobject.animation.sp.arrayobject;

public abstract class VertexDefaults extends Vertex {

	/**
	 * Settable static field, used as the default value for the x coordinate
	 * (initially 0.0f).
	 */
	public static float globalX = 0.0f;
	/**
	 * Settable static field, used as the default value for the y coordinate
	 * (initially 0.0f).
	 */
	public static float globalY = 0.0f;
	/**
	 * Settable static field, used as the default value for the z coordinate
	 * (initially 0.0f).
	 */
	public static float globalZ = 0.0f;
	/**
	 * Settable static field, used as the default value for the w coordinate
	 * (initially 1.0f).
	 */
	public static float globalW = 1.0f;


	/**
	 * Settable static field, used as the default value for the red channel
	 * (initially 0.0f).
	 */
	public static float globalRed = 0.0f;
	/**
	 * Settable static field, used as the default value for the green channel
	 * (initially 0.0f).
	 */
	public static float globalGreen = 0.0f;
	/**
	 * Settable static field, used as the default value for the blue channel
	 * (initially 0.0f).
	 */
	public static float globalBlue = 0.0f;
	/**
	 * Settable static field, used as the default value for the alpha channel
	 * (initially 1.0f).
	 */
	public static float globalAlpha = 1.0f;


	/**
	 * Settable static field, used as the default value for a normal's x
	 * coordinate (initially 0.0f).
	 */
	public static float globalNX = 0.0f;
	/**
	 * Settable static field, used as the default value for a normal's y
	 * coordinate (initially 0.0f).
	 */
	public static float globalNY = 0.0f;
	/**
	 * Settable static field, used as the default value for a normal's z
	 * coordinate (initially 0.0f).
	 */
	public static float globalNZ = 0.0f;


	/**
	 * Settable static field, used as the default value for the s texture
	 * coordinate (initially 0.0f).
	 */
	public static float globalS = 0.0f;
	/**
	 * Settable static field, used as the default value for the t texture
	 * coordinate (initially 0.0f).
	 */
	public static float globalT = 0.0f;

	VertexDefaults()
	{
		super();
	}

	VertexDefaults(float[] components, int offset)
	{
		super(components, offset);
	}

	VertexDefaults(Vertex embedder, int offset)
	{
		super(embedder, offset);
	}


}
