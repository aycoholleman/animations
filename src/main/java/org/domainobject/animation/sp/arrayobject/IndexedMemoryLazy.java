package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.FloatBuffer;
import java.util.Iterator;


public abstract class IndexedMemoryLazy<T extends ArrayObject> implements _IndexedMemoryLazy<T>  {

	protected final T[] objs;
	protected final float[] raw;
	protected final FloatBuffer objBuf;
	protected final int objSize;
	
	protected boolean destructive;
	protected int numObjs;
	protected int numElems;

	public IndexedMemoryLazy(T[] objects, int objSize)
	{
		this.objs = objects;
		this.objSize = objSize;
		raw = new float[objects.length * objSize];
		objBuf = createFloatBuffer(raw.length);
	}

	abstract T construct(float[] raw, int offset);

	@Override
	public void add(T arrayObject)
	{
		T copy = construct(raw, numElems);
		arrayObject.copyTo(copy);
		numElems += objSize;
		numObjs++;
	}

	@Override
	public T make()
	{
		T obj = construct(raw, numElems);
		objs[numObjs++] = obj;
		numElems += objSize;
		return obj;
	}

	@Override
	public int size()
	{
		return numObjs;
	}

	@Override
	public boolean isDestructive()
	{
		return destructive;
	}

	@Override
	public void setDestructive(boolean destructive)
	{
		this.destructive = destructive;
	}

	@Override
	public void clear()
	{
		numObjs = 0;
		numElems = 0;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>() {
			int i = 0;
			public boolean hasNext()
			{
				return i < numObjs;
			}
			public T next()
			{
				return objs[i++];
			}
		};
	}

}