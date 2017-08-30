package org.javacream.training.jvm.simulation.application;

import org.javacream.training.jvm.simulation.memory.LeakSimulation;
import org.javacream.training.jvm.simulation.memory.impl.SimpleLeakImplementation;

public class LocalLeakApplication {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Reading system parameter: mem, loops");
		int mem = Integer.parseInt(System.getProperty("mem", "100"));
		int loops = Integer.parseInt(System.getProperty("loops", "100"));
		System.out.println("using mem=" + mem + " KBytes, loops=" + loops);

		LeakSimulation leakSimulation = new SimpleLeakImplementation();
		for (int i = 0; i < loops; i++){
			System.out.println(leakSimulation.addToLeak(mem));
		}
		
		Object sync = new Object();
		synchronized (sync) {
			sync.wait();
		}
	}
}
