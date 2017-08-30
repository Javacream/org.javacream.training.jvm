package org.javacream.training.jvm.simulation.cpu;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.ObjectName;

import org.javacream.training.jvm.util.jmx.JmxCache;
import org.javacream.training.jvm.util.simulation.block.BlockingStrategies;
import org.javacream.training.jvm.util.simulation.cpu.SimpleProcessorLoad;

public class CpuSimulation {

	public void doSimulation(int threadPoolSize, long blockingTime,
			long busyTime) {
		ExecutorService executorService = Executors
				.newFixedThreadPool(threadPoolSize);
		for (int i = 0; i < threadPoolSize; i++) {
			executorService.execute(new CpuSimulationRunnable(blockingTime,
					busyTime));
		}
	}

	private class CpuSimulationRunnable implements Runnable {

		private long blockingTime;
		private long busyTime;
		private SimpleProcessorLoad simpleProcessorLoad;

		public CpuSimulationRunnable(long blockingTime, long busyTime) {
			this.blockingTime = blockingTime;
			this.busyTime = busyTime;
			simpleProcessorLoad = new SimpleProcessorLoad();
			simpleProcessorLoad.setBlockingStrategy(BlockingStrategies.THREAD);

			try {
				JmxCache cache = new JmxCache();
				ObjectName cacheName = new ObjectName(
						"javacream:service=jmx,type=cache,id=" + this.hashCode());
				ManagementFactory.getPlatformMBeanServer().registerMBean(cache,
						cacheName);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void run() {
			while (true) {
				simpleProcessorLoad.processorBusy(busyTime);
				simpleProcessorLoad.processorWait(blockingTime);
			}
		}
	}

	public static void main(String[] args) {
		new CpuSimulation().doSimulation(10, 10, 2);
		//new Mx4JStarter().startWithDefaults();
	}
}
