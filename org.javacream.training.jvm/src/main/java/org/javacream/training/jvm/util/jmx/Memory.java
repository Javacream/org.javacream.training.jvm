package org.javacream.training.jvm.util.jmx;

public class Memory implements MemoryMBean {

	private int warnThreshold = 10; 
	private int criticalThreshold = 5; 
	/* (non-Javadoc)
	 * @see org.javacream.util.jmx.MemoryMBean#getWarnThreshold()
	 */
	@Override
	public int getWarnThreshold() {
		return warnThreshold;
	}
	/* (non-Javadoc)
	 * @see org.javacream.util.jmx.MemoryMBean#setWarnThreshold(int)
	 */
	@Override
	public void setWarnThreshold(int warnThreshold) {
		this.warnThreshold = warnThreshold;
	}
	/* (non-Javadoc)
	 * @see org.javacream.util.jmx.MemoryMBean#getCriticalThreshold()
	 */
	@Override
	public int getCriticalThreshold() {
		return criticalThreshold;
	}
	/* (non-Javadoc)
	 * @see org.javacream.util.jmx.MemoryMBean#setCriticalThreshold(int)
	 */
	@Override
	public void setCriticalThreshold(int criticalThreshold) {
		this.criticalThreshold = criticalThreshold;
	}
	/* (non-Javadoc)
	 * @see org.javacream.util.jmx.MemoryMBean#getFreeMemory()
	 */
	@Override
	public long getFreeMemory(){
		return Runtime.getRuntime().freeMemory();
		
	}
	/* (non-Javadoc)
	 * @see org.javacream.util.jmx.MemoryMBean#getMaxFreeMemory()
	 */
	@Override
	public long getMaxFreeMemory(){
		Runtime runtime = Runtime.getRuntime();
		return runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory()); 
	}
	
	/* (non-Javadoc)
	 * @see org.javacream.util.jmx.MemoryMBean#getState()
	 */
	@Override
	public String getState(){
		System.gc();
		String state = "OK";
		long maxMemory = Runtime.getRuntime().maxMemory();
		long maxFreeMemory = getMaxFreeMemory();
		if ((100* maxFreeMemory)/maxMemory < warnThreshold){
			state = "WARN";
		}
		if ((100* maxFreeMemory)/maxMemory < criticalThreshold){
			state = "CRITICAL";
		}
		return state;
	}


}
