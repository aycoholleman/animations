package org.domainobject.animation.sp.arrayobject;

import org.domainobject.animation.sp.AnimationException;

@SuppressWarnings("serial")
public class MemoryException extends AnimationException {

	private static final String MSG_CANNOT_BURN_WHEN_EMPTY = "Cannot burn empty memory";
	private static final String MSG_OVERWRITTEN = "Array object to be committed has already been overwritten";


	public static void cannotBurnWhenEmpty() throws MemoryException
	{
		throw new MemoryException(MSG_CANNOT_BURN_WHEN_EMPTY);
	}


	public static void overwritten() throws MemoryException
	{
		throw new MemoryException(MSG_OVERWRITTEN);
	}


	public MemoryException()
	{
	}


	public MemoryException(String message)
	{
		super(message);
	}

}
