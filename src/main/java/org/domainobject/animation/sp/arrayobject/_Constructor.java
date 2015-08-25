package org.domainobject.animation.sp.arrayobject;

interface _Constructor<T extends ArrayObject> {

	T make(float[] raw, int offset);
	
	T[] array(int length);

}
