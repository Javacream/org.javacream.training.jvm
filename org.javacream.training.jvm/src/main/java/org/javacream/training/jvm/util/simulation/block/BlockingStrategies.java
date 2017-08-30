package org.javacream.training.jvm.util.simulation.block;

public interface BlockingStrategies {

	public static final BlockingStrategy THREAD = new BlockingStrategy() {

		public void block(long blockTime) {
			try {
				Thread.sleep(blockTime);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	};


}
