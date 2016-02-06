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
 * @param <ARRAY_OBJECT>
 *            The type of array object hosted by this memory object.
 * 
 * @see IndexedMemoryLazy
 */
public abstract class IndexedMemoryDirect<ARRAY_OBJECT extends Vertex> implements IIndexedMemory<ARRAY_OBJECT> {

	private class Committable implements ICommittable {

		public void commit(Vertex caller)
		{
			ARRAY_OBJECT[] p;
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
	private final FloatBuffer objBuf;
	/* Number of elements per array object */
	private final int objSize;
	/* Reflectionless constructor of various types of array objects */
	private final IConstructor<ARRAY_OBJECT> constructor;
	/*
	 * Passed on to array objects, so they can commit themselves to this memory
	 * object
	 */
	private final Committable committable = new Committable();
	private final IFastIndexer<ARRAY_OBJECT> indexer;

	// Contains the uncommitted array objects created through make().
	private ARRAY_OBJECT[] pending;

	IndexedMemoryDirect(IFastIndexer<ARRAY_OBJECT> indexer, int objSize)
	{
		this.objSize = objSize;
		this.constructor = getConstructor();
		this.objBuf = createFloatBuffer(indexer.getMaxNumIndices() * objSize);
		this.indexer = indexer;
	}

	public Class<?> getIndexType()
	{
		return indexer.getIndexType();
	}

	/* (non-Javadoc)
	 * @see org.domainobject.animation.sp.arrayobject.IIndexedMemory#numObjs()
	 */
	@Override
	public int numObjs()
	{
		return indexer.numVertices();
	}

	/* (non-Javadoc)
	 * @see org.domainobject.animation.sp.arrayobject.IIndexedMemory#numIndices()
	 */
	@Override
	public int numIndices()
	{
		return indexer.numIndices();
	}

	/* (non-Javadoc)
	 * @see org.domainobject.animation.sp.arrayobject.IIndexedMemory#add(ARRAY_OBJECT)
	 */
	@Override
	public void add(ARRAY_OBJECT obj)
	{
		pending = null;
		if (!indexer.index(obj)) {
			objBuf.put(obj.components, obj.offset, objSize);
			ARRAY_OBJECT copy = create();
			obj.copyTo(copy);
			indexer.add(copy);
		}
	}

	/* (non-Javadoc)
	 * @see org.domainobject.animation.sp.arrayobject.IIndexedMemory#absorb(ARRAY_OBJECT)
	 */
	@Override
	public void absorb(ARRAY_OBJECT obj)
	{
		pending = null;
		if (!indexer.index(obj)) {
			objBuf.put(obj.components);
			indexer.add(obj);
		}
	}

	public void addUnchecked(ARRAY_OBJECT obj)
	{
		pending = null;
		objBuf.put(obj.components);
	}

	/* (non-Javadoc)
	 * @see org.domainobject.animation.sp.arrayobject.IIndexedMemory#alloc()
	 */
	@Override
	public ARRAY_OBJECT alloc()
	{
		return alloc(1)[0];
	}

	/* (non-Javadoc)
	 * @see org.domainobject.animation.sp.arrayobject.IIndexedMemory#alloc(int)
	 */
	@Override
	public ARRAY_OBJECT[] alloc(int howmany)
	{
		float[] tmp = new float[howmany * objSize];
		pending = constructor.array(howmany);
		for (int i = 0; i < howmany; i++) {
			pending[i] = constructor.make(tmp, i * objSize);
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
		ARRAY_OBJECT[] tmp = pending;
		pending = null;
		if (which.length == 0) {
			for (ARRAY_OBJECT t : tmp) {
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
	public boolean contains(ARRAY_OBJECT arrayObject)
	{
		return indexer.contains(arrayObject);
	}

	@Override
	public ShaderInput burn()
	{
		pending = null;
		if (indexer.numVertices() == 0)
			MemoryException.cannotBurnWhenEmpty();
		objBuf.flip();
		return new ShaderInput(objBuf, indexer.burnIndices());
	}

	@Override
	public void clear()
	{
		pending = null;
		objBuf.clear();
		indexer.clear();
	}

	@Override
	public Iterator<ARRAY_OBJECT> iterator()
	{
		return indexer.iterator();
	}

	abstract IConstructor<ARRAY_OBJECT> getConstructor();

	private ARRAY_OBJECT create()
	{
		return constructor.make(new float[objSize], 0);
	}

}