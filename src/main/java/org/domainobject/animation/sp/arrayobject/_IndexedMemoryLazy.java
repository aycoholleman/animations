package org.domainobject.animation.sp.arrayobject;


public interface _IndexedMemoryLazy<T extends ArrayObject> extends _Memory<T> {

	/**
	 * Get the type of the indices in the element array buffer. Will be either
	 * {@code byte.class} or {@code short.class} or {@code int.class}.
	 * 
	 * @return The type of the indices in the element array buffer
	 */
	Class<?> getIndexType();

	/**
	 * Whether or not the burn process is allowed to delete and move around
	 * array objects in the staging area when indexing them (see {@link _Memory}
	 * for an explanation of what a staging area is). If destructive burning is
	 * not allowed, a new array is created with only unique array objects in the
	 * proper order (as specified by the index), and that is the array that is
	 * burnt to the OpenGL buffer. The staging area remains exactly as it was
	 * before burning, duplicates and all. When destructive burning is allowed,
	 * duplicate array objects will be removed from the staging area and which
	 * the remaining array objects will be packed together again. The staging
	 * area is itself burnt to OpenGL (no new array is created).
	 * 
	 * @return Whether or not the burn process is allowed to alter the staging
	 * area.
	 * 
	 * @see #burn()
	 */
	boolean isDestructive();

	/**
	 * Enable or disable destructive burning.
	 * 
	 * @param b
	 * {@code true} to enable, {@code false} to disable.
	 * 
	 * @see #isDestructive()
	 */
	void setDestructive(boolean b);
	
	/**
	 * Purge duplicates from the staging area and pack the remaining array
	 * objects. Always destructive of course.
	 */
	void pack();
	
	ShaderInput burnUnique();

}