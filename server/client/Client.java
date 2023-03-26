package server.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    int id;
    Socket socket;
    ObjectOutputStream objectSender;
    ObjectInputStream objectReciever;
    public Client(int id, Socket socket, ObjectOutputStream objectSender, ObjectInputStream objectReciever) {
        this.id = id;
        this.objectSender = objectSender;
        this.objectReciever = objectReciever;
    }
}
