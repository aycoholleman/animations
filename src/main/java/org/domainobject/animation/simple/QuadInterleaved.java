package org.domainobject.animation.simple;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL45.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.domainobject.animation.Animation;
import org.domainobject.animation.Program;
import org.domainobject.animation.shaders.PassThruFragmentShader;
import org.domainobject.animation.shaders.PassThruVertexShader;
import org.lwjgl.BufferUtils;

/**
 * @author Ayco Holleman
 * @created Jul 18, 2015
 *
 */
public class QuadInterleaved extends Animation {

	public static void main(String[] args)
	{
		new QuadInterleaved().start();
	}

	private Program program;

	@Override
	protected void dispose()
	{
		program.destroy();
		Program.deactivate();
	}


	@Override
	protected void init()
	{
		setupShaders();
		setupQuad();
	}



	private void setupQuad()
	{
		// TODO Auto-generated method stub
		
	}


	private void setupShaders()
	{
		program = new Program();
		program.attach(new PassThruVertexShader());
		program.attach(new PassThruFragmentShader());
		program.arm();
	}
}
