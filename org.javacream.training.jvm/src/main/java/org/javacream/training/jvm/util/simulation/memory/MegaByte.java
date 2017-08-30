package org.javacream.training.jvm.util.simulation.memory;

import java.io.Serializable;

/**
 * One MegaByte instance consumes 1 MByte memory
 * 
 * @author Dr. Rainer Sawitzki
 * 
 */
public class MegaByte implements Serializable, MemoryConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -531412137385987686L;

	byte[][][] megabyte;

	public MegaByte() {
		this(1);
	}

	public MegaByte(int mByte) {
		megabyte = new byte[mByte][1024][1024];
	}

}
