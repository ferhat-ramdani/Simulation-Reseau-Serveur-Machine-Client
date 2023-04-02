/*
 * This is a TimerTask class that sends a progress request to the worker
 * corresponding to ID, than recieves progress from worker, than decides 
 * how much new tasks to send to that worker.
 */
package server.worker.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import server.worker.net.Network;

public class Scheduler extends TimerTask{
    private int id;
    private Network net;
    private Data line;

    public Scheduler(int id, Network net, Data line) {
        this.id = id;
        this.net = net;
        this.line = line;
    }

    public void run() {
        try {
            net.send(id, "PROGRESS");
            while(!(line.getData() instanceof ArrayList)) {}
            ArrayList<Integer> progress = (ArrayList<Integer>) line.getData();
            line.setData(null);
            net.send(id, "TASKS IN QUEUE");
            while(!(line.getData() instanceof Integer)) {}
            int nbOfTasksInQueue = (int) line.getData();
            int neededTasks = 0;
            for(int p : progress) {
                if(p > 80 | p < 0) {
                    neededTasks++;
                }
            }
            neededTasks -= nbOfTasksInQueue;
            while(neededTasks > 0) {
                net.send(id, TaskGenerator.createTask());
                neededTasks--;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
} 
