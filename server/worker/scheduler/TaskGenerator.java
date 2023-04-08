/*
 * This is a utilitary class that has a lowerBound attribute 
 * and a createTask method
 */
package server.worker.scheduler;

import config.Cts;
import worker.computer.Calculator;

public class TaskGenerator {
    private static String lowerBound  = Cts.START;
    public static String[] createTask() {
        return (new String[] {
            lowerBound, 
            lowerBound = Calculator.addIntervalToString(lowerBound, Cts.INTERVAL_SIZE)
        });
    }
}