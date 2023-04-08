/*
 * This class creates a server, expects connections, 
 * than launches a processor to process the requests once
 * connection established
 */
package server.client;

import java.io.*;
import java.net.*;
import java.util.*;

import config.Cts;
import server.memory.Memory;

public class ClientServer extends Thread{
    private ArrayList clients;
    private Memory memory;
    public ClientServer(Memory memory) {
        this.clients = new ArrayList<>();
        this.memory = memory;
    }
    
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Cts.PORT2);
            int id = 1;
            while(true){
                Socket socket = serverSocket.accept();
                ObjectOutputStream objectSender = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectReciever = new ObjectInputStream(socket.getInputStream());
                Client client = new Client(id, socket, objectSender, objectReciever);
                clients.add(client);
                Processor processor = new Processor(client, memory);
                processor.setName("processor");
                processor.start();
                id++;
            }

        } catch (Exception e) {e.printStackTrace();}
    }
    
}
