package org.domainobject.animation.sp.arrayobject;

/**
 * @author Ayco Holleman
 *
 */
public abstract class IndexedMemoryFast<T extends ArrayObject> extends AbstractMemory<T> {

	public IndexedMemoryFast(T[] objects, int objSize)
	{
		super(objects, objSize);
	}

	@Override
	public ShaderInput burn()
	{
		return null;
	}

}
