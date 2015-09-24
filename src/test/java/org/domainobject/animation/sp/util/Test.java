package org.domainobject.animation.sp.util;

import java.text.DecimalFormat;
import java.util.Random;

public class Test {
	
	DecimalFormat df = new DecimalFormat("000,000.000");

	public static void main(String[] args) {
		new Test().test();
	}
	
	public void test() {
		float[] src = new float[16];
		Random rand = new Random();
		for (int i = 0; i < 16; i++) {
			src[i] = rand.nextFloat();
		}
		for(int j=0;j<100;j++) {
			test1(src);
			src[j%16] = rand.nextFloat();
			System.gc();
			test2(src);
			src[j%16] = rand.nextFloat();
			System.gc();
			test2(src);
			src[j%16] = rand.nextFloat();
			System.gc();
			test1(src);
			src[j%16] = rand.nextFloat();
			System.gc();
		}
	}

	public void test1(float[] src) {
		float[] trg = new float[16];
		long start = System.currentTimeMillis();
		for (int j = 0; j < 2000000000; j++) {
			cpy(trg, 0, src, 0);
		}
		long end = System.currentTimeMillis();
		System.out.println("test1 took: " + df.format(((double)(end-start))/1000D));
	}

	public void test2(float[] src) {
		float[] trg = new float[16];
		long start = System.currentTimeMillis();
		for (int j = 0; j < 1000000; j++) {
			C2J.memcpy16(trg, 0, src, 0);
		}
		long end = System.currentTimeMillis();
		System.out.println("test2 took: " + df.format(((double)(end-start))/1000D));
	}

	private static void cpy(float[] trg, int trgOff, float[] src, int srcOff) {
		for (int i = 0; i < 16; i++)
			trg[trgOff + i] = src[srcOff + i];
	}

}
