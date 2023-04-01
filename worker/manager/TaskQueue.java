/*
 * This class has a Queue attribute, and methods that allow to 
 * modify the queue.
 */
package worker.manager;

import java.util.*;

public class TaskQueue {
    private Queue<String[]> tasks;
    private Queue<CompletedTask> doneTasks;

    public TaskQueue() {
        this.tasks = new LinkedList<>();
        this.doneTasks = new LinkedList<>();
    }

    public synchronized void addTask(String[] task) {
        tasks.add(task);
        this.notifyAll();
    }

    public synchronized void addDoneTask(CompletedTask doneTask) {
        doneTasks.add(doneTask);
        this.notifyAll();
    }

    public synchronized String[] getTask() {
        while(tasks.isEmpty()){
            try {
                // System.out.println("\nPeasant waiting ...\n");
                this.wait();
                // System.out.println("\nJust recieved notify, no more waiting !\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // System.out.println("\nreturning range ! \n");
        return tasks.poll();
    }

    public synchronized CompletedTask getDoneTask() {
        while(doneTasks.isEmpty()){
            try {
                // System.out.println("\nPeasant waiting ...\n");
                this.wait();
                // System.out.println("\nJust recieved notify, no more waiting !\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nNotified and returning doneTask ...\n");
        return doneTasks.poll();
    }

    public int getAvailableTasks() {
        return tasks.size();
    }

    public synchronized Queue<String[]> getTasks() {
        return tasks;
    }

    public synchronized Queue<CompletedTask> getDoneTasks() {
        return doneTasks;
    }
    
}