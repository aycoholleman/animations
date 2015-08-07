package org.domainobject.animation.sp;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.ArrayList;

public class Program {

	public static void deactivate()
	{
		glUseProgram(0);
	}

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


	public void destroy()
	{
		for (Shader shader : shaders) {
			glDetachShader(id, shader.getId());
			glDeleteShader(shader.getId());
		}
		glDeleteProgram(id);
		shaders = null;
	}


	void addShader(Shader shader)
	{
		shaders.add(shader);
	}

}
