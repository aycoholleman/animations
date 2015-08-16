package org.domainobject.animation.sp.arrayobject;


public interface _IndexedMemoryLazy<T extends ArrayObject> extends _IndexedMemory<T> {

	T newInstance();

	boolean pack();

	void pack(boolean pack);

}