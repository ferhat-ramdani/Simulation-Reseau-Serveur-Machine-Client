package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import config.Cts;

public class Network {
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reciever;

    public Network() {
        try{
            this.socket = new Socket(Cts.HOST_NAME, Cts.PORT2);
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
}
