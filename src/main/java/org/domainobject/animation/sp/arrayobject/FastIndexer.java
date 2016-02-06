package org.domainobject.animation.sp.arrayobject;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Abstract base class for <i>both</i> fast indexers <i>and</i> direct indexers.
 * 
 * @author Ayco
 *
 * @param <VERTEX>
 * @param <INDEX_TYPE>
 */
abstract class FastIndexer<VERTEX extends Vertex, INDEX_TYPE> implements IFastIndexer<VERTEX> {

	final int maxNumVertices;

	LinkedHashMap<VERTEX, INDEX_TYPE> vertices;
	int numIndices;

	FastIndexer(int maxNumVertices)
	{
		this.maxNumVertices = maxNumVertices;
		this.vertices = new LinkedHashMap<>(maxNumVertices, 1.0f);
	}

	@Override
	public int getMaxNumVertices()
	{
		return maxNumVertices;
	}

	@Override
	public int numIndices()
	{
		return numIndices;
	}

	@Override
	public int numVertices()
	{
		return vertices.size();
	}

	@Override
	public Iterator<VERTEX> iterator()
	{
		return vertices.keySet().iterator();
	}

	@Override
	public boolean contains(VERTEX object)
	{
		return vertices.containsKey(object);
	}

}