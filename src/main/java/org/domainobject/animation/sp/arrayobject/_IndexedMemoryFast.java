package org.domainobject.animation.sp.arrayobject;


public interface _IndexedMemoryFast<T extends ArrayObject> extends _IndexedMemory<T> {

	/**
	 * Add the specified array object under the assumption that it is unique.
	 * This saves a hash table lookup to see if an equivalent array object
	 * already exists in memory, which might be advantageous for very large
	 * memory objects. Only use this method if you <i>know</i> the array object
	 * to be distinguishable from all other array objects in memory. Otherwise
	 * use {@link #add(ArrayObject)}.
	 * 
	 * @param arrayObject
	 */
	void addUnique(T arrayObject);

	boolean contains(T arrayObject);

}