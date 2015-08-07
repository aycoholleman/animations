package org.domainobject.animation.sp.shaders;

import org.domainobject.animation.sp.FragmentShader;

public class PassThruFragmentShader extends FragmentShader {

	private static final String SOURCE_FILE = "pass-thru.01.fragment.glsl";
	
	public PassThruFragmentShader()
	{
		super(PassThruFragmentShader.class.getResourceAsStream(SOURCE_FILE));
	}

}
