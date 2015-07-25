package org.domainobject.animation.util.vertex;

import static org.domainobject.animation.util.vertex.VertexAttribute.*;

public class DynamicVertex extends Vertex {

	private VertexDefinition def;


	public DynamicVertex(VertexDefinition def)
	{
		this.def = def;
	}


	public void xyz(float x, float y, float z)
	{
		components[offset + def.slot(X)] = x;
		components[offset + def.slot(Y)] = y;
		components[offset + def.slot(Z)] = z;
	}


	@Override
	protected int size()
	{
		return def.getComponentCount();
	}

}
