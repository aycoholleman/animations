package org.domainobject.animation.sp.arrayobject;

import org.lwjgl.opengl.GL11;

/**
 * Indexed memory is the type of memory you would use if you want to feed
 * indexed array buffers to your shader program using
 * {@link GL11#glDrawElements glDrawElements}. Indexed memory produces a
 * {@link ShaderInput} object that contains both an array buffer and an element
 * array buffer.
 */
public interface _IndexedMemory<T extends ArrayObject> extends _Memory<T> {

	/**
	 * Get the type of the indices in the element array buffer. Will be either
	 * {@code byte.class} or {@code short.class} or {@code int.class}.
	 * 
	 * @return The type of the indices in the element array buffer
	 */
	Class<?> getIndexType();

}