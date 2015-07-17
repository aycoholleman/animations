package org.domainobject.animation;

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
import java.util.ArrayList;

public class Program {

	private int id;
	private ArrayList<Shader> shaders;


	public Program(int numShaders)
	{
		id = glCreateProgram();
		shaders = new ArrayList<>(numShaders);
	}


	public Program()
	{
		id = glCreateProgram();
		shaders = new ArrayList<>();
	}


	public void arm()
	{
		for (Shader shader : shaders) {
			shader.attach(this);
		}
		glLinkProgram(id);
		if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE) {
			throw new LinkException(this);
		}
	}


	public void activate()
	{
		glUseProgram(id);
	}


	public void attach(Shader shader)
	{
		shader.attach(this);
	}


	public int getId()
	{
		return id;
	}


	void addShader(Shader shader)
	{
		shaders.add(shader);
	}

}
