package org.javacream.util.simulation.block;

import org.javacream.training.jvm.util.simulation.block.BlockingStrategies;

import junit.framework.TestCase;

public class BlockingStrategiesTest extends TestCase {

	public void testThread(){
		final int blockTime = 3000;
		long start = System.currentTimeMillis();
		BlockingStrategies.THREAD.block(blockTime);
		long end = System.currentTimeMillis() - start;
		assertTrue(end >= blockTime);
	}
}
