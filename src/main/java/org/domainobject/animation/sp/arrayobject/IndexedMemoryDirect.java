package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createFloatBuffer;

import java.nio.FloatBuffer;
import java.util.Iterator;

/**
 * Fast indexed memory indexes an vertex as soon as it is committed to
 * memory. In other words, as soon as the vertex is committed, a check is
 * done whether an equivalent vertex already exists in memory. If so, the
 * vertex itself is discarded and the commit action will only result in
 * the addition of a new index pointing to the vertex that is already in
 * memory.
 * 
 * @author Ayco Holleman
 *
 * @param <VERTEX>
 *            The type of vertex hosted by this memory object.
 * 
 * @see IndexedMemoryLazy
 */
public abstract class IndexedMemoryDirect<VERTEX extends Vertex> implements IIndexedMemory<VERTEX> {

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

	/* The GL_ARRAY_BUFFER */
	private final FloatBuffer vBuf;
	/* Number of elements per vertex */
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

	IndexedMemoryDirect(IFastIndexer<VERTEX> indexer, int vertexSize)
	{
		this.indexer = indexer;
		this.vSize = vertexSize;
		this.vBuf = createFloatBuffer(indexer.getMaxNumVertices() * vertexSize);
		this.constructor = getConstructor();
	}


	@Override
	public IndexType getIndexType()
	{
		return indexer.getIndexType();
	}
	
	@Override
	public int numVertices()
	{
		return indexer.numVertices();
	}

	@Override
	public int numIndices()
	{
		return indexer.numIndices();
	}

	@Override
	public void add(VERTEX obj)
	{
		pending = null;
		if (!indexer.index(obj)) {
			vBuf.put(obj.components, obj.offset, vSize);
			VERTEX copy = create();
			obj.copyTo(copy);
			indexer.add(copy);
		}
	}

	@Override
	public void absorb(VERTEX obj)
	{
		pending = null;
		if (!indexer.index(obj)) {
			vBuf.put(obj.components);
			indexer.add(obj);
		}
	}

	public void addUnchecked(VERTEX obj)
	{
		pending = null;
		vBuf.put(obj.components);
	}

	@Override
	public VERTEX alloc()
	{
		return alloc(1)[0];
	}

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

	/* (non-Javadoc)
	 * @see org.domainobject.animation.sp.arrayobject.IIndexedMemory#commit(int)
	 */
	@Override
	public void commit(int... which)
	{
		VERTEX[] tmp = pending;
		pending = null;
		if (which.length == 0) {
			for (VERTEX t : tmp) {
				if (t != null) {
					absorb(t);
				}
			}
		}
		else {
			for (int i : which) {
				if (tmp[i] != null) {
					absorb(tmp[i]);
				}
			}
		}
	}

	@Override
	public boolean contains(VERTEX arrayObject)
	{
		return indexer.contains(arrayObject);
	}

	@Override
	public ShaderInput burn()
	{
		pending = null;
		if (indexer.numVertices() == 0)
			MemoryException.cannotBurnWhenEmpty();
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

	private VERTEX create()
	{
		return constructor.make(new float[vSize], 0);
	}

}