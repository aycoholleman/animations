package org.domainobject.animation.sp.arrayobject;

/**
 * <p>
 * A type of indexed memory that indexes array objects the moment you add them.
 * This is what the "fast" part of the name refers to, rather than its speed.
 * Especially if you expect to add a lot of non-unique array objects, this type
 * of memory will take up less application memory than the "lazy" memory
 * variant, because adding a non-unique array object will only result in the
 * index being updated. The array object itself is discarded.
 * <p>
 * Contrary to plain (non-indexed) and "lazy" memory, you <b>must</b> call
 * {@link ArrayObject#commit() commit} on an array object created through the
 * {@link newInstance() newInstance} method. Otherwise it will not be visible to
 * the burn process. Also, a fast memory's object counter (reflected by the
 * {@link #size() size} method) will not be updated until you call
 * {@code commit} on the array object. You must call {@code commit} before you
 * call {@code newInstance} or {@link #add(ArrayObject) add} again. Otherwise
 * the array object will simply be overwritten.
 * 
 * @author Ayco Holleman
 *
 * @param <T>
 * The type of array object held in memory
 */
public interface _IndexedMemoryFast<T extends ArrayObject> extends _IndexedMemory<T> {

	/**
	 * Adds the specified array object under the assumption that it is unique.
	 * This saves a hash table lookup to see if an equivalent array object
	 * already exists in memory, which might be advantageous for very large
	 * memory objects. Only use this method if you <i>know</i> the array object
	 * to be distinguishable from all other array objects in memory. Adding
	 * non-unique array objects has undefined consequences. Like with the
	 * {@link #add(ArrayObject)} method, it's a copy of the specified array
	 * object that is added to memory, not the array object itself.
	 * 
	 * @param arrayObject
	 * 
	 * @see #add(ArrayObject)
	 */
	void addUnchecked(T arrayObject);

	/**
	 * Returns {@code true} if this memory object contains the specified array
	 * object.
	 * 
	 * @param arrayObject
	 * @return
	 */
	boolean contains(T arrayObject);

}