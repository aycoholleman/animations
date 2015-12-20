package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createFloatBuffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.domainobject.animation.sp.Global;


public abstract class IndexedMemoryLazy<T extends ArrayObject> {

	public static enum BurnMethod
	{
		MANY_DUPLICATES, FEW_DUPLICATES, UNCHECKED, DESTRUCTIVE
	}

	// The array objects
	private final T[] objs;
	// The array objects' backbone
	private final float[] raw;
	// The GL_ELEMENT_ARRAY_BUFFER
	private final ByteBuffer idxBuf;
	// The GL_ARRAY_BUFFER
	private final FloatBuffer objBuf;
	// Number of array elements per array object
	private final int objSize;
	private final _Constructor<T> constr;
	private final _LazyIndexer indexer;

	private BurnMethod burnMethod = Global.burnMethod;
	private int numObjs;

	public IndexedMemoryLazy(int maxNumObjs, int objSize, boolean forceIntIndices)
	{
		this.objSize = objSize;
		this.constr = getConstructor();
		this.objs = constr.array(maxNumObjs);
		this.raw = new float[maxNumObjs * objSize];
		this.objBuf = createFloatBuffer(raw.length);
		if (forceIntIndices || maxNumObjs > Short.MAX_VALUE)
			indexer = new LazyIntIndexer(maxNumObjs);
		else if (maxNumObjs > Byte.MAX_VALUE)
			indexer = new LazyShortIndexer(maxNumObjs);
		else
			indexer = new LazyByteIndexer(maxNumObjs);
		this.idxBuf = indexer.createIndicesBuffer();
	}

	public void add(T arrayObject)
	{
		T copy = constr.make(raw, numObjs * objSize);
		arrayObject.copyTo(copy);
		numObjs++;
	}

	public T make()
	{
		T obj = constr.make(raw, numObjs * objSize);
		objs[numObjs++] = obj;
		return obj;
	}

	public T[] make(int howMany)
	{
		T[] sandbox = constr.array(howMany);
		for (int i = 0; i < howMany; ++i) {
			T obj = constr.make(raw, numObjs * objSize);
			objs[numObjs++] = sandbox[i] = obj;
		}
		return sandbox;
	}

	public int size()
	{
		return numObjs;
	}

	public BurnMethod getBurnMethod()
	{
		return burnMethod;
	}

	public void setBurnMethod(BurnMethod burnMethod)
	{
		this.burnMethod = burnMethod;
	}

	public void purge()
	{
		T[] objs = this.objs;
		LinkedHashSet<T> purged = new LinkedHashSet<>(objs.length);
		for (int i = 0; i < objs.length; ++i)
			purged.add(objs[i]);
		if (purged.size() != numObjs) {
			int uniqObjs = 0;
			for (T obj : purged) {
				if (obj != objs[uniqObjs]) {
					objs[uniqObjs] = obj;
					obj.copyTo(raw, uniqObjs * objSize);
				}
				uniqObjs++;
			}
			numObjs = uniqObjs;
		}
	}

	public ShaderInput burn()
	{
		switch (burnMethod) {
			case MANY_DUPLICATES:
				burnManyDuplicates();
				break;
			case FEW_DUPLICATES:
				burnFewDuplicates();
				break;
			case DESTRUCTIVE:
				burnDestructive();
				break;
			case UNCHECKED:
				burnUnchecked();
				break;
		}
		return new ShaderInput(objBuf, idxBuf);
	}

	private void burnManyDuplicates()
	{
		HashMap<T, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		int i;
		for (i = 0; i < numObjs; i++) {
			Integer idx = tbl.get(objs[i]);
			if (idx == null) {
				indexer.index(i, i);
				tbl.put(objs[i], i);
			}
			else {
				indexer.index(i, idx);
			}
		}
		float[] data = new float[tbl.size() * objSize];
		i = 0;
		for (Integer idx : tbl.values()) {
			objs[idx].copyTo(data, i++ * objSize);
		}
		objBuf.put(data);
		indexer.burnIndices(idxBuf, numObjs);
	}

	private void burnFewDuplicates()
	{
		HashMap<T, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		float[] data = new float[raw.length];
		int c = 0;
		for (int i = 0; i < numObjs; i++) {
			T obj = objs[i];
			Integer idx = tbl.get(obj);
			if (idx == null) {
				indexer.index(i, i);
				tbl.put(obj, i);
				obj.copyTo(data, c++ * objSize);
			}
			else {
				indexer.index(i, idx);
			}
		}
		objBuf.put(data);
		indexer.burnIndices(idxBuf, numObjs);
	}

	private void burnDestructive()
	{
		HashMap<T, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		int uniqObjs = 0;
		for (int i = 0; i < numObjs; ++i) {
			T obj = objs[i];
			Integer idx = tbl.get(obj);
			if (idx == null) {
				if (uniqObjs != i) {
					// We have had some duplicates so we need to compact our objs
					// array
					objs[uniqObjs] = obj;
					obj.copyTo(raw, uniqObjs * objSize);
				}
				indexer.index(i, uniqObjs);
				tbl.put(obj, uniqObjs);
				uniqObjs++;
			}
			else {
				/*
				 * A duplicate. Give it the index of the array object of which it is
				 * a duplicate
				 */
				indexer.index(i, idx);
			}
		}
		objBuf.put(raw, 0, uniqObjs * objSize);
		indexer.burnIndices(idxBuf, numObjs);
		numObjs = uniqObjs;
	}

	private void burnUnchecked()
	{
		objBuf.put(raw, 0, numObjs * objSize);
		indexer.burnDummy(idxBuf, numObjs);
	}

	public void clear()
	{
		numObjs = 0;
		idxBuf.clear();
		objBuf.clear();
	}

	public Iterator<T> iterator()
	{
		return new Iterator<T>() {
			final int len = IndexedMemoryLazy.this.numObjs;
			int i = 0;
			public boolean hasNext()
			{
				return i < len;
			}
			public T next()
			{
				return objs[i++];
			}
		};
	}

	abstract _Constructor<T> getConstructor();

}