package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.vertex.VertexAttribute.*;

public final class DynamicVertex extends Vertex {

	private VertexDefinition def;


	public DynamicVertex(float[] components, int offset, VertexDefinition def)
	{
		super(components, offset);
		this.def = def;
	}


	public void xyz(float x, float y, float z)
	{
		components[offset + def.slot(X)] = x;
		components[offset + def.slot(Y)] = y;
		components[offset + def.slot(Z)] = z;
	}


	@Override
	int size()
	{
		return def.getNumComponents();
	}

}
