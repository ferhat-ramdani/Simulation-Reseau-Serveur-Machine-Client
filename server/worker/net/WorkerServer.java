/*
 * This is a thread class that creates a server, than creates a completed socket,
 * after that, it checks if connection with same ID exists in ArrayList of connections, 
 * in which case it completes it, otherwise, it creates a new connection.
 * If connection exists already (corresponding connection complete), it sends a task, 
 * and executes the scheduler every 5s.
 */
package server.worker.net;

import java.io.*;
import java.net.*;
import java.util.*;


import config.Cts;
import server.memory.Memory;
import server.worker.scheduler.*;

public class WorkerServer extends Thread {
    private Network net;
    private Memory memory;
    public WorkerServer(Network net, Memory memory) {
        this.net = net;
        this.memory = memory;
    }
    public void run() {
        int id = 1;
        try{
        ServerSocket serverSocket = new ServerSocket(Cts.PORT1);
        while(true) {
                Socket socket = serverSocket.accept();
                ObjectOutputStream objectSender = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectReciever = new ObjectInputStream(socket.getInputStream());
                net.getConnections().add(new Connection(id, socket, objectSender, objectReciever));
                Data line = new Data();
                TimerTask scheduler = new Scheduler(id, net, line);
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(scheduler, 0, 200);
                Listener listener = new Listener(id, net, memory, line);
                listener.start();
                id++;
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
