package org.domainobject.animation.sp.arrayobject;


public abstract class LazilyIndexedMemory<T extends ArrayObject> extends AbstractMemory<T> {

	boolean shuffle;

	public LazilyIndexedMemory(T[] objects, int numComponents)
	{
		super(objects, numComponents);
	}

	public void setShuffle(boolean shuffle)
	{
		this.shuffle = shuffle;
	}

	public abstract Class<?> getIndexType();

}