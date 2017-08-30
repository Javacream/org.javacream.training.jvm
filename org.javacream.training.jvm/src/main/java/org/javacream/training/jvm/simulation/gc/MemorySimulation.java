package org.javacream.training.jvm.simulation.gc;

public interface MemorySimulation {

	public abstract void doSimulation(int mem, int loops, long pause, long busy);

}