package server.client;

import java.io.*;
import java.net.*;
import java.util.*;

import config.Cts;

public class Server extends Thread{
    
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Cts.PORT2);
            ArrayList clients = new ArrayList<>();
            int id = 1;
            // while(true){
                Socket socket = serverSocket.accept();
                ObjectOutputStream objectSender = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectReciever = new ObjectInputStream(socket.getInputStream());
                clients.add(new Client(id, socket, objectSender, objectReciever));
            // }

        } catch (Exception e) {e.printStackTrace();}
    }
    
}
