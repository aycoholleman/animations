package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Iterator;


public abstract class IndexedMemoryFast<T extends ArrayObject> implements _IndexedMemoryFast<T> {

	private class Commitable implements _Commitable {
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

	// The array objects' backbone
	private final float[] raw;
	// The GL_ELEMENT_ARRAY_BUFFER
	private final ByteBuffer idxBuf;
	// The GL_ARRAY_BUFFER
	private final FloatBuffer objBuf;
	// The number of elements in array objects of type T
	private final int objSize;
	// Reflectionless constructor of T instances
	private final _Constructor<T> constructor;
	// Passed on to array objects created through make(), so they can commit
	// themselves to this memory object.
	private final Commitable commitable = new Commitable();
	private final _FastIndexer<T> indexer;
	
	// Contains the uncommitted array objects created through make().
	private T[] pending;


	IndexedMemoryFast(int maxNumObjs, int objSize, boolean forceIntIndices)
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

	@Override
	public int size()
	{
		return indexer.countObjects();
	}

	@Override
	public void add(T object)
	{
		pending = null;
		if (!indexer.index(object)) {
			T copy = allocate();
			object.copyTo(copy);
			indexer.add(copy);
		}
	}

	@Override
	public void addUnchecked(T object)
	{
		pending = null;
		T copy = allocate();
		object.copyTo(copy);
		indexer.add(copy);
	}

	@Override
	public T make()
	{
		return make(1)[0];
	}

	public T[] make(int howmany)
	{
		pending = constructor.array(howmany);
		for (int i = 0; i < howmany; i++) {
			pending[i] = trespass(i);
			pending[i].commitable = commitable;
		}
		return pending;
	}

	public void commit()
	{
		T[] tmp = pending;
		pending = null;
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i] != null && !indexer.index(tmp[i]))
				indexer.add(tmp[i]);
		}
	}

	@Override
	public boolean contains(T arrayObject)
	{
		return indexer.contains(arrayObject);
	}

	@Override
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

	@Override
	public void clear()
	{
		pending = null;
		idxBuf.clear();
		objBuf.clear();
		indexer.clear();
	}

	@Override
	public Iterator<T> iterator()
	{
		return indexer.iterator();
	}

	abstract _Constructor<T> getConstructor();

	private T allocate()
	{
		return constructor.make(raw, indexer.countObjects() * objSize);
	}

	private T trespass(int offset)
	{
		return constructor.make(raw, (indexer.countObjects() + offset) * objSize);
	}


}