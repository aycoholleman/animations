package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createFloatBuffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Iterator;

/**
 * Fast indexed memory indexes an array object as soon as it is added rather
 * than just before the memory object is burnt to an OpenGL array buffer
 * (GL_ARRAY_BUFFER) and an element array buffer (GL_ELEMENT_ARRAY_BUFFER).
 * 
 * @author Ayco
 *
 * @param <T>
 *            The type of array object hosted by this memory object.
 * 
 * @see LazyIndexedMemory
 */
public abstract class FastIndexedMemory<T extends ArrayObject> {

	private class Committable implements _Committable {
		public void commit(ArrayObject caller)
		{
			T[] p;
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

	/* Backbone of the array objects in this memory object */
	private final float[] raw;
	/* The GL_ELEMENT_ARRAY_BUFFER */
	private final ByteBuffer idxBuf;
	/* The GL_ARRAY_BUFFER */
	private final FloatBuffer objBuf;
	/* Number of elements per array object */
	private final int objSize;
	/* Reflectionless constructor of T instances */
	private final _Constructor<T> constructor;
	/*
	 * Passed on to array objects, so they can commit themselves to this memory
	 * object
	 */
	private final Committable committable = new Committable();
	private final _FastIndexer<T> indexer;

	// Contains the uncommitted array objects created through make().
	private T[] pending;


	FastIndexedMemory(int maxNumObjs, int objSize, boolean forceIntIndices)
	{
		this.objSize = objSize;
		this.constructor = getConstructor();
		this.raw = new float[maxNumObjs * objSize];
		this.objBuf = createFloatBuffer(raw.length);
		if (forceIntIndices || maxNumObjs > Short.MAX_VALUE)
			indexer = new FastIntIndexer<>(maxNumObjs);
		else if (maxNumObjs > Byte.MAX_VALUE)
			indexer = new FastShortIndexer<>(maxNumObjs);
		else
			indexer = new FastByteIndexer<>(maxNumObjs);
		this.idxBuf = indexer.createIndicesBuffer();
	}


	public Class<?> getIndexType()
	{
		return indexer.getIndexType();
	}


	public int size()
	{
		return indexer.countObjects();
	}


	public void add(T object)
	{
		pending = null;
		if (!indexer.index(object)) {
			T copy = allocate();
			object.copyTo(copy);
			indexer.add(copy);
		}
	}


	public void addUnchecked(T object)
	{
		pending = null;
		T copy = allocate();
		object.copyTo(copy);
		indexer.add(copy);
	}


	public T alloc()
	{
		return alloc(1)[0];
	}


	public T[] alloc(int howmany)
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
		T[] tmp = pending;
		pending = null;
		if (which.length == 0) {
			for (T t : tmp)
				if (t != null)
					add(t);
		}
		else {
			for (int i : which)
				if (tmp[i] != null)
					add(tmp[i]);
		}
	}


	public boolean contains(T arrayObject)
	{
		return indexer.contains(arrayObject);
	}


	public ShaderInput burn()
	{
		pending = null;
		if (indexer.countObjects() == 0)
			MemoryException.cannotBurnWhenEmpty();
		objBuf.put(raw, 0, indexer.countObjects() * objSize);
		objBuf.flip();
		idxBuf.clear();
		indexer.burnIndices(idxBuf);
		idxBuf.flip();
		return new ShaderInput(objBuf, idxBuf);
	}


	public void clear()
	{
		pending = null;
		idxBuf.clear();
		objBuf.clear();
		indexer.clear();
	}


	public Iterator<T> iterator()
	{
		return indexer.iterator();
	}


	abstract _Constructor<T> getConstructor();


	private T allocate()
	{
		return constructor.make(raw, indexer.countObjects() * objSize);
	}

}