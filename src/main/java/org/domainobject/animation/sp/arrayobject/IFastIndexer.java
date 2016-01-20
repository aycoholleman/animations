package org.domainobject.animation.sp.arrayobject;

import java.nio.Buffer;
import java.util.Iterator;

import org.domainobject.animation.sp.util.Comparators;

/**
 * 
 * @author Ayco Holleman
 *
 * @param <ARRAY_OBJECT>
 */
interface IFastIndexer<ARRAY_OBJECT extends ArrayObject> {

	/**
	 * Returns the type of the indices in the element array buffer. Either {code
	 * int.class} or {code short.class} or {@code byte.class}.
	 * 
	 * @return
	 */
	Class<?> getIndexType();

	boolean contains(ARRAY_OBJECT object);

	boolean index(ARRAY_OBJECT object);

	void add(ARRAY_OBJECT object);

	/**
	 * Returns the number of <b>distinct</b> array objects in memory. If a
	 * non-unique array object is submitted (that it, it {@code equals} one of
	 * the array objects already in memory), it is in fact only indexed. The
	 * array object itself is discarded and a new index is added pointing to the
	 * existing array object.
	 * 
	 * @return
	 * 
	 * @see IndexedMemoryFast#add(ArrayObject)
	 * @see IndexedMemoryFast#commit(int...)
	 * @see Comparators#same3(float[], int, float[], int, float)
	 * @see Comparators#same4(float[], int, float[], int, float) etc.
	 */
	int numObjs();

	/**
	 * Returns the number of objects that were
	 * {@link IndexedMemoryFast#add(ArrayObject) added} or
	 * {@link IndexedMemoryFast#commit(int...) committed} to the memory object
	 * used by this indexer.
	 * 
	 * @return
	 */
	int numIndices();

	Buffer burnIndices();

	void clear();

	Iterator<ARRAY_OBJECT> iterator();

}