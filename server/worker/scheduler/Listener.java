package server.worker.scheduler;

import java.io.IOException;
import java.util.Arrays;

import server.memory.Memory;
import server.worker.net.Network;
import worker.manager.CompletedTask;

public class Listener extends Thread {
    private Network net;
    private Memory memory;
    private int id;
    private Line line;
    

    public Listener(int id, Network net, Memory memory, Line line) {
        this.id = id;
        this.net = net;
        this.memory = memory;
        this.line = line;
    }
    public void run() {
        try {
            while(true){
                Object object = net.receive(id);
                // System.out.println("\nRead instance of " +
                // object.getClass()+ " in Listener\n");
                line.setLine(object);            //reading
                if(line.getLine() instanceof CompletedTask) {
                    CompletedTask task = (CompletedTask) line.getLine();
                    // System.out.println("\nRead Completed Task " + 
                    // Arrays.toString(task.getRange()) + " in Listener " + 
                    // "successfully\n");
                    memory.storeInRAM(task);
                    line.setLine(null);
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}