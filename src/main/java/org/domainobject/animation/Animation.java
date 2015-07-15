package org.domainobject.animation;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class Animation extends AbstractAnimation {

	public static void main(String[] args)
	{
		new Animation().start();
	}


	public Animation()
	{
		super();
	}


	@Override
	protected GLFWErrorCallback onError()
	{
		return Callbacks.errorCallbackPrint(System.err);
	}


	protected GLFWKeyCallback onKeyPress()
	{
		return new GLFWKeyCallback() {

			@Override
			public void invoke(long window, int key, int scancode, int action, int mods)
			{
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
					glfwSetWindowShouldClose(window, GL_TRUE);
				}
			}
		};
	}


	@Override
	protected GLFWCursorPosCallback onMouseMove()
	{
		return null;
	}


	@Override
	protected GLFWMouseButtonCallback onClick()
	{
		return null;
	}


	@Override
	protected GLFWScrollCallback onScroll()
	{
		return null;
	}


	@Override
	protected String getWindowTitle()
	{
		return "";
	}


	@Override
	protected void init()
	{
	}


	@Override
	protected void update(double time)
	{
	}


	@Override
	protected void render(double time)
	{
	}


	@Override
	protected void dispose()
	{
	}

}
