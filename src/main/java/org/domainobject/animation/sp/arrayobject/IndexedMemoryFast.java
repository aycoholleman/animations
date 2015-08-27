package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;


public abstract class IndexedMemoryFast<T extends ArrayObject, U> implements _IndexedMemoryFast<T> {

	private class Commitable implements _Commitable {
		public void commit(ArrayObject caller)
		{
			T[] p;
			if ((p = pending) == null)
				MemoryException.commitWindowClosed();
			for (int i = 0; i < p.length; i++) {
				if (p[i] == caller) {
					index(p[i]);
					p[i] = null;
					break;
				}
			}
			MemoryException.alreadyCommitted();
		}
	}

	// Maps array objects to the index at which they are stored in
	// the array of indices (maintained by the sublasses). When an array object
	// is added or committed to memory, its uniqueness is first checked through a
	// lookup on this map. If it is not unique, it is discarded and only the
	// index of the array object of which it is a duplicate is added to the array
	// of indices.
	private final LinkedHashMap<T, U> objs;
	// The raw array of float elements functioning as the backbone of the
	// individual array objects.
	private final float[] raw;
	// The thing that becomes the GL_ARRAY_BUFFER
	private final FloatBuffer objBuf;
	// The number of elements per array object of type T
	private final int objSize;
	// Allows us to construct objects of type T without reflection.
	private final _Constructor<T> constructor;
	// Passed on to array objects created through make(), so they can commit
	// themselves to this memory object.
	private final Commitable commitable = new Commitable();

	// The number of array objects. Equal to the number of elements in the array
	// of indices.
	private int numObjs;
	// Contains the uncommitted array objects created through make().
	private T[] pending;


	IndexedMemoryFast(int maxNumObjects, int objSize)
	{
		this.constructor = getConstructor();
		this.objSize = objSize;
		objs = new LinkedHashMap<>(maxNumObjects, 1.0f);
		raw = new float[maxNumObjects * objSize];
		objBuf = createFloatBuffer(raw.length);
	}


	@Override
	public int size()
	{
		return numObjs;
	}

	@Override
	public void add(T obj)
	{
		pending = null;
		U idx = objs.get(obj);
		if (idx == null) {
			T copy = constructor.make(raw, numObjs * objSize);
			obj.copyTo(copy);
			addNewArrayObject(objs, copy, numObjs);
		}
		else {
			indexDuplicate(numObjs, idx);
		}
		numObjs++;
	}

	@Override
	public void addUnique(T obj)
	{
		pending = null;
		T copy = constructor.make(raw, numObjs * objSize);
		obj.copyTo(copy);
		addNewArrayObject(objs, copy, numObjs);
		numObjs++;
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
			pending[i] = constructor.make(raw, ((numObjs + i) * objSize));
			pending[i].commitable = commitable;
		}
		return pending;
	}

	public void commit()
	{
		T[] tmp = pending;
		pending = null;
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i] != null) {
				index(tmp[i]);
			}
		}
	}

	@Override
	public boolean contains(T arrayObject)
	{
		return objs.keySet().contains(arrayObject);
	}

	@Override
	public ShaderInput burn()
	{
		pending = null;
		if (numObjs == 0)
			MemoryException.cannotBurnWhenEmpty();
		objBuf.clear();
		objBuf.put(raw, 0, numObjs * objSize);
		objBuf.flip();
		return new ShaderInput(objBuf, bufferIndices(numObjs));
	}

	@Override
	public void clear()
	{
		numObjs = 0;
		objs.clear();
		pending = null;
	}

	@Override
	public Iterator<T> iterator()
	{
		return objs.keySet().iterator();
	}

	private void index(T obj)
	{
		U idx = objs.get(obj);
		if (idx == null) {
			addNewArrayObject(objs, obj, numObjs);
		}
		else {
			indexDuplicate(numObjs, idx);
		}
		numObjs++;
	}

	abstract _Constructor<T> getConstructor();


	abstract void addNewArrayObject(LinkedHashMap<T, U> objs, T obj, int numObjs);

	abstract void indexDuplicate(int numObjs, U index);

	abstract ByteBuffer bufferIndices(int numIndices);
	
	abstract void clearIndicesBuffer();

}