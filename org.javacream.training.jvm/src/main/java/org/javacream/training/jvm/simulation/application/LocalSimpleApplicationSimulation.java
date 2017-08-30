package org.javacream.training.jvm.simulation.application;

import org.javacream.training.jvm.simulation.memory.impl.SimpleApplicationSimulation;

public class LocalSimpleApplicationSimulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleApplicationSimulationGui gui = new SimpleApplicationSimulationGui();
		gui.setApplicationSimulation(new SimpleApplicationSimulation());
	}

}
