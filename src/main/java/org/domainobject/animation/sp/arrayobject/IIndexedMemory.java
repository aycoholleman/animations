package org.domainobject.animation.sp.arrayobject;

import java.util.Iterator;

public interface IIndexedMemory<VERTEX extends Vertex> {

	IndexType getIndexType();

	/**
	 * Returns the number of <i>unique</i> vertices in memory.
	 * 
	 * @return
	 */
	int numVertices();

	/**
	 * Returns the number of vertices submitted to memory. Every submission
	 * using {@link #add(Vertex) add} or {@link #commit(int...) commit} results
	 * in a new index added to the element array buffer, but only <i>unique</i>
	 * vertices are actually stored in memory.
	 * 
	 * @return
	 */
	int numIndices();

	/**
	 * Submits the specified vertex to this memory instance. If an equivalent
	 * vertex is already present in this memory instance, the vertex is
	 * discarded, but a new index, pointing to the vertex in memory, is added to
	 * the indices array. Otherwise, the vertex is burnt directly to the
	 * {@code FloatBuffer} functioning as the GL_ARRAY_BUFFER. You are free to
	 * re-use and change the submitted array object afterwards, because the
	 * internally maintained {@link java.util.Map Map} used to check the
	 * uniqueness of the provided vertex will store a copy of the vertex rather
	 * than the vertex itself.
	 * 
	 * @param obj
	 */
	void add(VERTEX obj);

	/**
	 * Submits the specified vertex to this memory instance. If an equivalent
	 * vertex is already present in this memory instance, the vertex is
	 * discarded, but a new index, pointing to the vertex in memory, is added to
	 * the indices array. Otherwise, the vertex is burnt directly to the
	 * {@code FloatBuffer} functioning as the GL_ARRAY_BUFFER. You SHOULD NOT
	 * change the submitted vertex afterwards, because the internal
	 * {@link java.util.Map Map} used to check the uniqueness of the provided
	 * vertex will store the vertex itself rather than a copy of it. Thus,
	 * changing the the vertex afterwards interferes with the uniqueness checks
	 * done by this memory instance.
	 * 
	 * @param obj
	 */
	void absorb(VERTEX obj);

	/**
	 * Creates a new vertex of the type stored by this memory instance, but does
	 * not yet add or commit it to this memory instance. Calling
	 * {@link Vertex#commit() commit} on the vertex will, however, commit it to
	 * <i>this</i> memory instance.
	 * 
	 * @return
	 */
	VERTEX alloc();

	/**
	 * Creates the specified amount of vertices but does not yet add or commit
	 * them to this memory instance. See {@link #alloc() alloc}.
	 * 
	 * @param howmany
	 * @return
	 */
	VERTEX[] alloc(int howmany);

	void commit(int... which);

	boolean contains(VERTEX arrayObject);

	ShaderInput burn();

	void clear();

	Iterator<VERTEX> iterator();

}