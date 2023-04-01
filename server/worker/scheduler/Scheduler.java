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
    private Line line;

    public Scheduler(int id, Network net, Line line) {
        this.id = id;
        this.net = net;
        this.line = line;
    }

    public void run() {
        try {
            // System.out.println("\n__________________________\n");
            net.send(id, "PROGRESS");        //sending
            while(!(line.getLine() instanceof ArrayList)) {}
            ArrayList<Integer> progress = (ArrayList<Integer>) line.getLine();
            // System.out.println("\nRecieved progress : " + progress.toString() + "\n");
            line.setLine(null);
            net.send(id, "TASKS IN QUEUE");        //sending
            while(!(line.getLine() instanceof Integer)) {}
            int nbOfTasksInQueue = (int) line.getLine();
            // System.out.println("\nRecieved tasks in queue : " + nbOfTasksInQueue + "\n");
            int neededTasks = 0;
            for(int p : progress) {
                if(p > 80 | p < 0) {
                    neededTasks++;
                }
            }
            neededTasks -= nbOfTasksInQueue;
            // System.out.println("\nNeeded tasks : " + neededTasks + "\n");
            while(neededTasks > 0) {
                net.send(id, TaskGenerator.createTask());        //sending
                neededTasks--;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
} 
