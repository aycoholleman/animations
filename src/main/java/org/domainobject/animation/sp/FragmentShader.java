package org.domainobject.animation.sp;

import java.io.InputStream;

public class FragmentShader extends Shader {

	public FragmentShader(String source)
	{
		super(source, ShaderType.FRAGMENT);
	}


	public FragmentShader(InputStream is)
	{
		super(is, ShaderType.FRAGMENT);
	}

}
