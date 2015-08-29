package org.domainobject.animation.sp.arrayobject;

import static org.lwjgl.BufferUtils.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;


public abstract class LazyIndexedMemory<T extends ArrayObject> {

	// The array objects
	private final T[] objs;
	// The array objects' backbone
	private final float[] raw;
	// The GL_ELEMENT_ARRAY_BUFFER
	private final ByteBuffer idxBuf;
	// The GL_ARRAY_BUFFER
	private final FloatBuffer objBuf;
	// The number of array elements per array object
	private final int objSize;
	private final _Constructor<T> constructor;
	private final _LazyIndexer indexer;

	private boolean destructive;
	private int numObjs;
	private int numElems;

	public LazyIndexedMemory(int maxNumObjs, int objSize, boolean forceIntIndices)
	{
		this.objSize = objSize;
		this.constructor = getConstructor();
		this.objs = constructor.array(maxNumObjs);
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
		T copy = constructor.make(raw, numElems);
		arrayObject.copyTo(copy);
		numElems += objSize;
		numObjs++;
	}

	public T make()
	{
		T obj = constructor.make(raw, numElems);
		objs[numObjs++] = obj;
		numElems += objSize;
		return obj;
	}

	public T[] make(int howMany)
	{
		T[] objs = constructor.array(howMany);
		for (int i = 0; i < howMany; ++i) {
			objs[i] = make();
		}
		return objs;
	}

	public int size()
	{
		return numObjs;
	}

	public boolean isDestructive()
	{
		return destructive;
	}

	public void setDestructive(boolean destructive)
	{
		this.destructive = destructive;
	}

	public void purge()
	{
		LinkedHashSet<T> purged = new LinkedHashSet<>(Arrays.asList(objs));
		if (purged.size() != numObjs) {
			numObjs = 0;
			for (T obj : purged) {
				if (obj != objs[numObjs]) {
					objs[numObjs] = obj;
					obj.copyTo(raw, numObjs * objSize);
				}
				numObjs++;
			}
		}
	}

	public ShaderInput burn()
	{
		if (destructive)
			burnDestructive();
		else
			burnNonDestructive();
		return new ShaderInput(objBuf, idxBuf);
	}

	private void burnDestructive()
	{
		HashMap<T, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		int count = 0;
		for (int i = 0; i < numObjs; ++i) {
			T obj = objs[i];
			Integer index = tbl.get(obj);
			if (index == null) {
				indexer.assignIndex(i, i);
				tbl.put(obj, i);
				if (count != i) {
					objs[count] = obj;
					obj.copyTo(raw, count * objSize);
				}
				count++;
			}
			else {
				indexer.assignIndex(i, index);
			}
		}
		objBuf.put(raw, 0, count * objSize);
		indexer.burnIndices(idxBuf, numObjs);
		numObjs = count;
	}

	private void burnNonDestructive()
	{
		HashMap<T, Integer> tbl = new HashMap<>(numObjs, 1.0f);
		for (int i = 0; i < numObjs; ++i) {
			Integer index = tbl.get(objs[i]);
			if (index == null) {
				indexer.assignIndex(i, i);
				tbl.put(objs[i], i);
			}
			else {
				indexer.assignIndex(i, index);
			}
		}
		float[] data = new float[tbl.size() * objSize];
		int count = 0;
		for (Entry<T, Integer> entry : tbl.entrySet()) {
			objs[entry.getValue()].copyTo(data, count++ * objSize);
		}
		objBuf.put(data);
		indexer.burnIndices(idxBuf, numObjs);
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

	abstract _Constructor<T> getConstructor();

}