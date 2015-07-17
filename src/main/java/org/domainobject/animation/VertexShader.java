package org.domainobject.animation;

import java.io.InputStream;

public class VertexShader extends Shader {

	public VertexShader(String source)
	{
		super(source, ShaderType.VERTEX);
	}


	public VertexShader(InputStream is)
	{
		super(is, ShaderType.VERTEX);
	}

}
