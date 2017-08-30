/**
 * 
 */
package org.javacream.training.jvm.util.simulation.cpu;

import org.javacream.training.jvm.util.simulation.block.BlockingStrategy;

/**
 * Default Implementation of ProcessorLoad inside a J2EE Container
 * 
 * @author Dr. Rainer Sawitzki
 * 
 */
public class SimpleProcessorLoad implements ProcessorLoad {

	private BlockingStrategy blockingStrategy;

	public void processorBusy(long busyTime) {

		long start = System.currentTimeMillis();
		long delta = start + busyTime;
		while (delta > System.currentTimeMillis()) {
		}
	}

	public void processorWait(long blockingTime) {
		blockingStrategy.block(blockingTime);
	}

	public void setBlockingStrategy(BlockingStrategy blockingStrategy) {
		this.blockingStrategy = blockingStrategy;
	}

}
