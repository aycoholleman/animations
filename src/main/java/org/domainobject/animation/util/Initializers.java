package org.domainobject.animation.util;

public class Initializers {

	private Initializers()
	{
	}


	//@formatter:off
	public static void initialize14(int[] array, int val)
	{
		array[0] = val; array[1] = val; array[2] = val; array[3] = val;
		array[4] = val; array[5] = val; array[6] = val; array[7] = val;
		array[8] = val; array[9] = val; array[10] = val; array[11] = val;
		array[12] = val; array[13] = val;
	}
	public static void initialize17(int[] array, int val)
	{
		array[0] = val; array[1] = val; array[2] = val; array[3] = val;
		array[4] = val; array[5] = val; array[6] = val; array[7] = val;
		array[8] = val; array[9] = val; array[10] = val; array[11] = val;
		array[12] = val; array[13] = val; array[14] = val; array[15] = val;
		array[16] = val;
	}
	//@formatter:on

	public static int[] initialize14(int val)
	{
		int[] array = new int[14];
		initialize14(array, val);
		return array;
	}


	public static int[] initialize17(int val)
	{
		int[] array = new int[17];
		initialize17(array, val);
		return array;
	}

}
