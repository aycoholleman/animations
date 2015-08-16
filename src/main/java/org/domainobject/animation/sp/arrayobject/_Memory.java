package org.domainobject.animation.sp.arrayobject;


interface _Memory<T extends ArrayObject> extends Iterable<T> {

	int size();

	void add(T arrayObject);

	ShaderInput burn();

	void clear();

}