package org.domainobject.animation.sp.arrayobject;

public abstract class TypedVertex extends ArrayObject {

	/**
	 * Settable static field, used as the default value for the w coordinate.
	 */
	public static float DEFAULT_W = 1.0f;
	/**
	 * Settable static field, used as the default value for a color's alpha
	 * channel.
	 */
	public static float DEFAULT_ALPHA = 1.0f;


	TypedVertex(float[] components, int offset)
	{
		super(components, offset);
	}

}
