package org.domainobject.animation.sp.arrayobject;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * @author Ayco Holleman
 *
 */
public final class ShaderInput {

	FloatBuffer arrayBuffer;
	Buffer elementArrayBuffer;

	ShaderInput(FloatBuffer arrayBuffer)
	{
		this.arrayBuffer = arrayBuffer;
	}

	ShaderInput(FloatBuffer arrayBuffer, Buffer elementArrayBuffer)
	{
		this.arrayBuffer = arrayBuffer;
		this.elementArrayBuffer=elementArrayBuffer;
	}

	public FloatBuffer getArrayBuffer()
	{
		return arrayBuffer;
	}

	public Buffer getElementArrayBuffer()
	{
		return elementArrayBuffer;
	}

}
