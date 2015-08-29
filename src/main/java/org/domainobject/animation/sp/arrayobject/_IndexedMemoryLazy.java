package org.domainobject.animation.sp.arrayobject;

/**
 * A type of indexed memory that indexes the array objects stored in it only
 * when it is request to burn the array buffer and element array buffer.
 * 
 * @author Ayco Holleman
 *
 * @param <T>
 * The type of array object held in memory
 */
public interface _IndexedMemoryLazy<T extends ArrayObject> extends _IndexedMemory<T> {

	/**
	 * Whether or not the burn process is allowed to delete and move around array
	 * objects in the staging area when indexing them (see {@link _Memory} for an
	 * explanation of what a staging area is). If destructive burning is not
	 * allowed, a new array is created with only unique array objects in the
	 * proper order (as specified by the index), and that is the array that is
	 * burnt to the OpenGL buffer. The staging area remains exactly as it was
	 * before burning, duplicates and all. When destructive burning is allowed,
	 * no new array is created. Duplicate array objects will be removed from the
	 * staging area and the remaining array objects will be packed together
	 * again. The staging area is itself burnt to OpenGL.
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
	 * Purges duplicate array objects from the staging area and packs the
	 * remaining array objects.
	 */
	void purge();

	/**
	 * Burn this memory object to OpenGL buffers under the assumption that all
	 * array objects contained in it are unique. No attempt will be made to
	 * filter out non-unique array objects.
	 * 
	 * @return
	 */
	ShaderInput burnUnchecked();

}