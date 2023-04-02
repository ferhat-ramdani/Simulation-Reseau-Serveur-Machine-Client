package server.worker.scheduler;

import java.io.IOException;

import server.memory.Memory;
import server.worker.net.Network;
import worker.manager.CompletedTask;

public class Listener extends Thread {
    private Network net;
    private Memory memory;
    private int id;
    private Data line;
    

    public Listener(int id, Network net, Memory memory, Data line) {
        this.id = id;
        this.net = net;
        this.memory = memory;
        this.line = line;
    }
    public void run() {
        try {
            while(true){
                Object object = net.receive(id);
                line.setData(object);
                if(line.getData() instanceof CompletedTask) {
                    CompletedTask task = (CompletedTask) line.getData();
                    memory.storeInRAM(task);
                    line.setData(null);
                } else if(line.getData() instanceof Boolean) {
                    System.out.println("\nRecieved End from worker\n");
                    net.getConnection(id).closeConnection();
                    net.deleteConnection(id);
                    break;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}