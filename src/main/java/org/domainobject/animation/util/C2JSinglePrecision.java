package org.domainobject.animation.util;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

/**
 * Utility class with C-ish methods. The memcpy family of methods avoids using
 * {@code System.arrayCopy()}, which does not provide optimal performance for
 * small arrays.
 * 
 * @author Ayco Holleman
 *
 */
public class C2JSinglePrecision {

	/**
	 * Size in bytes of a float (4 bytes).
	 */
	public static final int SIZE_OF_FLOAT = 4;


	private C2JSinglePrecision()
	{
	}


	/**
	 * Get size in bytes of the specified number of float elements.
	 * 
	 * @param numElements
	 * @return
	 */
	public static int sizeof(int numElements)
	{
		return numElements << 2;
	}


	/**
	 * Get size in bytes of the specified array
	 * 
	 * @param array
	 *            The array whose size in bytes to measure
	 * @return
	 */
	public static int sizeof(float[] array)
	{
		return array.length << 2;
	}


	/**
	 * Get size in bytes of the specified two-dimensional array. Assumes all rows
	 * have the same length.
	 * 
	 * @param array
	 *            The array whose size in bytes to measure
	 * @return
	 */
	public static int sizeof(float[][] array)
	{
		if (array.length == 0) {
			return 0;
		}
		return (array.length * array[0].length) << 2;
	}


	/**
	 * Copy first two array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            The destination (write-to) array
	 * @param src
	 *            The source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy2(float[] dst, float[] src)
	{
		dst[0] = src[0];
		dst[1] = src[1];
	}


	/**
	 * Copy three array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy3(float[] dst, int dstPos, float[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
	}


	/**
	 * Copy first three array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param src
	 *            Source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy3(float[] dst, float[] src)
	{
		memcpy3(dst, 0, src, 0);
	}


	/**
	 * Copy four array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy4(float[] dst, int dstPos, float[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
		dst[dstPos + 3] = src[srcPos + 3];
	}


	/**
	 * Copy first four array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param src
	 *            Source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy4(float[] dst, float[] src)
	{
		memcpy4(dst, 0, src, 0);
	}


	/**
	 * Copy six array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy5(float[] dst, int dstPos, float[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
		dst[dstPos + 3] = src[srcPos + 3];
		dst[dstPos + 4] = src[srcPos + 4];
	}


	/**
	 * Copy first six array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param src
	 *            Source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy5(float[] dst, float[] src)
	{
		memcpy5(dst, 0, src, 0);
	}


	/**
	 * Copy six array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy6(float[] dst, int dstPos, float[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
		dst[dstPos + 3] = src[srcPos + 3];
		dst[dstPos + 4] = src[srcPos + 4];
		dst[dstPos + 5] = src[srcPos + 5];
	}


	/**
	 * Copy first six array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param src
	 *            Source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy6(float[] dst, float[] src)
	{
		memcpy6(dst, 0, src, 0);
	}


	/**
	 * Copy eight array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy8(float[] dst, int dstPos, float[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
		dst[dstPos + 3] = src[srcPos + 3];
		dst[dstPos + 4] = src[srcPos + 4];
		dst[dstPos + 5] = src[srcPos + 5];
		dst[dstPos + 6] = src[srcPos + 6];
		dst[dstPos + 7] = src[srcPos + 7];
	}


	/**
	 * Copy first eight array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param src
	 *            Source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy8(float[] dst, float[] src)
	{
		memcpy8(dst, 0, src, 0);
	}


	/**
	 * Copy eight array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy9(float[] dst, int dstPos, float[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
		dst[dstPos + 3] = src[srcPos + 3];
		dst[dstPos + 4] = src[srcPos + 4];
		dst[dstPos + 5] = src[srcPos + 5];
		dst[dstPos + 6] = src[srcPos + 6];
		dst[dstPos + 7] = src[srcPos + 7];
		dst[dstPos + 8] = src[srcPos + 8];
	}


	/**
	 * Copy first nine array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param src
	 *            Source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy9(float[] dst, float[] src)
	{
		memcpy9(dst, 0, src, 0);
	}


	/**
	 * Copy 16 array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy16(float[] dst, int dstPos, float[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
		dst[dstPos + 3] = src[srcPos + 3];
		dst[dstPos + 4] = src[srcPos + 4];
		dst[dstPos + 5] = src[srcPos + 5];
		dst[dstPos + 6] = src[srcPos + 6];
		dst[dstPos + 7] = src[srcPos + 7];
		dst[dstPos + 8] = src[srcPos + 8];
		dst[dstPos + 9] = src[srcPos + 9];
		dst[dstPos + 10] = src[srcPos + 10];
		dst[dstPos + 11] = src[srcPos + 11];
		dst[dstPos + 12] = src[srcPos + 12];
		dst[dstPos + 13] = src[srcPos + 13];
		dst[dstPos + 14] = src[srcPos + 14];
		dst[dstPos + 15] = src[srcPos + 15];
	}


	/**
	 * Copy first 16 array elements from {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param src
	 *            Source (read-from) array
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             This method does not perform array index checking!
	 */
	public static void memcpy16(float[] dst, float[] src)
	{
		memcpy16(dst, 0, src, 0);
	}


	public static void buffer(IntBuffer buffer, int[] elements)
	{
		buffer.clear();
		buffer.put(elements);
		buffer.flip();
	}


	public static FloatBuffer buffer(float[] elements)
	{
		FloatBuffer buf = BufferUtils.createFloatBuffer(elements.length);
		buf.put(elements);
		buf.flip();
		return buf;
	}


	/**
	 * Copies the specified array of vectors to the specified {@code Buffer}.
	 * Each row in the array is assumed to be a two-component vector (i.e. an
	 * array of length 2). The {@code Buffer} is cleared before copying and
	 * flipped after copying.
	 * 
	 * @param buffer
	 *            The buffer to copy the vectors to
	 * @param vectors
	 *            The vectors
	 */
	public static void bufferVec2Array(FloatBuffer buffer, float[][] vectors)
	{
		// First squash two-dimensional array into a one-dimensional
		// array
		float[] flat = new float[2 * vectors.length];
		int i = 0;
		for (float[] vec : vectors) {
			flat[i++] = vec[0];
			flat[i++] = vec[1];
		}
		buffer.clear();
		buffer.put(flat);
		buffer.flip();
	}


	/**
	 * Copies the specified array of vectors to the specified {@code Buffer}.
	 * Each row in the array is assumed to be a three-component vector (i.e. an
	 * array of length 3). The {@code Buffer} is cleared before copying and
	 * flipped after copying.
	 * 
	 * @param buffer
	 *            The buffer to copy the vectors to
	 * @param vectors
	 *            The vectors
	 */
	public static void bufferVectors3(FloatBuffer buffer, float[][] vectors)
	{
		// First squash two-dimensional array into a one-dimensional
		// array
		float[] flat = new float[3 * vectors.length];
		int i = 0;
		for (float[] vec : vectors) {
			flat[i++] = vec[0];
			flat[i++] = vec[1];
			flat[i++] = vec[2];
		}
		buffer.clear();
		buffer.put(flat);
		buffer.flip();
	}


	public static float sqrtf(float f)
	{
		return (float) Math.sqrt(f);
	}

}
