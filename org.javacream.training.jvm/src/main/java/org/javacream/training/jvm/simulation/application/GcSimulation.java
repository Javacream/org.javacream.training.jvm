package org.javacream.training.jvm.simulation.application;

import org.javacream.training.jvm.simulation.gc.impl.MemorySimulationImpl;
import org.javacream.training.jvm.util.simulation.block.BlockingStrategies;
import org.javacream.training.jvm.util.simulation.cpu.SimpleProcessorLoad;

public class GcSimulation {


	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Reading system parameter: mem, loops, pause, busy");
		int mem = Integer.parseInt(System.getProperty("mem", "10"));
		int loops = Integer.parseInt(System.getProperty("loops", "100"));
		long pause = Long.parseLong(System.getProperty("pause", "5"));
		long busy = Long.parseLong(System.getProperty("busy", "5"));
		
		System.out.println("using mem=" + mem + " MBytes, loops=" + loops + ", pause=" + pause + ", busy pause=" + busy);
		
		MemorySimulationImpl memorySimulation = new MemorySimulationImpl();
		SimpleProcessorLoad ejbProcessorLoad = new SimpleProcessorLoad();
		ejbProcessorLoad.setBlockingStrategy(BlockingStrategies.THREAD);
		memorySimulation.setProcessorLoad(ejbProcessorLoad);
		long start = System.currentTimeMillis();
		memorySimulation.doSimulation(mem, loops, pause, busy);
		System.out.println("memory Simulation took "+ (System.currentTimeMillis() - start) + "msec.");
		
	}

}
