package org.javacream.training.jvm.simulation.memory.impl;

import java.util.ArrayList;

import org.javacream.training.jvm.simulation.memory.ApplicationSimulation;
import org.javacream.training.jvm.util.simulation.memory.KiloByte;

public class SimpleApplicationSimulation implements ApplicationSimulation {

	private ArrayList<KiloByte> session;
	private static ArrayList<KiloByte> application;

	static {
		application = new ArrayList<KiloByte>();
	}

	@Override
	public void login() {
		session = new ArrayList<KiloByte>();
		System.out.println("login");
	}

	@Override
	public void logout() {
		session = new ArrayList<KiloByte>();
		System.out.println("logout");
	}

	@Override
	public void request(int requestMem, int sessionMem, int applicationMem) {
		if (requestMem > 0) {
			new KiloByte(requestMem);
		}
		if (sessionMem > 0) {
			session.add(new KiloByte(sessionMem));
		}
		if (applicationMem > 0) {
			application.add(new KiloByte(applicationMem));
		}
		System.out.println("request done: requestMem=" + requestMem + ", sessionMem=" + sessionMem + ", applicationMem=" + applicationMem);
	}

}
