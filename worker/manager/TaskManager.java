/**
 * This is a thread class that periodically listens 
 * through the server, and wheneven it recieves a task, 
 * it adds it to the queue and notifies the peasants
 */
package worker.manager;

import java.io.IOException;
import java.util.*;

import worker.Network;
import worker.computer.Peasant;

public class TaskManager extends Thread {
    private TaskQueue taskQueue;
    private Network net;
    private Peasant[] peasants;
    public TaskManager(TaskQueue taskQueue, Network net, Peasant[] peasants) {
        this.taskQueue = taskQueue;
        this.net = net;
        this.peasants = peasants;
    }

    public void run() {
        while(true){
            try {
                Object receivedObject = net.recieve();            //reading
                System.out.println("The read object is : " + receivedObject + " in TaskManager");
                if(receivedObject instanceof String[]) {
                    String[] task = (String[]) receivedObject;     
                    taskQueue.addTask(task);
                } else if(receivedObject instanceof String) {
                    String request = (String) receivedObject;
                    processor(request);
                }
            } catch (ClassNotFoundException | IOException e) { e.printStackTrace();}          
        }
    }

    private void processor(String request) throws IOException {
        switch (request) {
            case "PROGRESS":
                ArrayList<Integer> progress = new ArrayList<>();
                for(Peasant peasant : peasants) {
                    progress.add(peasant.getProgress());
                }
                net.send(progress);         //sending
                break;
                
            case "TASKS IN QUEUE":
                net.send(taskQueue.getAvailableTasks());
                break;
        
            default:
                break;
        }
    }
}