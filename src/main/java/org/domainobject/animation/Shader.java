package org.domainobject.animation;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.InputStream;

import org.domainobject.util.StringUtil;

public abstract class Shader {

	private String src;
	private ShaderType type;

	private int id;


	public Shader(String source, ShaderType type)
	{
		this.src = source;
		this.type = type;
	}


	public Shader(InputStream is, ShaderType type)
	{
		this.src = StringUtil.fromInputStream(is);
		this.type = type;
	}


	public void attach(Program program)
	{
		if (id == 0) {
			id = glCreateShader(type.getOpenGLId());
			glShaderSource(id, src);
			glCompileShader(id);
			if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
				throw new ShaderCompilationException(this);
			}
		}
		glAttachShader(program.getId(), id);
	}
	
	public int getId()
	{
		return id;
	}


	public ShaderType getType()
	{
		return type;
	}

}
