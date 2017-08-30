package org.javacream.training.jvm.simulation.gc.impl;

import org.javacream.training.jvm.simulation.gc.MemorySimulation;
import org.javacream.training.jvm.util.simulation.cpu.ProcessorLoad;
import org.javacream.training.jvm.util.simulation.memory.MegaByte;



public class MemorySimulationImpl implements MemorySimulation {
private ProcessorLoad processorLoad;
	public void setProcessorLoad(ProcessorLoad processorLoad) {
	this.processorLoad = processorLoad;
}
	/* (non-Javadoc)
	 * @see org.javacream.simulation.MemorySimulation#doSimulation(int, int, long, long)
	 */
	@Override
	public void doSimulation(int mem, int loops, long pause, long busy){
		for (int i = 0; i < loops; i++){
			new MegaByte(mem);
			processorLoad.processorWait(pause);
			processorLoad.processorBusy(busy);
		}
	}

}
