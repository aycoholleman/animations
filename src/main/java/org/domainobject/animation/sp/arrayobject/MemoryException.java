package org.domainobject.animation.sp.arrayobject;

import org.domainobject.animation.sp.AnimationException;

@SuppressWarnings("serial")
public class MemoryException extends AnimationException {

	private static final String MSG_CANNOT_BURN_WHEN_EMPTY;
	private static final String MSG_COMMIT_WINDOW_CLOSED;
	private static final String MSG_ALREADY_COMMITTED;

	static {
		MSG_CANNOT_BURN_WHEN_EMPTY = "Cannot burn empty memory";
		MSG_COMMIT_WINDOW_CLOSED = "Array object(s) can no longer be committed to memory. "
				+ "They may have been written by another array object, or you may have burnt "
				+ "the memory object to OpenGL already";
		MSG_ALREADY_COMMITTED = "Array object already committed to memory";
	}


	public static void cannotBurnWhenEmpty() throws MemoryException
	{
		throw new MemoryException(MSG_CANNOT_BURN_WHEN_EMPTY);
	}


	public static void commitWindowClosed() throws MemoryException
	{
		throw new MemoryException(MSG_COMMIT_WINDOW_CLOSED);
	}


	public static void alreadyCommitted() throws MemoryException
	{
		throw new MemoryException(MSG_ALREADY_COMMITTED);
	}


	public MemoryException()
	{
	}


	public MemoryException(String message)
	{
		super(message);
	}

}
