package org.domainobject.animation.util.vertex;

import org.domainobject.animation.util.Initializers;

public class VertexType {

	private final int[] map = Initializers.initialize17(Integer.MAX_VALUE);


	public VertexType(VertexAttribute... attributes)
	{
		for (int i = 0; i < attributes.length; ++i) {
			map[attributes[i].ordinal()] = i;
		}
	}


	int locate(VertexAttribute attr)
	{
		return map[attr.ordinal()];
	}

}
