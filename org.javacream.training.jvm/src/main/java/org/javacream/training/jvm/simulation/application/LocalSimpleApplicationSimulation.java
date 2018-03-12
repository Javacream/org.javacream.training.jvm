package org.javacream.training.jvm.simulation.application;

import org.javacream.training.jvm.simulation.gc.impl.MemorySimulationImpl;
import org.javacream.training.jvm.simulation.memory.impl.SimpleApplicationSimulation;
import org.javacream.training.jvm.util.simulation.block.BlockingStrategies;
import org.javacream.training.jvm.util.simulation.cpu.SimpleProcessorLoad;

public class LocalSimpleApplicationSimulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleApplicationSimulationGui gui = new SimpleApplicationSimulationGui();
		gui.setApplicationSimulation(new SimpleApplicationSimulation());
		MemorySimulationImpl memorySimulation = new MemorySimulationImpl();
		SimpleProcessorLoad ejbProcessorLoad = new SimpleProcessorLoad();
		ejbProcessorLoad.setBlockingStrategy(BlockingStrategies.THREAD);
		memorySimulation.setProcessorLoad(ejbProcessorLoad);
		gui.setMemorySimulation(memorySimulation);
	}

}
