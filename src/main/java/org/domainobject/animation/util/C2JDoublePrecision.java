package org.domainobject.animation.util;

import java.nio.DoubleBuffer;

/**
 * Utility class with C-ish methods, used to make the Java code look as much as
 * possible like the original C/C++code, and to make the original C/C++ code as
 * copy-pastable as possible.
 * 
 * @author ayco
 *
 */
public class C2JDoublePrecision {

	/**
	 * Size in bytes of a double (8 bytes).
	 */
	public static final int SIZE_OF_DOUBLE = 8;


	private C2JDoublePrecision()
	{
	}


	/**
	 * Get size in bytes of the specified array
	 * 
	 * @param array
	 *            The array whose size in bytes to measure
	 * @return
	 */
	public static int sizeof(double[] array)
	{
		return array.length << 3;
	}


	/**
	 * Get size in bytes of the specified two-dimensional array. Assumes rows
	 * all have the same length.
	 * 
	 * @param array
	 *            The array whose size in bytes to measure
	 * @return
	 */
	public static int sizeof(double[][] array)
	{
		if (array.length == 0) {
			return 0;
		}
		return (array.length * array[0].length) << 3;
	}


	/**
	 * Copy first two array elements of {@code src} to {@code dst}.
	 * 
	 * @param dst
	 *            The destination (write-to) array
	 * @param src
	 *            The source (read-from) array
	 */
	public static void memcpy2(double[] dst, double[] src)
	{
		dst[0] = src[0];
		dst[1] = src[1];
	}


	/**
	 * Equivalent to System.arraycopy with length parameter set to 3 (i.e. three
	 * elements will be copied from src to dest).
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 */
	public static void memcpy3(double[] dst, int dstPos, double[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
	}


	public static void memcpy3(double[] dst, double[] src)
	{
		memcpy3(dst, 0, src, 0);
	}


	/**
	 * Equivalent to System.arraycopy with length parameter set to 4 (i.e. four
	 * elements will be copied from src to dest).
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 */
	public static void memcpy4(double[] dst, int dstPos, double[] src, int srcPos)
	{
		dst[dstPos + 0] = src[srcPos + 0];
		dst[dstPos + 1] = src[srcPos + 1];
		dst[dstPos + 2] = src[srcPos + 2];
		dst[dstPos + 3] = src[srcPos + 3];
	}


	public static void memcpy4(double[] dst, double[] src)
	{
		memcpy4(dst, 0, src, 0);
	}


	/**
	 * Equivalent to System.arraycopy with length parameter set to 9 (i.e. nine
	 * elements will be copied from src to dest).
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 */
	public static void memcpy9(double[] dst, int dstPos, double[] src, int srcPos)
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


	public static void memcpy9(double[] dst, double[] src)
	{
		memcpy9(dst, 0, src, 0);
	}


	/**
	 * Equivalent to System.arraycopy with length parameter set to 16 (i.e. 16
	 * elements will be copied from src to dest).
	 * 
	 * @param dst
	 *            Destination (write-to) array
	 * @param dstPos
	 *            Index from which to insert into destination array
	 * @param src
	 *            Source (read-from) array
	 * @param srcPos
	 *            Index from which the read the source array
	 */
	public static void memcpy16(double[] dst, int dstPos, double[] src, int srcPos)
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


	public static void memcpy16(double[] dst, double[] src)
	{
		memcpy16(dst, 0, src, 0);
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
	public static void bufferVectors2(DoubleBuffer buffer, double[][] vectors)
	{
		// First squash two-dimensional array into a one-dimensional
		// array
		double[] flat = new double[2 * vectors.length];
		int i = 0;
		for (double[] vec : vectors) {
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
	public static void bufferVectors3(DoubleBuffer buffer, double[][] vectors)
	{
		// First squash two-dimensional array into a one-dimensional
		// array
		double[] flat = new double[3 * vectors.length];
		int i = 0;
		for (double[] vec : vectors) {
			flat[i++] = vec[0];
			flat[i++] = vec[1];
			flat[i++] = vec[2];
		}
		buffer.clear();
		buffer.put(flat);
		buffer.flip();
	}


	public static double sqrt(double d)
	{
		return Math.sqrt(d);
	}

}
