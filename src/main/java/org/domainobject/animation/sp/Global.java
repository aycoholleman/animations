package org.domainobject.animation.sp;

import org.domainobject.animation.sp.arrayobject.FastIndexedMemory;
import org.domainobject.animation.sp.arrayobject.LazyIndexedMemory;
import org.domainobject.animation.sp.arrayobject.LazyIndexedMemory.BurnMethod;

public class Global {

	/**
	 * Burn method for lazily indexed memory.
	 * 
	 * @see LazyIndexedMemory
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
	 * @see LazyIndexedMemory
	 * @see FastIndexedMemory
	 */
	public static boolean forceIntIndices = false;

	private Global()
	{
	}

}
