package org.javacream.training.jvm.simulation.memory;

public interface ApplicationSimulation {

	public void login();
	public void logout();
	public void request(int requestMem, int sessionMem, int applicationMem);
}
