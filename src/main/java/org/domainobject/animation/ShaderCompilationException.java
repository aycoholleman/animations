package org.domainobject.animation;

import static org.lwjgl.opengl.GL20.*;

@SuppressWarnings("serial")
public class ShaderCompilationException extends AnimationException {

	private static final String MESSAGE_PATTERN = "Error compiling shader (type=%s; id=%s): %s";


	private static String getMessage(Shader shader)
	{
		String error = glGetShaderInfoLog(shader.getId(), glGetShaderi(shader.getId(), GL_INFO_LOG_LENGTH));
		return String.format(MESSAGE_PATTERN, shader.getType(), shader.getId(), error);
	}


	public ShaderCompilationException(Shader shader)
	{
		super(getMessage(shader));
	}
}
