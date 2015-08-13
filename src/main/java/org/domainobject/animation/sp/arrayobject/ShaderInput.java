package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * @author Ayco Holleman
 *
 */
public class ShaderInput {

	FloatBuffer arrayBuffer;
	ByteBuffer elementArrayBuffer;

	ShaderInput(FloatBuffer arrayBuffer)
	{
		this.arrayBuffer = arrayBuffer;
	}

	public FloatBuffer getArrayBuffer()
	{
		return arrayBuffer;
	}

}
