package org.domainobject.animation.sp.arrayobject;

/**
 * @author Ayco Holleman
 *
 */
public abstract class IndexedMemory<T extends ArrayObject> extends AbstractMemory<T> {

	public IndexedMemory(T[] objects, int objSize)
	{
		super(objects, objSize);
	}

	@Override
	public ShaderInput burn()
	{
		return null;
	}

}
