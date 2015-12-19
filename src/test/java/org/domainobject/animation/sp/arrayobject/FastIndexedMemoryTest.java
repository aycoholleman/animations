package org.domainobject.animation.sp.arrayobject;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.domainobject.util.reflect.Reflection;
import org.junit.Test;

public class FastIndexedMemoryTest {

	@Test
	public final void testFastIndexedMemory()
	{

	}

	@Test
	public final void testGetIndexType()
	{

	}

	@Test
	public final void testSize()
	{

	}

	@Test
	@SuppressWarnings("rawtypes")
	public final void test00_commit()
	{
		boolean forceIntIndexer = false;
		FastIndexedMemory<Pos4Color4> memory = Pos4Color4.reserveFast(6, forceIntIndexer);
		Pos4Color4[] vertices = memory.alloc(6);
		// Add six vertices; two of them duplicates
		vertices[0].xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f);//0		
		vertices[1].xyzw(-0.5f, -0.5f, 0f, 1f).rgba(0f, 1f, 0f, 1f);//1
		vertices[2].xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f);//2
		vertices[3].xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f);//2
		vertices[4].xyzw(0.5f, 0.5f, 0f, 1f).rgba(1f, 1f, 1f, 1f);//3
		vertices[5].xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f);//0
		memory.commit();
		//@formatter:off
		// This is what we expect to be in memory
		float[] objects = new float[] {
				-0.5f, 0.5f, 0f, 1f, 1f, 0f, 0f, 1f,
				-0.5f, -0.5f, 0f, 1f, 0f, 1f, 0f, 1f,
				0.5f, -0.5f, 0f, 1f, 0f, 0f, 1f, 1f,
				0.5f, 0.5f, 0f, 1f, 1f, 1f, 1f, 1f
		};
		byte[] indices = {
		        0, 1, 2,
		        2, 3, 0
		};
		//@formatter:on
		Reflection r = new Reflection(memory);
		assertEquals(8, (int) r.getInt("objSize"));
		_FastIndexer indexer = (_FastIndexer) r.get("indexer");
		assertTrue(indexer.getClass() == FastByteIndexer.class);
		FastIndexer<T> fbi = (FastIndexer<T>) indexer;
		Reflection r2 = new Reflection(fbi);
		assertEquals(4, r2.getInt("numObjs"));
		assertEquals(6, r2.getInt("numIndices"));
		float[] raw = (float[]) r.get("raw");
		int i;
		for (i = 0; i < objects.length; i++)
			assertEquals(objects[i], raw[i], 0);
		while (i < raw.length)
			assertEquals(raw[i++], 0, 0);
		byte[] actualIndices = (byte[]) r2.get("indices");
		assertArrayEquals(indices, actualIndices);
		ShaderInput si = memory.burn();
		assertEquals(32, si.arrayBuffer.limit());
		assertEquals(6, si.elementArrayBuffer.limit());
	}

	@Test
	public final void testAddUnchecked()
	{

	}

	@Test
	public final void testMake()
	{

	}

	@Test
	public final void testMake__int()
	{

	}

	@Test
	public final void testContains()
	{

	}

	@Test
	public final void testBurn()
	{

	}

	@Test
	public final void testClear()
	{

	}

	@Test
	public final void testIterator()
	{

	}

	@Test
	public final void testGetConstructor()
	{

	}

}
