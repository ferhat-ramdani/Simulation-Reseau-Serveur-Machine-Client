/**
 * This class is composed of two completed sockets : socket and 
 * progressSocket.
 * For every socket, it has sender and reciever object.
 * We also have an ID
 * It also has methods send tasks, recieve completed tasks, send
 * requests for progress and recieve updates on progress
 */
package server.worker.net;
import java.net.*;
import java.util.*;
import java.io.*;

public class Connection {
    private int id;
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reciever;
    private ArrayList<int[]> peasantsProgress;
    private int maxTasks;
    public Connection(int id, Socket socket, ObjectOutputStream obSender, ObjectInputStream obReciever, int maxTasks) {
        this.id = id;
        this.sender = obSender;
        this.reciever = obReciever;
        this.socket = socket;
        this.maxTasks = maxTasks;
        System.out.println("\nConnection created, id : " + id + "\n");
    }
    public synchronized int getId() {
        return id;
    }
    public synchronized void setSocket(Socket socket){
        this.socket = socket;
    }
    public ArrayList<int[]> getPeasantProgress() {
        return peasantsProgress;
    }
    public synchronized ObjectOutputStream getSender() {
        return sender;
    }
    public synchronized ObjectInputStream getReciever() {
        return reciever;
    }
}