/**
 * 
 */
package org.javacream.training.jvm.util.simulation.block;

public interface BlockingStrategy {
	public void block(long blockTime);
}