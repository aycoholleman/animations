package org.domainobject.animation.sp.arrayobject;

import java.util.Iterator;


public interface _LazyIndexedMemory<T extends ArrayObject> extends Iterable<T> {

	T newInstance();

	Class<?> getIndexType();

	int size();

	void add(T arrayObject);

	boolean pack();

	void pack(boolean pack);

	ShaderInput burn();

	void clear();

	Iterator<T> iterator();

}