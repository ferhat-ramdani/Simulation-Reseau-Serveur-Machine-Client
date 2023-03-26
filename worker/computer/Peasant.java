/**
 * This is a thread class that retrieves a task from 
 * taskQueue, calculates the persistance of each number in 
 * task and immediatly stores it in a private completed task,
 * then sends the completed task through the taskSocket
 */
package worker.computer;

import worker.manager.*;

import java.io.IOException;
import java.util.Hashtable;

import worker.Network;
public class Peasant extends Thread {

    private int id;
    public String[] range;
    private int progress;
    private TaskQueue taskQueue;
    private CompletedTask task;
    private Network net;

    public Peasant(TaskQueue taskQueue, int id, Network net) {
        this.taskQueue = taskQueue;
        this.range = new String[3];                // [a, b, length]
        this.progress = -1;
        this.id = id;
        this.net = net;
    }

    public int getProgress() {
        return progress;
    }

    public int getID() {
        return this.id;
    }

    public void run() {
        while(true){
            range = taskQueue.getTask();
            task = new CompletedTask(range);
            System.out.println("\nAbout to start peasant, range : [" + range[0] + " , " + range[1] + "] in " + id + "\n");
            String number = range[0];
            for(int i = 0; i < Integer.parseInt(range[2]); i++) {
            // for(int i = 0; i < 10; i++) {
                int p = Calculator.calculatePersistanceOf(number);
                task.addNumber(number, p);
                // System.out.println("\n" + number + " : " + p + " \n");
                number = Calculator.incrementNumber(number);
                progress = (int) ((i + 1) / Double.parseDouble(range[2]) * 100);
                // System.out.println("\nJust updated progress : " + progress + "\n");
            }
            System.out.print("\n" + getName() + " just finished task\n");
            try {
                net.send(task);         //sending
                System.out.println("\nI succesfully sent the completed task though socket\n");
                // Thread.sleep(3600 * 1000);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}