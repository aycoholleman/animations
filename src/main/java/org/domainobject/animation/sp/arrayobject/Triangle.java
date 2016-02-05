package org.domainobject.animation.sp.arrayobject;

public class Triangle<T extends IPos4> extends Vertex {

	public Triangle()
	{
	}

	Triangle(float[] components, int offset)
	{
		super(components, offset);
	}

	Triangle(Vertex embedder, int offset)
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
	void copyTo(Vertex other)
	{
	}

}
