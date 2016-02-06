package org.domainobject.animation.sp.arrayobject;

import java.nio.Buffer;
import java.util.Iterator;

import org.domainobject.animation.sp.util.Comparators;

/**
 * 
 * @author Ayco Holleman
 *
 * @param <VERTEX>
 */
interface IFastIndexer<VERTEX extends Vertex> {

	IndexType getIndexType();

	int getMaxNumVertices();

	boolean contains(VERTEX object);

	boolean index(VERTEX object);

	void add(VERTEX object);

	/**
	 * Returns the number of <b>distinct</b> vertices in memory. If a non-unique
	 * vertex is submitted (that it, it {@code equals} one of the vertices
	 * already in memory), it is in fact only indexed. The vertex itself is
	 * discarded and a new index is added pointing to the existing vertex.
	 * 
	 * @return
	 * 
	 * @see IndexedMemoryFast#add(Vertex)
	 * @see IndexedMemoryFast#commit(int...)
	 * @see Comparators#same3(float[], int, float[], int, float)
	 * @see Comparators#same4(float[], int, float[], int, float) etc.
	 */
	int numVertices();

	/**
	 * Returns the number of objects that were
	 * {@link IndexedMemoryFast#add(Vertex) added} or
	 * {@link IndexedMemoryFast#commit(int...) committed} to the memory object
	 * used by this indexer.
	 * 
	 * @return
	 */
	int numIndices();

	Buffer burnIndices();

	void clear();

	Iterator<VERTEX> iterator();

}
