package org.javacream.training.jvm.util.jmx;

public interface MemoryMBean {

	public abstract int getWarnThreshold();

	public abstract void setWarnThreshold(int warnThreshold);

	public abstract int getCriticalThreshold();

	public abstract void setCriticalThreshold(int criticalThreshold);

	public abstract long getFreeMemory();

	public abstract long getMaxFreeMemory();

	public abstract String getState();

}