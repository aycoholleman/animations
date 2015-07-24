package org.domainobject.animation.util.vertex;

import java.util.Arrays;
import java.util.HashSet;

import org.domainobject.animation.util.Initializers;

import static org.domainobject.animation.util.vertex.VertexAttribute.*;

public class VertexDefinition {

	public static final VertexDefinition XYZ = new VertexDefinition(X, Y, Z);
	public static final VertexDefinition XYZW = new VertexDefinition(X, Y, Z, W);

	private int[] slots = Initializers.initialize17(Integer.MAX_VALUE);
	private int componentCount;


	public VertexDefinition(VertexAttribute... attributes)
	{
		if (attributes.length != new HashSet<>(Arrays.asList(attributes)).size()) {
			//TODO: Error (non-unique attributes)
		}
		componentCount = attributes.length;
		for (int i = 0; i < attributes.length; ++i) {
			slots[attributes[i].ordinal()] = i;
		}
	}


	int getComponentCount()
	{
		return componentCount;
	}


	int slot(VertexAttribute attr)
	{
		return slots[attr.ordinal()];
	}

}
