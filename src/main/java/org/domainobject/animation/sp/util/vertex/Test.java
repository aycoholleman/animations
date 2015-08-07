package org.domainobject.animation.sp.util.vertex;

import java.nio.FloatBuffer;
import java.text.DecimalFormat;
import java.util.Random;

import org.lwjgl.BufferUtils;

public class Test {

	public static void main(String[] args)
	{
		int size = 1000 * 1000 * 100;
		int repeats = 100;
		test0(size, repeats);
	}


	private static void test0(int size, int repeats)
	{
		DecimalFormat df = new DecimalFormat("0000.00");
		Random r = new Random();

		float[] source0 = new float[size];
		float[] source1 = new float[size];
		for (int i = 0; i < size; ++i) {
			source0[i] = r.nextFloat();
			source1[i] = r.nextFloat();
		}

		FloatBuffer buffer = BufferUtils.createFloatBuffer(size);
		float[] array = new float[size];

		long start1 = System.currentTimeMillis();
		for (int j = 0; j < repeats; ++j) {
			buffer.clear();
			for (int i = 0; i < size; ++i) {
				array[i] = (i + start1) % 2 == 0 ? source0[i] : source1[i];
			}
			buffer.put(array);
			buffer.flip();
		}
		String seconds1 = df.format((double) (System.currentTimeMillis() - start1) / 1000D);
		System.out.println("Using float array ........... : " + seconds1 + " seconds");

		System.gc();

		long start2 = System.currentTimeMillis();
		for (int j = 0; j < repeats; ++j) {
			buffer.clear();
			for (int i = 0; i < size; ++i) {
				buffer.put((i + start2) % 2 == 0 ? source0[i] : source1[i]);
			}
			buffer.flip();
		}
		String seconds2 = df.format((double) (System.currentTimeMillis() - start2) / 1000D);
		System.out.println("Using FloatBuffer directly .. : " + seconds2 + " seconds");
	}
}
