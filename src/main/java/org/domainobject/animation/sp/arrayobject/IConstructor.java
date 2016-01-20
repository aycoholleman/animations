package org.domainobject.animation.sp.arrayobject;

interface IConstructor<T extends ArrayObject> {

	T make(float[] raw, int offset);
	
	T[] array(int length);

}
