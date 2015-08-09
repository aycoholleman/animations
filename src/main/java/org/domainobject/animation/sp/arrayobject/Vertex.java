package org.domainobject.animation.sp.arrayobject;

public abstract class Vertex extends ArrayObject {

	/**
	 * Settable static field, used as the default value for the x coordinate
	 * (initially 0.0f).
	 */
	public static float defaultX = 0.0f;
	/**
	 * Settable static field, used as the default value for the y coordinate
	 * (initially 0.0f).
	 */
	public static float defaultY = 0.0f;
	/**
	 * Settable static field, used as the default value for the z coordinate
	 * (initially 0.0f).
	 */
	public static float defaultZ = 0.0f;
	/**
	 * Settable static field, used as the default value for the w coordinate
	 * (initially 1.0f).
	 */
	public static float defaultW = 1.0f;


	/**
	 * Settable static field, used as the default value for the red channel
	 * (initially 0.0f).
	 */
	public static float defaultRed = 0.0f;
	/**
	 * Settable static field, used as the default value for the green channel
	 * (initially 0.0f).
	 */
	public static float defaultGreen = 0.0f;
	/**
	 * Settable static field, used as the default value for the blue channel
	 * (initially 0.0f).
	 */
	public static float defaultBlue = 0.0f;
	/**
	 * Settable static field, used as the default value for the alpha channel
	 * (initially 1.0f).
	 */
	public static float defaultAlpha = 1.0f;


	/**
	 * Settable static field, used as the default value for a normal's x
	 * coordinate (initially 0.0f).
	 */
	public static float defaultNormalX = 0.0f;
	/**
	 * Settable static field, used as the default value for a normal's y
	 * coordinate (initially 0.0f).
	 */
	public static float defaultNormalY = 0.0f;
	/**
	 * Settable static field, used as the default value for a normal's z
	 * coordinate (initially 0.0f).
	 */
	public static float defaultNormalZ = 0.0f;


	/**
	 * Settable static field, used as the default value for the s texture
	 * coordinate (initially 0.0f).
	 */
	public static float defaultS = 0.0f;
	/**
	 * Settable static field, used as the default value for the t texture
	 * coordinate (initially 0.0f).
	 */
	public static float defaultT = 0.0f;


	Vertex(float[] components, int offset)
	{
		super(components, offset);
	}

}
