package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.Iterator;

interface _FastIndexer<T> {

	Class<?> getIndexType();
	
	ByteBuffer createIndicesBuffer();
	
	boolean contains(T object);
	
	boolean index(T object);

	void add(T object);
	
	int countObjects();

	void burnIndices(ByteBuffer idxBuf);
	
	void clear();
	
	Iterator<T> iterator();

}
