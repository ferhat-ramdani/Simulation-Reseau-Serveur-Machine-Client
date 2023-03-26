/*
 * This class has a Queue attribute, and methods that allow to 
 * modify and queue.
 */
package worker.manager;

import java.io.IOException;
import java.util.*;
import worker.Network;

public class TaskQueue {
    private Queue<String[]> queue;
    private Network net;

    public TaskQueue(Network net) {
        this.net = net;
        this.queue = new LinkedList<>();
    }

    public synchronized void addTask(String[] task) {
        queue.add(task);
        this.notifyAll();
    }

    public synchronized String[] getTask() {
        while(this.isEmpty()){
            try {
                // System.out.println("\nPeasant waiting ...\n");
                this.wait();
                // System.out.println("\nJust recieved notify, no more waiting !\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // System.out.println("\nreturning range ! \n");
        return queue.poll();
    }

    public Boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getAvailableTasks() {
        return queue.size();
    }

}