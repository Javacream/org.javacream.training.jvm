package org.javacream.training.jvm.util.simulation.memory;

import java.io.Serializable;

/**
 * One KiloByte instance consumes 1 KByte memory
 * 
 * @author Dr. Rainer Sawitzki
 * 
 */
public class KiloByte implements Serializable, MemoryConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	byte[][] kilobyte;

	public KiloByte() {
		this(1);
	}

	public KiloByte(int kByte) {
		kilobyte = new byte[kByte][1024];
	}

}
