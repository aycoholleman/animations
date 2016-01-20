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
public abstract class IndexedMemoryDirect<ARRAY_OBJECT extends ArrayObject> {

	private class Committable implements ICommittable {

		public void commit(ArrayObject caller)
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

	IndexedMemoryDirect(int maxNumObjs, int objSize, boolean useIntIndices)
	{
		this.objSize = objSize;
		this.constructor = getConstructor();
		this.objBuf = createFloatBuffer(maxNumObjs * objSize);
		indexer = new FastIntIndexer<>(maxNumObjs);
		//indexer = new FastShortIndexer<>(maxNumObjs);
		//indexer = new FastByteIndexer<>(maxNumObjs);
		//		if (useIntIndices || maxNumObjs > Short.MAX_VALUE)
		//			indexer = new FastIntIndexer<>(maxNumObjs);
		//		else if (maxNumObjs > Byte.MAX_VALUE)
		//			indexer = new FastShortIndexer<>(maxNumObjs);
		//		else
		//			indexer = new FastByteIndexer<>(maxNumObjs);
	}

	public Class<?> getIndexType()
	{
		return indexer.getIndexType();
	}

	/**
	 * Returns the number of <i>unique</i> array objects in memory.
	 * 
	 * @return
	 */
	public int numObjs()
	{
		return indexer.numObjs();
	}

	/**
	 * Returns the number of array objects submitted to memory. Every submission
	 * using {@link #add(ArrayObject) add} or {@link #commit(int...) commit}
	 * results in a new index added to the element array buffer, but only
	 * <i>unique</i> array objects are actually stored in memory.
	 * 
	 * @return
	 */
	public int numIndices()
	{
		return indexer.numIndices();
	}

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
	public void add(ARRAY_OBJECT obj)
	{
		pending = null;
		if (!indexer.index(obj)) {
			objBuf.put(obj.components);
			ARRAY_OBJECT copy = create();
			obj.copyTo(copy);
			indexer.add(copy);
		}
	}

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

	/**
	 * Creates a new array object of the type stored by this memory instance,
	 * but does not yet add or commit it to this memory instance. Calling
	 * {@link ArrayObject#commit() commit} on the array object will, however,
	 * commit it to <i>this</i> memory instance.
	 * 
	 * @return
	 */
	public ARRAY_OBJECT alloc()
	{
		return alloc(1)[0];
	}

	/**
	 * Creates the specified amount of array objects but does not yet add or
	 * commit them to this memory instance. See {@link #alloc() alloc}.
	 * 
	 * @param howmany
	 * @return
	 */
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

	public boolean contains(ARRAY_OBJECT arrayObject)
	{
		return indexer.contains(arrayObject);
	}

	public ShaderInput burn()
	{
		pending = null;
		if (indexer.numObjs() == 0)
			MemoryException.cannotBurnWhenEmpty();
		objBuf.flip();
		return new ShaderInput(objBuf, indexer.burnIndices());
	}

	public void clear()
	{
		pending = null;
		objBuf.clear();
		indexer.clear();
	}

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