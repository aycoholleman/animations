package org.domainobject.animation.sp.simple;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL45.*;

import java.nio.ByteBuffer;

import org.domainobject.animation.sp.Animation;
import org.domainobject.animation.sp.Program;
import org.domainobject.animation.sp.arrayobject.Memory;
import org.domainobject.animation.sp.arrayobject.Pos4;
import org.domainobject.animation.sp.arrayobject.old.RawMemory;
import org.domainobject.animation.sp.shaders.PassThruFragmentShader;
import org.domainobject.animation.sp.shaders.PassThruVertexShader;
import org.lwjgl.BufferUtils;

/**
 * @see http://wiki.lwjgl.org/wiki/The_Quad_colored
 * 
 * @author Ayco Holleman
 * @created Jul 17, 2015
 *
 */
public class QuadColoredWithPos4_Color4 extends Animation {

	public static void main(String[] args)
	{
		new QuadColoredWithPos4_Color4().start();
	}

	// Quad variables
	private int vaoId = 0;
	private int vboId = 0;
	private int vbocId = 0;
	private int vboiId = 0;
	private int indicesCount = 0;

	private Program program;


	public QuadColoredWithPos4_Color4()
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
		
		Memory<Pos4> vertices = Pos4.allocate(30);
		vertices.newInstance().xyzw(-0.5f, 0.5f, 0f, 1f);
		vertices.newInstance().xyzw(-0.5f, -0.5f, 0f, 1f);
		vertices.newInstance().xyzw(0.5f, -0.5f, 0f, 1f);
		vertices.newInstance().xyzw(0.5f, 0.5f, 0f, 1f);

		
		RawMemory colors = new RawMemory(30);
		colors.add(1f, 0f, 0f, 1f);
		colors.add(0f, 1f, 0f, 1f);
		colors.add(0f, 0f, 1f, 1f);
		colors.add(1f, 1f, 1f, 1f);

		// OpenGL expects to draw vertices in counter clockwise order by default
		byte[] indices = { 0, 1, 2, 2, 3, 0 };
		indicesCount = indices.length;
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
		indicesBuffer.put(indices);
		indicesBuffer.flip();

		// Create a new Array Array Object in memory and select it (bind)
		vaoId = glCreateVertexArrays();
		glBindVertexArray(vaoId);

		// Create a new Array Buffer Object in memory and select it (bind) - VERTICES
		vboId = glCreateBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, vertices.burn(), GL_STATIC_DRAW);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		// Create a new VBO for the indices and select it (bind) - COLORS
		vbocId = glCreateBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbocId);
		glBufferData(GL_ARRAY_BUFFER, colors.burn(), GL_STATIC_DRAW);
		glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		// Deselect (bind to 0) the VAO
		glBindVertexArray(0);

		// Create a new VBO for the indices and select it (bind) - INDICES
		vboiId = glCreateBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
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
