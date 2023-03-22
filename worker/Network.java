package worker;

import java.net.*;
import java.util.*;
import java.io.*;
import config.Cts;
import worker.manager.*;

public class Network {

    private Socket s;
    private ObjectOutputStream obSender;
    private ObjectInputStream obReciever;

    public Network() {
        try{
            this.s = new Socket(Cts.HOST_NAME, Cts.PORT1);
            this.obSender = new ObjectOutputStream(s.getOutputStream());
            this.obReciever = new ObjectInputStream(s.getInputStream());
        } catch (IOException e) {e.printStackTrace();}
    }

    public void sendObject(Object ob) {
        try{
            obSender.writeObject(ob);
        } catch (IOException e) {e.printStackTrace();}
    }

    public Object recieveObject() {
        try{
            return obReciever.readObject();         //reading
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return -1;
        }
    }
}