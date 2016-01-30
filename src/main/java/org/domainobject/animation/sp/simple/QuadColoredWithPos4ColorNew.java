package org.domainobject.animation.sp.simple;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STREAM_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL45.glCreateBuffers;
import static org.lwjgl.opengl.GL45.glCreateVertexArrays;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.opengl.GL41.*;
import static org.lwjgl.opengl.GL42.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.opengl.GL44.*;
import static org.lwjgl.opengl.GL45.*;


import java.nio.ShortBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;

import org.domainobject.animation.sp.Animation;
import org.domainobject.animation.sp.Program;
import org.domainobject.animation.sp.arrayobject.IndexedMemoryFast;
import org.domainobject.animation.sp.arrayobject.Pos4Color4;
import org.domainobject.animation.sp.arrayobject.ShaderInput;
import org.domainobject.animation.sp.shaders.PassThruFragmentShader;
import org.domainobject.animation.sp.shaders.PassThruVertexShader;

/**
 * @see http://wiki.lwjgl.org/wiki/The_Quad_colored
 * 
 * @author Ayco Holleman
 * @created Jul 17, 2015
 *
 */
public class QuadColoredWithPos4ColorNew extends Animation {

	public static void main(String[] args)
	{
		new QuadColoredWithPos4ColorNew().start();
	}

	// Quad variables
	private int vaoId = 0;
	private int vboId = 0;
	private int vboiId = 0;

	private Program program;
	IndexedMemoryFast<Pos4Color4> memory;

	public QuadColoredWithPos4ColorNew()
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

		memory.add(new Pos4Color4().xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f));
		memory.add(new Pos4Color4().xyzw(-0.5f, -0.5f, 0f, 1f).rgba(0f, 1f, 0f, 1f));
		memory.add(new Pos4Color4().xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f));
		memory.add(new Pos4Color4().xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f));
		memory.add(new Pos4Color4().xyzw(0.5f, 0.5f, 0f, 1f).rgba(1f, 1f, 1f, 1f));
		memory.add(new Pos4Color4().xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f));
		
		ShaderInput shaderInput = memory.burn();

		// Bind to the VAO that has all the information about the vertices
		glBindVertexArray(vaoId);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, shaderInput.getArrayBuffer(), GL_STREAM_DRAW);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 8 * 4, 0);
		glVertexAttribPointer(1, 4, GL_FLOAT, false, 8 * 4, 4 * 4);
		glBindBuffer(GL_ARRAY_BUFFER, 0);


		// Bind to the index VBO that has all the information about the order of the vertices
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, (IntBuffer) shaderInput.getElementArrayBuffer(), GL_STATIC_DRAW);


		// Draw the vertices
		//glDrawElements(GL_TRIANGLES, vertices.countIndices(), GL_UNSIGNED_BYTE, 0);
		//glDrawElements(GL_TRIANGLES, vertices.countIndices(), GL_UNSIGNED_SHORT, 0);
		glDrawElements(GL_TRIANGLES, memory.numIndices(), GL_UNSIGNED_INT, 0);
	
		// Put everything back to default (deselect)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
		
		memory.clear();

	}


	@Override
	protected void dispose()
	{
		program.destroy();
		Program.deactivate();
	}


	private void setupQuad()
	{		
		//memory = Pos4Color4.reserveFast(30, true);		
		memory = Pos4Color4.getDirectMemory(30, true);	
		// Create a new Array Array Object in memory and select it (bind)
		vaoId = glCreateVertexArrays();
		glBindVertexArray(vaoId);
		vboId = glCreateBuffers();
		// Create a new VBO for the indices and select it (bind) - INDICES
		vboiId = glCreateBuffers();
	}


	private void setupShaders()
	{
		program = new Program();
		program.attach(new PassThruVertexShader());
		program.attach(new PassThruFragmentShader());
		program.arm();
	}

}
