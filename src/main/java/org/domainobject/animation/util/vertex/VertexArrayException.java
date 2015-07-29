package org.domainobject.animation.util.vertex;

import org.domainobject.animation.AnimationException;

@SuppressWarnings("serial")
public class VertexArrayException extends AnimationException {

	private static final String MSG_CANNOT_BURN_WHEN_EMPTY = "Cannot burn empty VertexArray";


	public static void cannotBurnWhenEmpty() throws VertexArrayException
	{
		throw new VertexArrayException(MSG_CANNOT_BURN_WHEN_EMPTY);
	}


	public VertexArrayException()
	{
	}


	public VertexArrayException(String message)
	{
		super(message);
	}

}
