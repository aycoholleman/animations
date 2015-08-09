package org.domainobject.animation.sp.util;

import static java.lang.Math.abs;

/**
 * @author Ayco Holleman
 */
public final class Comparators {

	/**
	 * The mininum difference between two numbers for them to count as
	 * distinguishable. If the difference between the numbers is less than
	 * {@code distinguishable} they are taken to be the same.
	 */
	public static float distinguishable = 0.001f;


	private Comparators()
	{
	}


	/**
	 * Returns {@code true} if the absolute difference between a and b is less
	 * than {@link #distinguishable}, {@code false} otherwise.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean same(float a, float b)
	{
		return abs(a - b) < distinguishable;
	}


	/**
	 * Returns {@code true} if the absolute difference between a and b is less
	 * than {@code distinguishable}, {@code false} otherwise.
	 * 
	 * @param a
	 * @param b
	 * @param distinguishable
	 * @return
	 */
	public static boolean same(float a, float b, float distinguishable)
	{
		return abs(a - b) < distinguishable;
	}


	/**
	 * Returns {@code true} if all elements of a and b are, pair-wise,
	 * indistinguishable, {@code false otherwise}. The treshold for
	 * distinguishability is set by {@link #distinguishable}.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean same(float[] a, float[] b)
	{
		return same(a, b, distinguishable);
	}


	/**
	 * Returns {@code true} if all elements of a and b are, pair-wise,
	 * indistinguishable, {@code false otherwise}.
	 * 
	 * @param a
	 * @param b
	 * @param distinguishable
	 * @return
	 */
	public static boolean same(float[] a, float[] b, float distinguishable)
	{
		return same(a, 0, b, 0, a.length, distinguishable);
	}


	/**
	 * Returns {@code true} if the specified elements of a and b are, pair-wise,
	 * indistinguishable, {@code false otherwise}. The treshold for
	 * distinguishability is set by {@link #distinguishable}.
	 * 
	 * @param a
	 *        The first array
	 * @param aOffset
	 *        The offset into the first array
	 * @param b
	 *        The second array
	 * @param bOffset
	 *        The offset into the second array
	 * @param length
	 *        The number of array elements to compare
	 * @return
	 */
	public static boolean same(float[] a, int aOffset, float[] b, int bOffset, int length)
	{
		return same(a, aOffset, b, bOffset, length, distinguishable);
	}


	/**
	 * Returns {@code true} if the specified elements of a and b are, pair-wise,
	 * indistinguishable, {@code false otherwise}.
	 * 
	 * @param a
	 *        The first array
	 * @param aOffset
	 *        The offset into the first array
	 * @param b
	 *        The second array
	 * @param bOffset
	 *        The offset into the second array
	 * @param length
	 *        The number of array elements to compare
	 * @param distinguishable
	 *        The treshold for distinguishability
	 * @return
	 */
	public static boolean same(float[] a, int aOffset, float[] b, int bOffset, int length,
			float distinguishable)
	{
		for (int i = 0; i < length; ++i)
			if (!same(a[aOffset + i], b[bOffset + i], distinguishable))
				return false;
		return true;
	}


	public static boolean same2(float[] a, float[] b)
	{
		return same2(a, b, distinguishable);
	}


	public static boolean same2(float[] a, float[] b, float distinguishable)
	{
		return same2(a, 0, b, 0, distinguishable);
	}


	public static boolean same2(float[] a, int aOffset, float[] b, int bOffset)
	{
		return same2(a, aOffset, b, bOffset, distinguishable);
	}


	public static boolean same2(float[] a, int aOffset, float[] b, int bOffset, float distinguishable)
	{
		return same(a[aOffset + 0], b[bOffset + 0], distinguishable)
				&& same(a[aOffset + 1], b[bOffset + 1], distinguishable);
	}


	public static boolean same3(float[] a, float[] b)
	{
		return same3(a, b, distinguishable);
	}


	public static boolean same3(float[] a, float[] b, float distinguishable)
	{
		return same3(a, 0, b, 0, distinguishable);
	}


	public static boolean same3(float[] a, int aOffset, float[] b, int bOffset)
	{
		return same3(a, aOffset, b, bOffset, distinguishable);
	}


	public static boolean same3(float[] a, int aOffset, float[] b, int bOffset, float distinguishable)
	{
		return same(a[aOffset + 0], b[bOffset + 0], distinguishable)
				&& same(a[aOffset + 1], b[bOffset + 1], distinguishable)
				&& same(a[aOffset + 2], b[bOffset + 2], distinguishable);
	}


	public static boolean same4(float[] a, float[] b)
	{
		return same4(a, b, distinguishable);
	}


	public static boolean same4(float[] a, float[] b, float distinguishable)
	{
		return same4(a, 0, b, 0, distinguishable);
	}


	public static boolean same4(float[] a, int aOffset, float[] b, int bOffset)
	{
		return same4(a, aOffset, b, bOffset, distinguishable);
	}


	public static boolean same4(float[] a, int aOffset, float[] b, int bOffset, float distinguishable)
	{
		return same(a[aOffset + 0], b[bOffset + 0], distinguishable)
				&& same(a[aOffset + 1], b[bOffset + 1], distinguishable)
				&& same(a[aOffset + 2], b[bOffset + 2], distinguishable)
				&& same(a[aOffset + 3], b[bOffset + 3], distinguishable);
	}

}