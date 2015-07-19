package org.domainobject.animation.util.vertex;

public abstract class Vertex {
	
	float[] components;
	int offset;

	Vertex()
	{

	}

	abstract int getComponentCount();

}
