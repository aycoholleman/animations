package org.domainobject.animation;

import static org.lwjgl.opengl.GL20.*;

import java.util.ArrayList;

public class Program {

	private int id;
	private ArrayList<Shader> shaders;


	public Program(int numShaders)
	{
		id = glCreateProgram();
		shaders = new ArrayList<>(numShaders);
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
