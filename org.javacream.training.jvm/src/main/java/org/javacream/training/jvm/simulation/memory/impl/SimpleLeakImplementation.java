package org.javacream.training.jvm.simulation.memory.impl;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import org.javacream.training.jvm.simulation.memory.LeakSimulation;
import org.javacream.training.jvm.util.simulation.memory.KiloByte;

public class SimpleLeakImplementation implements LeakSimulation {

	private static List<KiloByte> leak;
	private static List<SoftReference<KiloByte>> cache;

	{
		leak = new ArrayList<KiloByte>();
		cache = new ArrayList<SoftReference<KiloByte>>();
	}

	@Override
	public long addToLeak(int kilobyte) {
		leak.add(new KiloByte(kilobyte));
		return Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
	}

	public long addToCache(int kilobyte) {
		cache.add(new SoftReference<KiloByte>(new KiloByte(kilobyte)));
		return Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
	}

	
}
