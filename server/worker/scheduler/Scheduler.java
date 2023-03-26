/*
 * This is a TimerTask class that sends a progress request to the worker
 * corresponding to ID, than recieves progress from worker, than decides 
 * how much new tasks to send to that worker.
 */
package server.worker.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import server.memory.Memory;
import server.worker.net.Network;
import worker.manager.CompletedTask;

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
            System.out.println("\n__________________________\n");
            // System.out.println("\nSending : PROGRESS in Scheduler\n");
            net.send(id, "PROGRESS");        //sending
            while(!(line.getLine() instanceof ArrayList)) {}
            ArrayList<Integer> progress = (ArrayList<Integer>) line.getLine();
            line.setLine(null);
            // System.out.print("The recieved progress is : " + progress + "\n");
            // System.out.print("\nSending TASKS IN QUEUE in Scheduler\n");
            net.send(id, "TASKS IN QUEUE");        //sending
            while(!(line.getLine() instanceof Integer)) {}
            int nbOfTasksInQueue = (int) line.getLine();
            // System.out.print("\nRecieved TASKS IN QUEUE : " + nbOfTasksInQueue + "\n");
            int neededTasks = 0;
            for(int p : progress) {
                if(p > 80 | p < 0) {
                    neededTasks++;
                }
            }
            neededTasks -= nbOfTasksInQueue;
            // System.out.print("\nFound " + neededTasks + " needed tasks in Scheduler\n");
            // System.out.print("\nSending the needed tasks in Scheduler\n");
            while(neededTasks > 0) {
                net.send(id, TaskGenerator.createTask());        //sending
                neededTasks--;
                // Thread.sleep(3600 * 1000);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
} 
