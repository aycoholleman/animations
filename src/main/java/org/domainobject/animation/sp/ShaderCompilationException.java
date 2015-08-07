package org.domainobject.animation.sp;

import static org.lwjgl.opengl.GL20.*;

@SuppressWarnings("serial")
public class ShaderCompilationException extends AnimationException {

	private static final String MESSAGE_PATTERN = "\n\nError compiling shader (type=%s; id=%s):\n%s";


	private static String getMessage(Shader shader)
	{
		String error = glGetShaderInfoLog(shader.getId());
		return String.format(MESSAGE_PATTERN, shader.getType(), shader.getId(), error);
	}


	public ShaderCompilationException(Shader shader)
	{
		super(getMessage(shader));
	}
}
