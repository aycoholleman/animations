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

import java.nio.ByteBuffer;

import org.domainobject.animation.sp.Animation;
import org.domainobject.animation.sp.Program;
import org.domainobject.animation.sp.arrayobject.FastIndexedMemory;
import org.domainobject.animation.sp.arrayobject.Pos4Color4;
import org.domainobject.animation.sp.arrayobject.ShaderInput;
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
public class QuadColoredWithPos4ColorNew extends Animation {

	public static void main(String[] args)
	{
		new QuadColoredWithPos4ColorNew().start();
	}

	// Quad variables
	private int vaoId = 0;
	private int vboId = 0;
	private int vboiId = 0;
	private int indicesCount = 0;

	private Program program;
	FastIndexedMemory<Pos4Color4> vertices;

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

		vertices.add(new Pos4Color4().xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f));
		vertices.add(new Pos4Color4().xyzw(-0.5f, -0.5f, 0f, 1f).rgba(0f, 1f, 0f, 1f));
		vertices.add(new Pos4Color4().xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f));
		vertices.add(new Pos4Color4().xyzw(0.5f, -0.5f, 0f, 1f).rgba(0f, 0f, 1f, 1f));
		vertices.add(new Pos4Color4().xyzw(0.5f, 0.5f, 0f, 1f).rgba(1f, 1f, 1f, 1f));
		vertices.add(new Pos4Color4().xyzw(-0.5f, 0.5f, 0f, 1f).rgba(1f, 0f, 0f, 1f));
		
		ShaderInput shaderInput = vertices.burn();

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
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, shaderInput.getElementArrayBuffer(), GL_STATIC_DRAW);


		// Draw the vertices
		glDrawElements(GL_TRIANGLES, vertices.countIndices(), GL_UNSIGNED_BYTE, 0);

		// Put everything back to default (deselect)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
		
		vertices.clear();

	}


	@Override
	protected void dispose()
	{
		program.destroy();
		Program.deactivate();
	}


	private void setupQuad()
	{
		
		vertices = Pos4Color4.reserveFast(30, false);
		
		// OpenGL expects to draw vertices in counter clockwise order by default
//		byte[] indices = { 0, 1, 2, 2, 3, 0 };
//		indicesCount = indices.length;
//		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
//		indicesBuffer.put(indices);
//		indicesBuffer.flip();

		// Create a new Array Array Object in memory and select it (bind)
		vaoId = glCreateVertexArrays();
		glBindVertexArray(vaoId);
		vboId = glCreateBuffers();
		// Create a new VBO for the indices and select it (bind) - INDICES
		vboiId = glCreateBuffers();
		
//		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiId);
//		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
//		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}


	private void setupShaders()
	{
		program = new Program();
		program.attach(new PassThruVertexShader());
		program.attach(new PassThruFragmentShader());
		program.arm();
	}

}
