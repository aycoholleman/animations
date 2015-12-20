package org.domainobject.animation.sp;

import org.domainobject.animation.sp.arrayobject.IndexedMemoryFast;
import org.domainobject.animation.sp.arrayobject.IndexedMemoryLazy;
import org.domainobject.animation.sp.arrayobject.IndexedMemoryLazy.BurnMethod;

public class Global {

	/**
	 * Burn method for lazily indexed memory.
	 * 
	 * @see IndexedMemoryLazy
	 * @see BurnMethod
	 */
	public static BurnMethod burnMethod = BurnMethod.DESTRUCTIVE;

	/**
	 * Whether or not to always use integer indices for the indices buffer
	 * (GL_ELEMENT_ARRAY_BUFFER) of indexed memory. If not, the program will
	 * adapt the index type to the maximum number of array objects in memory (
	 * {@code int.class} if more than {@code Short.MAX_VALUE};
	 * {@code short.class} if more than {@code Byte.MAX_VALUE};
	 * {@code byte.class} otherwise).
	 * 
	 * @see IndexedMemoryLazy
	 * @see IndexedMemoryFast
	 */
	public static boolean forceIntIndices = false;

	private Global()
	{
	}

}
