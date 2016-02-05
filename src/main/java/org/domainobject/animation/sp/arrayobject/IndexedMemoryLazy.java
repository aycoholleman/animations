package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.createFloatBuffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.domainobject.animation.sp.Global;

public abstract class IndexedMemoryLazy<ARRAY_OBJECT extends Vertex> {

	public static enum BurnMethod
	{
		MANY_DUPLICATES, FEW_DUPLICATES, UNCHECKED, DESTRUCTIVE
	}

	// The array objects
	private final ARRAY_OBJECT[] objs;
	// The array objects' backbone
	private final float[] raw;
	// The GL_ELEMENT_ARRAY_BUFFER
	private final ByteBuffer idxBuf;
	// The GL_ARRAY_BUFFER
	private final FloatBuffer objBuf;
	// Number of array elements per array object
	private final int objSize;
	private final IConstructor<ARRAY_OBJECT> constr;
	private final ILazyIndexer indexer;

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

	public void add(ARRAY_OBJECT obj)
	{
		ARRAY_OBJECT copy = constr.make(raw, numObjs * objSize);
		obj.copyTo(copy);
		numObjs++;
	}

	/**
	 * Creates a new array object of the type stored by this memory instance.
	 * The array object is immediately added to this memory instance. Any
	 * changes you make to it affect this memory instance's backing array. The
	 * advantage of calling this method over using the array object's
	 * constructor is that no new backing array is created for the array object;
	 * it piggy-backs on the backing array of this memory instance.
	 * 
	 * @return
	 */
	public ARRAY_OBJECT alloc()
	{
		ARRAY_OBJECT obj = constr.make(raw, numObjs * objSize);
		objs[numObjs++] = obj;
		return obj;
	}

	/**
	 * Creates the specified amount of array objects.
	 * 
	 * @param howMany
	 * @return
	 */
	public ARRAY_OBJECT[] alloc(int howMany)
	{
		ARRAY_OBJECT[] sandbox = constr.array(howMany);
		for (int i = 0; i < howMany; ++i) {
			ARRAY_OBJECT obj = constr.make(raw, numObjs * objSize);
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
		ARRAY_OBJECT[] objs = this.objs;
		LinkedHashSet<ARRAY_OBJECT> purged = new LinkedHashSet<>(objs.length);
		for (int i = 0; i < objs.length; ++i)
			purged.add(objs[i]);
		if (purged.size() != numObjs) {
			int uniqObjs = 0;
			for (ARRAY_OBJECT obj : purged) {
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
		HashMap<ARRAY_OBJECT, Integer> tbl = new HashMap<>(numObjs, 1.0f);
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
		HashMap<ARRAY_OBJECT, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		float[] data = new float[raw.length];
		int c = 0;
		for (int i = 0; i < numObjs; i++) {
			ARRAY_OBJECT obj = objs[i];
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
		HashMap<ARRAY_OBJECT, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		int uniqObjs = 0;
		for (int i = 0; i < numObjs; ++i) {
			ARRAY_OBJECT obj = objs[i];
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
				 * A duplicate. Give it the index of the array object of which
				 * it is a duplicate
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

	public Iterator<ARRAY_OBJECT> iterator()
	{
		return new Iterator<ARRAY_OBJECT>() {

			final int len = IndexedMemoryLazy.this.numObjs;
			int i = 0;

			public boolean hasNext()
			{
				return i < len;
			}

			public ARRAY_OBJECT next()
			{
				return objs[i++];
			}
		};
	}

	abstract IConstructor<ARRAY_OBJECT> getConstructor();

}