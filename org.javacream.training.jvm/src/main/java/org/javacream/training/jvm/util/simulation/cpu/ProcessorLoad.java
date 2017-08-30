/**
 * 
 */
package org.javacream.training.jvm.util.simulation.cpu;

/**
 * @author Dr. Rainer Sawitzki
 *
 */
public interface ProcessorLoad {
   void processorBusy(long load);
   void processorWait(long blockingTime);
}
