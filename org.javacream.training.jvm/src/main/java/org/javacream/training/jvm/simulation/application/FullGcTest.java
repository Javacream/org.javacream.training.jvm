package org.javacream.training.jvm.simulation.application;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Random;

import org.javacream.training.jvm.util.simulation.memory.KiloByte;

public class FullGcTest {

	public static void main(String[] args) {
		while(true){
			Random random = new Random();
			ArrayList<SoftReference<KiloByte>> cache = new ArrayList<SoftReference<KiloByte>>();
			int numberOfBytes = (int)Math.abs(random.nextDouble() * 10000.0) + 1000;
			System.out.println(numberOfBytes);
			cache.add(new SoftReference<KiloByte>(new KiloByte(numberOfBytes)));
		}
	}
}
