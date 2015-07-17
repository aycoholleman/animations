package org.domainobject.animation.simple;

import org.domainobject.animation.Animation;
import org.domainobject.animation.shaders.PassThruVertexShader;

/**
 * @see http://wiki.lwjgl.org/wiki/The_Quad_colored
 * 
 * @author Ayco Holleman
 * @created Jul 17, 2015
 *
 */
public class QuadColored extends Animation {

	public static void main(String[] args)
	{
		new QuadColored().start();
	}


	public QuadColored()
	{
		super();
	}


	@Override
	protected void init()
	{
		new PassThruVertexShader().test();
	}
	
	

}
