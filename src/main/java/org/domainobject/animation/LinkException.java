package org.domainobject.animation;

import static org.lwjgl.opengl.GL20.*;

@SuppressWarnings("serial")
public class LinkException extends AnimationException {

	private static final String MESSAGE_PATTERN = "Error linking program (id=%s): %s";


	private static String getMessage(Program program)
	{
		String error = glGetProgramInfoLog(program.getId(), glGetProgrami(program.getId(), GL_INFO_LOG_LENGTH));
		return String.format(MESSAGE_PATTERN, program.getId(), error);
	}


	public LinkException(Program program)
	{
		super(getMessage(program));
	}
}
