/**
 * This is a thread class that retrieves a task from 
 * taskQueue, calculates the persistance of each number in 
 * task and immediatly stores it in a private completed task,
 * then sends the completed task.
 */
package worker.computer;

import worker.manager.*;

import config.Cts;
import worker.Main;
import worker.Network;
public class Peasant extends Thread {

    private int id;
    public String[] range;
    private int progress;
    private TaskQueue taskQueue;
    private CompletedTask task;

    public Peasant(TaskQueue taskQueue, int id) {
        this.taskQueue = taskQueue;
        this.range = new String[2];                // [a, b]
        this.progress = -1;
        this.id = id;
    }

    public int getProgress() {
        return progress;
    }

    public int getID() {
        return this.id;
    }

    public void run() {
        while(!Main.off){
            range = taskQueue.getTask();
            task = new CompletedTask(range);
            String number = range[0];
            for(int i = 0; i < Integer.parseInt(Cts.INTERVAL_SIZE); i++) {
                int p = Calculator.calculatePersistanceOf(number);
                task.addNumber(number, p);
                number = Calculator.incrementNumber(number);
                progress = (int) ((i + 1) / Double.parseDouble(Cts.INTERVAL_SIZE) * 100);
            }
            taskQueue.addDoneTask(task);
            progress = -1;
        }
    }
}