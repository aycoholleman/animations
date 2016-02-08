package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createFloatBuffer;

import java.nio.FloatBuffer;
import java.util.Iterator;

/**
 * Fast indexed memory indexes an array object as soon as it is committed to
 * memory. In other words, as soon as the array object is committed, a check is
 * done whether an equivalent array object already exists in memory. If so, the
 * array object itself is discarded and the commit action will only result in
 * the addition of a new index pointing to the array object that is already in
 * memory.
 * 
 * @author Ayco Holleman
 *
 * @param <VERTEX>
 *            The type of array object hosted by this memory object.
 * 
 * @see IndexedMemoryLazy
 */
public abstract class IndexedMemoryFast<VERTEX extends Vertex> implements IIndexedMemory<VERTEX> {

	private class Committable implements ICommittable {

		public void commit(Vertex caller)
		{
			VERTEX[] p;
			if ((p = pending) == null)
				MemoryException.commitWindowClosed();
			for (int i = 0; i < p.length; i++) {
				if (p[i] == caller) {
					if (!indexer.index(p[i]))
						indexer.add(p[i]);
					p[i] = null;
					return;
				}
			}
			MemoryException.alreadyCommitted();
		}
	}

	/* Backbone for the vertices managed by this memory object */
	private final float[] raw;
	/* The GL_ARRAY_BUFFER */
	private final FloatBuffer vBuf;
	/* Number of elements per array object */
	private final int vSize;
	/* Reflectionless constructor of various types of vertices */
	private final IConstructor<VERTEX> constructor;
	/*
	 * Passed on to vertices, so they can commit themselves to this memory
	 * object
	 */
	private final Committable committable = new Committable();
	private final IFastIndexer<VERTEX> indexer;

	// Contains the uncommitted vertices created through make().
	private VERTEX[] pending;

	IndexedMemoryFast(IFastIndexer<VERTEX> indexer, int vertexSize)
	{
		this.vSize = vertexSize;
		this.constructor = getConstructor();
		this.raw = new float[indexer.getMaxNumVertices() * vertexSize];
		this.vBuf = createFloatBuffer(raw.length);
		this.indexer = indexer;
	}

	@Override
	public IndexType getIndexType()
	{
		return indexer.getIndexType();
	}

	/**
	 * Returns the number of <i>unique</i> vertices in memory.
	 * 
	 * @return
	 */
	@Override
	public int numVertices()
	{
		return indexer.numVertices();
	}

	/**
	 * Returns the number of vertices submitted to memory. Every submission
	 * using {@link #add(Vertex) add} or {@link #commit(int...) commit} results
	 * in a new index added to the element array buffer, but only <i>unique</i>
	 * vertices are actually stored in memory.
	 * 
	 * @return
	 */
	@Override
	public int numIndices()
	{
		return indexer.numIndices();
	}

	/**
	 * Submits the specified array object to this memory instance. If an
	 * equivalent array object is already present in this memory instance, the
	 * array object is discarded, but a new index, pointing to the array object
	 * in memory, is added to the indices array. Otherwise, a <i>copy</i> of the
	 * specified array object is added to this memory instance. You are free to
	 * re-use and change the submitted array object afterwards.
	 * 
	 * @param obj
	 */
	@Override
	public void add(VERTEX obj)
	{
		pending = null;
		if (!indexer.index(obj)) {
			VERTEX copy = newVertex();
			obj.copyTo(copy);
			indexer.add(copy);
		}
	}

	/**
	 * Submits the specified array object to this memory instance. Contrary to
	 * {@link IndexedMemoryDirect} this method works exactly like the
	 * {@link #add(Vertex) add} method in that it is always a copy of the array
	 * object that is added to memory, never the array object itself. Therefore,
	 * whether you use {@link #absorb(Vertex) absorb} or {@link #add(Vertex)
	 * add}, in both cases you are are free to re-use and change the submitted
	 * array object afterwards.
	 * 
	 * @param v
	 */
	@Override
	public void absorb(VERTEX v)
	{
		add(v);
	}

	public void addUnchecked(VERTEX v)
	{
		pending = null;
		VERTEX copy = newVertex();
		v.copyTo(copy);
		indexer.add(copy);
	}

	/**
	 * Creates a new array object of the type stored by this memory instance,
	 * but does not yet add or commit it to this memory instance. Calling
	 * {@link Vertex#commit() commit} on the array object will, however, commit
	 * it to <i>this</i> memory instance.
	 * 
	 * @return
	 */
	@Override
	public VERTEX alloc()
	{
		return alloc(1)[0];
	}

	/**
	 * Creates the specified amount of vertices but does not yet add or
	 * commit them to this memory instance. See {@link #alloc() alloc}.
	 * 
	 * @param howmany
	 * @return
	 */
	@Override
	public VERTEX[] alloc(int howmany)
	{
		float[] tmp = new float[howmany * vSize];
		pending = constructor.array(howmany);
		for (int i = 0; i < howmany; i++) {
			pending[i] = constructor.make(tmp, i * vSize);
			pending[i].memory = committable;
		}
		return pending;
	}

	@Override
	public void commit(int... which)
	{
		VERTEX[] tmp = pending;
		pending = null;
		if (which.length == 0) {
			for (VERTEX t : tmp) {
				if (t != null) {
					add(t);
				}
			}
		}
		else {
			for (int i : which) {
				if (tmp[i] != null) {
					add(tmp[i]);
				}
			}
		}
	}

	@Override
	public boolean contains(VERTEX v)
	{
		return indexer.contains(v);
	}

	@Override
	public ShaderInput burn()
	{
		pending = null;
		if (indexer.numVertices() == 0)
			MemoryException.cannotBurnWhenEmpty();
		vBuf.clear();
		vBuf.put(raw, 0, indexer.numVertices() * vSize);
		vBuf.flip();
		return new ShaderInput(vBuf, indexer.burnIndices());
	}

	@Override
	public void clear()
	{
		pending = null;
		vBuf.clear();
		indexer.clear();
	}

	@Override
	public Iterator<VERTEX> iterator()
	{
		return indexer.iterator();
	}

	abstract IConstructor<VERTEX> getConstructor();

	private VERTEX newVertex()
	{
		return constructor.make(raw, indexer.numVertices() * vSize);
	}

}