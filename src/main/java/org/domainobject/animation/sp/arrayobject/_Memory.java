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
 * The type of array object held in memory
 */
public interface _Memory<T extends ArrayObject> extends Iterable<T> {

	/**
	 * Returns the number of array objects stored in memory.
	 * 
	 * @return The number of array objects stored in memory
	 */
	int size();

	/**
	 * Adds a <i>copy</i> of the specified array object to this memory object.
	 * The array object passed to this method is read only.
	 * 
	 * @param arrayObject
	 * The array object to copy into memory
	 */
	void add(T arrayObject);

	/**
	 * Creates a new array object of type T. The new array object will occupy a
	 * small portion of this memory object's backing array. Thus, you will
	 * receive a new instance of type T, but no array creation or array copying
	 * takes places. With the "fast" implementations of {@code _Memory} you
	 * <b>must</b> call {@link ArrayObject#commit()} on the array objects
	 * created through {@code newInstance()}. Otherwise they will not be visible
	 * to the burn process. Also, the memory's object counter (reflected by the
	 * {@link #size()} method) will not be updated until you call
	 * {@code ArrayObject.commit()}. When using the "lazy" or "non-indexed"
	 * implementations of {@code _Memory} you don't need to call
	 * {@code ArrayObject.commit()}. It's a no-op in that case (as is
	 * {@link ArrayObject#discard()}).
	 * 
	 * @return A new array object of type T
	 * 
	 * @see #commit()
	 * @see #burn()
	 * @see #discard()
	 * 
	 */
	T make();


	/**
	 * Burns the array objects to OpenGL memory, creating an OpenGL array buffer
	 * ({@code GL_ARRAY_BUFFER}) and, in the case of {@link _IndexedMemory
	 * indexed memory}, also an OpenGL element array buffer (
	 * {@code GL_ELEMENT_ARRAY_BUFFER}).
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