package org.domainobject.animation.util.vertex;

import java.util.Arrays;
import java.util.HashSet;

import org.domainobject.animation.util.Array;

public class VertexDefinition {

	private int[] slots = Array.fill17(Integer.MAX_VALUE);
	private int numComponents;


	public VertexDefinition(VertexAttribute... attributes)
	{
		if (attributes.length != new HashSet<>(Arrays.asList(attributes)).size()) {
			//TODO: Error (non-unique attributes)
		}
		numComponents = attributes.length;
		for (int i = 0; i < attributes.length; ++i) {
			slots[attributes[i].ordinal()] = i;
		}
	}


	int getNumComponents()
	{
		return numComponents;
	}


	int slot(VertexAttribute attr)
	{
		return slots[attr.ordinal()];
	}

}
