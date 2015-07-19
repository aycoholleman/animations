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

	// Quad variables
	private int vaoId = 0;
	private int vboId = 0;
	private int vbocId = 0;
	private int vboiId = 0;
	private int indicesCount = 0;
	
	private Program program;

	@Override
	protected void update(double time)
	{
		glClear(GL_COLOR_BUFFER_BIT);

		program.activate();

		// Bind to the VAO that has all the information about the vertices
		glBindVertexArray(vaoId);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		// Bind to the index VBO that has all the information about the order of the vertices
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiId);

		// Draw the vertices
		glDrawElements(GL_TRIANGLES, indicesCount, GL_UNSIGNED_BYTE, 0);

		// Put everything back to default (deselect)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
		
		Program.deactivate();

	}
	
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
