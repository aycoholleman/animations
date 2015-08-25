package org.domainobject.animation.sp.arrayobject;

public class Triangle<T extends _Pos4> extends ArrayObject {

	public Triangle()
	{
	}

	Triangle(float[] components, int offset)
	{
		super(components, offset);
	}

	Triangle(ArrayObject embedder, int offset)
	{
		super(embedder, offset);
	}

	@Override
	public void copyTo(float[] target, int offset)
	{
	}

	@Override
	int objSize()
	{
		return 0;
	}

	@Override
	void copyTo(ArrayObject other)
	{
	}

}
