package org.domainobject.animation.sp.shaders;

import org.domainobject.animation.sp.VertexShader;

public class PassThruVertexShader extends VertexShader {

	private static final String SOURCE_FILE = "pass-thru.00.vertex.glsl";
	
	public PassThruVertexShader()
	{
		super(PassThruVertexShader.class.getResourceAsStream(SOURCE_FILE));
	}

}
