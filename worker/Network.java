/*
 * This class creates the two completed sockets : socket and 
 * progressSocket. It also has methods that allow to send and 
 * recieve objects from and to the server
 */
package worker;

import java.net.*;
import java.io.*;
import config.Cts;

public class Network {

    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reciever;

    public Network() {
        try{
            // System.out.println("\nCompleting the socket : " + Cts.PORT1);
            this.socket = new Socket(Cts.HOST_NAME, Cts.PORT1);
            this.sender = new ObjectOutputStream(socket.getOutputStream());
            this.reciever = new ObjectInputStream(socket.getInputStream());
            sender.writeObject(Cts.NB_CORES);       //sending
        } catch (IOException e) {e.printStackTrace();}
    }

    public synchronized void send(Object ob) throws IOException{
        sender.writeObject(ob);         //sending
    }

    public synchronized Object recieve() throws ClassNotFoundException, IOException {
        return reciever.readObject();         //reading
    }
}