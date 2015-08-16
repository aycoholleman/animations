package org.domainobject.animation.sp.arrayobject;


/**
 * An object that stores array objects and copies them to OpenGL buffers. The
 * process of copying array objects to OpenGL buffers is called "burning" in
 * these packages. Array objects are never burnt directly to the OpenGL buffers.
 * OpenGL buffers are implemented as {@code java.nio.Buffer} objects, and these
 * don't perform well with lots of small {@code put} operations. Therefore the
 * array objects are first stored in simple {@code float} arrays or
 * {@code java.util.Collection} objects, which are then burnt in one shot to
 * OpenGL memory. Memory objects are intended to be long-lived objects, catering
 * for considerable amounts of array objects. Instantiating them is expensive as
 * it involves creating the arrays or {@code Collection} objects for temporarily
 * storing the array objects as well as {@code java.nio.Buffer}s to burn the
 * array objects to.
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
	 * The array object passed to this method is not touched.
	 * 
	 * @param arrayObject
	 * The array object to copy into memory
	 */
	void add(T arrayObject);

	/**
	 * 
	 * @return
	 */
	ShaderInput burn();

	void clear();

}