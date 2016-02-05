package org.domainobject.animation.sp.arrayobject;

import java.util.Iterator;

public interface IIndexedMemory<ARRAY_OBJECT extends ArrayObject> {

	/**
	 * Returns the number of <i>unique</i> array objects in memory.
	 * 
	 * @return
	 */
	int numObjs();

	/**
	 * Returns the number of array objects submitted to memory. Every submission
	 * using {@link #add(ArrayObject) add} or {@link #commit(int...) commit}
	 * results in a new index added to the element array buffer, but only
	 * <i>unique</i> array objects are actually stored in memory.
	 * 
	 * @return
	 */
	int numIndices();

	/**
	 * Submits the specified array object to this memory instance. If an
	 * equivalent array object is already present in this memory instance, the
	 * array object is discarded, but a new index, pointing to the array object
	 * in memory, is added to the indices array. Otherwise, the array object is
	 * burnt directly to the {@code FloatBuffer} functioning as the
	 * GL_ARRAY_BUFFER. You are free to re-use and change the submitted array
	 * object afterwards, because the internally maintained {@link java.util.Map
	 * Map} used to check the uniqueness of the provided array object will store
	 * a copy of the array object rather than the array object itself.
	 * 
	 * @param obj
	 */
	void add(ARRAY_OBJECT obj);

	/**
	 * Submits the specified array object to this memory instance. If an
	 * equivalent array object is already present in this memory instance, the
	 * array object is discarded, but a new index, pointing to the array object
	 * in memory, is added to the indices array. Otherwise, the array object is
	 * burnt directly to the {@code FloatBuffer} functioning as the
	 * GL_ARRAY_BUFFER. You SHOULD NOT change the submitted array object
	 * afterwards, because the internal {@link java.util.Map Map} used to check
	 * the uniqueness of the provided array object will store the array object
	 * itself rather than a copy of it. Thus, changing the the array object
	 * afterwards interferes with the uniqueness checks done by this memory
	 * instance.
	 * 
	 * @param obj
	 */
	void absorb(ARRAY_OBJECT obj);

	/**
	 * Creates a new array object of the type stored by this memory instance,
	 * but does not yet add or commit it to this memory instance. Calling
	 * {@link ArrayObject#commit() commit} on the array object will, however,
	 * commit it to <i>this</i> memory instance.
	 * 
	 * @return
	 */
	ARRAY_OBJECT alloc();

	/**
	 * Creates the specified amount of array objects but does not yet add or
	 * commit them to this memory instance. See {@link #alloc() alloc}.
	 * 
	 * @param howmany
	 * @return
	 */
	ARRAY_OBJECT[] alloc(int howmany);

	void commit(int... which);

	boolean contains(ARRAY_OBJECT arrayObject);

	ShaderInput burn();

	void clear();

	Iterator<ARRAY_OBJECT> iterator();

}