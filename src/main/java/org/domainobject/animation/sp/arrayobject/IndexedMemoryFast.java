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
					if (indexer.isNew(p[i])) {
						indexer.assignIndexTo(p[i]);
					}
					p[i] = null;
					return;
				}
			}
			MemoryException.alreadyCommitted();
		}
	}

	// The raw array of float elements functioning as the backbone of the
	// individual array objects.
	private final float[] raw;
	// The GL_ELEMENT_ARRAY_BUFFER
	private final ByteBuffer idxBuf;
	// The GL_ARRAY_BUFFER
	private final FloatBuffer objBuf;
	// The number of elements in T's internal array
	private final int objSize;
	// Allows us to construct objects of type T without reflection
	private final _Constructor<T> constructor;
	// Passed on to array objects created through make(), so they can commit
	// themselves to this memory object.
	private final Commitable commitable = new Commitable();

	private final _Indexer<T> indexer;

	// Contains the uncommitted array objects created through make().
	private T[] pending;


	IndexedMemoryFast(int maxNumObjects, int objSize, boolean forceIntIndices)
	{
		this.objSize = objSize;
		raw = new float[maxNumObjects * objSize];
		idxBuf = createByteBuffer(maxNumObjects);
		objBuf = createFloatBuffer(raw.length);
		constructor = getConstructor();
		if (forceIntIndices || maxNumObjects > Short.MAX_VALUE) {
			indexer = null;
		}
		else if (maxNumObjects > Byte.MAX_VALUE) {
			indexer = new ShortIndexer<>(maxNumObjects);
		}
		else {
			indexer = null;
		}
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
		if (indexer.isNew(object)) {
			T copy = allocate();
			object.copyTo(copy);
			indexer.assignIndexTo(copy);
		}
	}

	@Override
	public void addUnchecked(T object)
	{
		pending = null;
		T copy = allocate();
		object.copyTo(copy);
		indexer.assignIndexTo(copy);
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
			if (tmp[i] != null && indexer.isNew(tmp[i]))
				indexer.assignIndexTo(tmp[i]);
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
		indexer.write(idxBuf);
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

	private T allocate()
	{
		return constructor.make(raw, indexer.countObjects() * objSize);
	}

	private T trespass(int offset)
	{
		return constructor.make(raw, (indexer.countObjects() + offset) * objSize);
	}

	abstract _Constructor<T> getConstructor();

}