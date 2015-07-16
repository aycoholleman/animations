package org.domainobject.animation;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL40.*;

public enum ShaderType
{
	VERTEX(GL_VERTEX_SHADER),
	FRAGMENT(GL_FRAGMENT_SHADER),
	TESS_CONTROL(GL_TESS_CONTROL_SHADER),
	TESS_EVALUATION(GL_TESS_EVALUATION_SHADER),
	GEOMETRY(GL_GEOMETRY_SHADER);

	private final int openGLId;


	private ShaderType(int openGLId)
	{
		this.openGLId = openGLId;
	}


	public int getOpenGLId()
	{
		return openGLId;
	}


	@Override
	public String toString()
	{
		return "GL_" + name() + "_SHADER";
	}

}
