package org.domainobject.animation.sp.arrayobject;

import java.util.Iterator;
import java.util.LinkedHashMap;

abstract class DirectIndexer<ARRAY_OBJECT extends Vertex, INDEX_TYPE>
		implements IFastIndexer<ARRAY_OBJECT> {

	final int maxNumIndices;

	LinkedHashMap<ARRAY_OBJECT, INDEX_TYPE> objs;

	DirectIndexer(int maxNumObjs)
	{
		this.maxNumIndices = maxNumObjs;
		this.objs = new LinkedHashMap<>(maxNumObjs, 1.0f);
	}

	public int getMaxNumIndices()
	{
		return maxNumIndices;
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