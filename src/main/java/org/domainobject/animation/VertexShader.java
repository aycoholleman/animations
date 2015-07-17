package org.domainobject.animation;

import java.io.InputStream;

public class VertexShader extends Shader {

	public VertexShader(String source, ShaderType type)
	{
		super(source, type);
	}


	public VertexShader(InputStream is)
	{
		super(is, ShaderType.VERTEX);
	}

}
