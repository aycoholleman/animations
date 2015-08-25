package org.domainobject.animation.sp.arrayobject;

interface AOFactory<T extends ArrayObject> {

	T construct(float[] raw, int offset);
	
	T[] array(int length);

}
