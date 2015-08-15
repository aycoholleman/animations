package org.domainobject.animation.sp.arrayobject;


public abstract class IndexedMemoryLazy<T extends ArrayObject> extends AbstractMemory<T> {

	boolean shuffle;

	public IndexedMemoryLazy(T[] objects, int numComponents)
	{
		super(objects, numComponents);
	}

	public void setShuffle(boolean shuffle)
	{
		this.shuffle = shuffle;
	}

	public abstract Class<?> getIndexType();

}