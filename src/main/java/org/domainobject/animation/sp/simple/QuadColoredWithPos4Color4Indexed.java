package org.domainobject.animation.sp.simple;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL45.glCreateBuffers;
import static org.lwjgl.opengl.GL45.glCreateVertexArrays;

import java.io.PrintWriter;

import org.domainobject.animation.sp.Animation;
import org.domainobject.animation.sp.Program;
import org.domainobject.animation.sp.arrayobject.FastIndexedMemory;
import org.domainobject.animation.sp.arrayobject.Pos4Color4;
import org.domainobject.animation.sp.arrayobject.ShaderInput;
import org.domainobject.animation.sp.shaders.PassThruFragmentShader;
import org.domainobject.animation.sp.shaders.PassThruVertexShader;
import org.domainobject.util.debug.BeanPrinter;

/**
 * @see http://wiki.lwjgl.org/wiki/The_Quad_colored
 * 
 * @author Ayco Holleman
 * @created Jul 17, 2015
 *
 */
public class QuadColoredWithPos4Color4Indexed extends Animation {

	public static void main(String[] args)
	{
		new QuadColoredWithPos4Color4Indexed().start();
	}

	// Quad variables
	private int vaoId = 0;
	private int vboId = 0;
	private int vboiId = 0;
	private int indicesCount = 0;

	private Program program;


	public QuadColoredWithPos4Color4Indexed()
	{
		super();
	}


	@Override
	protected void init()
	{
		setupShaders();
		setupQuad();
	}


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


	private void setupQuad()
	{
		
		FastIndexedMemory<Pos4Color4> memory = Pos4Color4.indexFast(8, false);
		Pos4Color4[] vertices = memory.make(6);
		vertices[0].position(-0.5f, 0.5f, 0f, 1f).color(1f, 0f, 0f, 1f);//0
		vertices[1].position(-0.5f, -0.5f, 0f, 1f).color(0f, 1f, 0f, 1f);//1
		vertices[2].position(0.5f, -0.5f, 0f, 1f).color(0f, 0f, 1f, 1f);//2
		vertices[3].position(0.5f, -0.5f, 0f, 1f).color(0f, 0f, 1f, 1f);//2
		vertices[4].position(0.5f, 0.5f, 0f, 1f).color(1f, 1f, 1f, 1f);//3
		vertices[5].position(-0.5f, 0.5f, 0f, 1f).color(1f, 0f, 0f, 1f);//0
		memory.commit();
		
		BeanPrinter bp = new BeanPrinter(new PrintWriter(System.out));
		bp.dump(memory);
		

				
		ShaderInput si = memory.burn();

		vaoId = glCreateVertexArrays();
		glBindVertexArray(vaoId);

		// Create a new Array Buffer Object in memory and select it (bind)
		vboId = glCreateBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, si.getArrayBuffer(), GL_STATIC_DRAW);
		// Put the positions in attribute list 0
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 8 * 4, 0);
		// Put the colors in attribute list 1
		glVertexAttribPointer(1, 4, GL_FLOAT, false, 8 * 4, 4 * 4);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		// Deselect (bind to 0) the VAO
		glBindVertexArray(0);

		// Create a new VBO for the indices and select it (bind) - INDICES
		vboiId = glCreateBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, si.getElementArrayBuffer(), GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

		
	}


	private void setupShaders()
	{
		program = new Program();
		program.attach(new PassThruVertexShader());
		program.attach(new PassThruFragmentShader());
		program.arm();
	}

}
