package org.domainobject.animation.simple;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL45.*;

import java.nio.ByteBuffer;

import org.domainobject.animation.Animation;
import org.domainobject.animation.Program;
import org.domainobject.animation.shaders.PassThruFragmentShader;
import org.domainobject.animation.shaders.PassThruVertexShader;
import org.domainobject.animation.util.vertex.Pos4Color4Texture;
import org.domainobject.animation.util.vertex.VertexArray;
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
	private int vboiId = 0;
	private int indicesCount = 0;
	
	private Program program;
	private VertexArray vertices;
	
	public QuadInterleaved() {
		vertices = new VertexArray(60);
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


	@Override
	protected void init()
	{
		setupShaders();
		setupQuad();
	}



	private void setupQuad()
	{
		vertices.xyzw(-0.5f, 0.5f, 0f).rgba(1, 0, 0);
		vertices.xyzw(-0.5f, -0.5f, 0f).rgba(0, 1, 0);
		vertices.xyzw(0.5f, -0.5f, 0f).rgba(0, 0, 1);
		vertices.xyzw(0.5f, 0.5f, 0f).rgba(1, 1, 1);
		
        // OpenGL expects to draw vertices in counter clockwise order by default
        byte[] indices = {
                0, 1, 2,
                2, 3, 0
        };
        indicesCount = indices.length;
        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
         
        // Create a new Vertex Array Object in memory and select it (bind)
        vaoId = glCreateVertexArrays();
        glBindVertexArray(vaoId);
         
        // Create a new Vertex Buffer Object in memory and select it (bind)
        vboId = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertices.burn(), GL_STATIC_DRAW);
        // Put the positions in attribute list 0
        glVertexAttribPointer(0, 4, GL_FLOAT, false, Pos4Color4Texture.BYTE_SIZE, Pos4Color4Texture.strides[0]);
        // Put the colors in attribute list 1
        glVertexAttribPointer(1, 4, GL_FLOAT, false, Pos4Color4Texture.BYTE_SIZE, Pos4Color4Texture.strides[1]);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
         
        // Deselect (bind to 0) the VAO
        glBindVertexArray(0);
         
        // Create a new VBO for the indices and select it (bind) - INDICES
        vboiId = glCreateBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);	}


	private void setupShaders()
	{
		program = new Program();
		program.attach(new PassThruVertexShader());
		program.attach(new PassThruFragmentShader());
		program.arm();
	}
}
