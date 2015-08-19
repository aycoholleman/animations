package org.domainobject.animation.sp.arrayobject;


/**
 * An object that stores array objects and copies them to OpenGL buffers. The
 * process of copying array objects to OpenGL buffers goes by the name of
 * "burning" in this library. Array objects are never burnt directly to the
 * OpenGL buffers. OpenGL buffers are implemented as {@code java.nio.Buffer}
 * objects, which are relatively ineffecient for small {@code put} operations.
 * Therefore the array objects are first staged in plain arrays and/or
 * {@code java.util.Collection} objects, which are then written in one shot to
 * OpenGL memory. Memory objects are intended to be long-lived objects, catering
 * for considerable amounts of array objects. Instantiating them is expensive as
 * it involves the creation of the arrays and/or {@code Collection} objects for
 * staging the array objects as well as the creation of {@code java.nio.Buffer}s
 * to burn the array objects to.
 * 
 * @author Ayco Holleman
 *
 * @param <T>
 * The type of array object stored
 */
interface _Memory<T extends ArrayObject> extends Iterable<T> {

	/**
	 * Get the number of array objects stored in memory.
	 * 
	 * @return The number of array objects stored in memory
	 */
	int size();

	/**
	 * Add a <i>copy</i> of the specified array object to this memory object.
	 * The array object passed to this method is read only.
	 * 
	 * @param arrayObject
	 * The array object to copy into memory
	 */
	void add(T arrayObject);

	/**
	 * Create a new array object of type T. The new array object will occupy a
	 * small portion of this memory object's backing array. Thus, you will
	 * receive a new instance of type T, but no array creation or array copying
	 * takes places. With the "fast" implementations of {@code _Memory} you
	 * <i>must</i> call {@link #commit()} before array objects created through
	 * {@code newInstance} become visible to the burn process. In other words,
	 * if you don't call {@code commit}, they won't get burnt to OpenGL memory.
	 * Also, the object counter (reflected by the {@link #size()} method) won't
	 * be updated until you call {@code commit}. For the "lazy" and
	 * "non-indexed" implementations of {@code _Memory} the {@code commit}
	 * method is a no-op. With them, you have in fact no way of preventing array
	 * objects, once added, from being burnt because the {@link #discard()} is
	 * also a no-op.
	 * 
	 * @return A new array object of type T
	 * 
	 * @see #commit()
	 * @see #burn()
	 * @see #discard()
	 * 
	 */
	T newInstance();

	/**
	 * Commit all array objects created through {@link #newInstance()} to
	 * memory, thereby increasing the object counter and making them visible to
	 * the {@link #burn()} method.
	 * 
	 * @see #discard()
	 */
	void commit();

	/**
	 * Discard all uncommitted array objects.
	 * 
	 * @see #newInstance()
	 * @see #commit()
	 */
	void discard();

	/**
	 * Burn the internal arrays and/or {@code Collection} objects to OpenGL
	 * memory. Whether or not these internal structures still are still intact
	 * after burning is implementation-dependent.
	 * 
	 * @return
	 */
	ShaderInput burn();

	/**
	 * Put the memory object into a state where you can start adding array
	 * objects again as though the memory object was just created. Most likely
	 * this will just reset some internal counters without actually clearing the
	 * internal structures, but this is implementation-dependent.
	 */
	void clear();

}