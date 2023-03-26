/*
 * This is a thread class creates a server, than creates a completed socket,
 * after that, it reads an ID from corresponding worker, it checks if connection 
 * with same ID exists in ArrayList of connections, in which case it completes it,
 * otherwise, it creates a new connection.
 * If connection exists already (corresponding connection complete), it sends a task, 
 * and executes the scheduler every 10s.
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
                // System.out.println("\nTrying to accept from Server\n");
                Socket socket = serverSocket.accept();
                // System.out.println("\nJust accepted new connection in Server\n");
                ObjectOutputStream objectSender = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectReciever = new ObjectInputStream(socket.getInputStream());
                // System.out.println("\nJust created sender and reciever in Server\n");
                // System.out.println("\nReading maxTasks\n");
                int maxTasks = (int) objectReciever.readObject();       //reading
                // System.out.println("\nJust read maxTasks : " + maxTasks + " in Server\n");
                net.getConnections().add(new Connection(id, socket, objectSender, objectReciever, maxTasks));
                // System.out.println("\nLaunching scheduler\n");
                Line line = new Line();
                TimerTask scheduler = new Scheduler(id, net, line);
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(scheduler, 0, 15*1000);
                Listener listener = new Listener(id, net, memory, line);
                listener.start();
                id++;
            }
        } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
    }
}
