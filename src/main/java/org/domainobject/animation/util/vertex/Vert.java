package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.vertex.VertexAttribute.*;

public class Vert {

	private final VertexDefinition def;

	float[] components;
	int offset;


	public Vert(VertexDefinition def)
	{
		this.def = def;
	}


	public void xyz(float x, float y, float z)
	{
		components[offset + def.slot(X)] = x;
		components[offset + def.slot(Y)] = y;
		components[offset + def.slot(Z)] = z;
	}

}
