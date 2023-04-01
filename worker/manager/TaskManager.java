
package worker.manager;

import java.io.IOException;
import java.util.*;

import worker.Network;
import worker.computer.Peasant;

public class TaskManager {
    private TaskQueue taskQueue;
    private Network net;
    private Peasant[] peasants;
    public TaskManager(TaskQueue taskQueue, Network net, Peasant[] peasants) {
        this.taskQueue = taskQueue;
        this.net = net;
        this.peasants = peasants;
    }

    public void listen() {
        Runnable listenable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Object receivedObject = net.recieve();            //reading
                        // System.out.println("The read object is : " + receivedObject + " in TaskManager");
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
        };

        Runnable dispatchable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        net.send(taskQueue.getDoneTask());
                        // System.out.println("\nDone sending doneTask");
                    } catch (IOException e) { e.printStackTrace();}
                }
            }
        };

        Runnable displayable = new Runnable() {
            @Override
            public void run() {
                // try {
                //     Thread.sleep(10 * 1000);
                // } catch (InterruptedException e) { e.printStackTrace();}
                while(true){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\n______________Tasks_____________\n");
                    for (String[] task : taskQueue.getTasks()) {
                        System.out.println(Arrays.toString(task));
                    }
                    System.out.println("\n______________Peasants_____________\n");
                    for (Peasant peasant : peasants) {
                        System.out.println(peasant.getName() + " : " + peasant.getProgress());
                    }
                    System.out.println("\n______________Done_Tasks_____________\n");
                    for (CompletedTask doneTask : taskQueue.getDoneTasks()) {
                        System.out.println(Arrays.toString(doneTask.getRange()));
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) { e.printStackTrace();}
                }
            }
        };

        Thread listener = new Thread(listenable);
        listener.start();
        Thread dispatcher = new Thread(dispatchable);
        dispatcher.start();
        Thread displayer = new Thread(displayable);
        displayer.start();
    }

    // public void run() {
    //     while(true){
    //         try {
    //             Object receivedObject = net.recieve();            //reading
    //             // System.out.println("The read object is : " + receivedObject + " in TaskManager");
    //             if(receivedObject instanceof String[]) {
    //                 String[] task = (String[]) receivedObject;     
    //                 taskQueue.addTask(task);
    //             } else if(receivedObject instanceof String) {
    //                 String request = (String) receivedObject;
    //                 processor(request);
    //             }
    //         } catch (ClassNotFoundException | IOException e) { e.printStackTrace();}          
    //     }
    // }

    private void processor(String request) throws IOException {
        switch (request) {
            case "PROGRESS":
                ArrayList<Integer> progress = new ArrayList<>();
                for(Peasant peasant : peasants) {
                    progress.add(peasant.getProgress());
                }
                // System.out.println("\nSending PROGRESS ...\n");
                net.send(progress);         //sending
                // System.out.println("\nPROGRESS sent\n");
                break;
                
            case "TASKS IN QUEUE":
                // System.out.println("\nSending TASKS IN QUEUE ...\n");
                net.send(taskQueue.getAvailableTasks());
                // System.out.println("\nTASKS IN QUEUE sent\n");
                break;
        
            default:
                break;
        }
    }
}