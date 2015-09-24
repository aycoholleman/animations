package org.domainobject.animation.sp.arrayobject;

import java.io.PrintWriter;

import org.domainobject.util.debug.BeanPrinter;
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
	public final void test_add___000()
	{
		FastIndexedMemory<Pos4Color4> memory = Pos4Color4.reserveFast(6, false);
		Pos4Color4[] vertices = memory.alloc(6);
		vertices[0].xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f);//0
		vertices[1].xyzw(-0.5f, -0.5f, 0f, 1f).rgba(0f, 1f, 0f, 1f);//1
		vertices[2].xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f);//2
		vertices[3].xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f);//2
		vertices[4].xyzw(0.5f, 0.5f, 0f, 1f).rgba(1f, 1f, 1f, 1f);//3
		vertices[5].xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f);//0
		memory.commit();
		BeanPrinter bp = new BeanPrinter(new PrintWriter(System.out));
		bp.setShowSimpleClassNames(true);
		bp.dump(memory);		
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
	public final void testCommit()
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
