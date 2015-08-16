package org.domainobject.animation.sp.arrayobject;


interface _IndexedMemoryFast<T extends ArrayObject> extends _IndexedMemory<T> {

	void unique(T arrayObject);

	boolean contains(T arrayObject);

}