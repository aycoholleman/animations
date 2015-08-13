package org.domainobject.animation.sp.simple.torus;

import java.util.HashMap;

/**
 * @author Ayco Holleman
 *
 */
public class TriangleBatch {

	private HashMap<Triangle,Integer> triangles;
	
	private int numIndices;
	private Integer[] indices;

	public TriangleBatch(int maxSize)
	{
		triangles = new HashMap<>(maxSize, 1.0f);
		indices = new Integer[maxSize];
	}

	public void addTriangle(Triangle t)
	{
		Integer i = triangles.get(t);
		if(i == null) {
			triangles.put(t, numIndices);
			indices[numIndices] = numIndices;
		}
		else {
			indices[numIndices] = i;
		}
		++numIndices;
	}

}
