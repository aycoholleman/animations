package org.domainobject.animation.util;

public class Array {

	private Array()
	{
	}


	public static void fill14(int[] array, int val)
	{
		array[0] = val;
		array[1] = val;
		array[2] = val;
		array[3] = val;
		array[4] = val;
		array[5] = val;
		array[6] = val;
		array[7] = val;
		array[8] = val;
		array[9] = val;
		array[10] = val;
		array[11] = val;
		array[12] = val;
		array[13] = val;
	}


	public static void fill17(int[] array, int val)
	{
		array[0] = val;
		array[1] = val;
		array[2] = val;
		array[3] = val;
		array[4] = val;
		array[5] = val;
		array[6] = val;
		array[7] = val;
		array[8] = val;
		array[9] = val;
		array[10] = val;
		array[11] = val;
		array[12] = val;
		array[13] = val;
		array[14] = val;
		array[15] = val;
		array[16] = val;
	}


	public static int[] fill14(int val)
	{
		int[] array = new int[14];
		fill14(array, val);
		return array;
	}


	public static int[] fill17(int val)
	{
		int[] array = new int[17];
		fill17(array, val);
		return array;
	}


	public static void set(float[] dest, int offset, float e0, float e1)
	{
		dest[offset + 0] = e0;
		dest[offset + 1] = e1;
	}


	public static void set(float[] dest, int offset, float e0, float e1, float e2)
	{
		dest[offset + 0] = e0;
		dest[offset + 1] = e1;
		dest[offset + 2] = e2;
	}


	public static void set(float[] dest, int offset, float e0, float e1, float e2, float e3)
	{
		dest[offset + 0] = e0;
		dest[offset + 1] = e1;
		dest[offset + 2] = e2;
		dest[offset + 3] = e3;
	}


	public static void set(float[] dest, int offset, float e0, float e1, float e2, float e3, float e4)
	{
		dest[offset + 0] = e0;
		dest[offset + 1] = e1;
		dest[offset + 2] = e2;
		dest[offset + 3] = e3;
		dest[offset + 4] = e4;
	}


	public static void set(float[] dest, int offset, float e0, float e1, float e2, float e3, float e4, float e5)
	{
		dest[offset + 0] = e0;
		dest[offset + 1] = e1;
		dest[offset + 2] = e2;
		dest[offset + 3] = e3;
		dest[offset + 4] = e4;
		dest[offset + 5] = e5;
	}


	public static void set(float[] dest, int offset, float e0, float e1, float e2, float e3, float e4, float e5,
			float e6)
	{
		dest[offset + 0] = e0;
		dest[offset + 1] = e1;
		dest[offset + 2] = e2;
		dest[offset + 3] = e3;
		dest[offset + 4] = e4;
		dest[offset + 5] = e5;
		dest[offset + 6] = e6;
	}

}
