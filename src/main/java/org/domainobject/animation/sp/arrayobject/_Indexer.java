package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.util.Iterator;

interface _Indexer<T> {

	Class<?> getIndexType();
	
	boolean contains(T object);
	
	boolean index(T object);

	void add(T object);
	
	int countObjects();

	void write(ByteBuffer idxBuf);
	
	void clear();
	
	Iterator<T> iterator();

}
