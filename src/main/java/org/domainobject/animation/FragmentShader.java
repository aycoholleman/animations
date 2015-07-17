package org.domainobject.animation;

import java.io.InputStream;

public class FragmentShader extends Shader {

	public FragmentShader(String source, ShaderType type)
	{
		super(source, ShaderType.FRAGMENT);
	}


	public FragmentShader(InputStream is, ShaderType type)
	{
		super(is, ShaderType.FRAGMENT);
	}

}
