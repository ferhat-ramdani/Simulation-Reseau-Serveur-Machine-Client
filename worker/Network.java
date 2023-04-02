/*
 * This class creates the two completed sockets : socket and 
 * progressSocket. It also has methods that allow to send and 
 * recieve objects from and to the server
 */
package worker;

import java.net.*;
import java.util.Scanner;
import java.io.*;
import config.Cts;

public class Network {

    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reciever;

    public Network() {
        try{
            this.socket = new Socket(Cts.HOST_NAME, Cts.PORT1);
            this.sender = new ObjectOutputStream(socket.getOutputStream());
            this.reciever = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {e.printStackTrace();}
    }

    public synchronized void send(Object ob) throws IOException{
        sender.writeObject(ob);
    }

    public synchronized Object recieve() throws ClassNotFoundException, IOException {
        return reciever.readObject();
    }

    public void closeResources() {
        Runnable closable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String request = scanner.nextLine();
                    if(request.equals("end")) {
                        try {
                            sender.writeObject(true);
                            Thread.sleep(5 * 1000);
                            Main.off = true;
                            // Thread.sleep(5 * 1000);
                            sender.close();
                            reciever.close();
                            socket.close();
                        } catch (IOException | InterruptedException e) {e.printStackTrace();}
                    }
                }
            }
        };
        Thread closer = new Thread(closable);
        closer.start();
    }
}