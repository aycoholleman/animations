package org.domainobject.animation.sp.arrayobject;

import java.util.Iterator;
import java.util.LinkedHashMap;

abstract class FastIndexer<ARRAY_OBJECT extends ArrayObject, INDEX_TYPE>
		implements _FastIndexer<ARRAY_OBJECT> {

	final int maxNumObjs;

	LinkedHashMap<ARRAY_OBJECT, INDEX_TYPE> objs;
	int numIndices;

	FastIndexer(int maxNumObjs)
	{
		this.maxNumObjs = maxNumObjs;
		this.objs = new LinkedHashMap<>(maxNumObjs, 1.0f);
	}

	@Override
	public int numIndices()
	{
		return numIndices;
	}

	@Override
	public Iterator<ARRAY_OBJECT> iterator()
	{
		return objs.keySet().iterator();
	}

	@Override
	public boolean contains(ARRAY_OBJECT object)
	{
		return objs.containsKey(object);
	}

}