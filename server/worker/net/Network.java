package server.worker.net;
import java.net.*;
import java.io.*;
import java.util.*;
import config.Cts;
import server.worker.net.Connection;

public class Network {
    public static ArrayList<Connection> connections;
    private ServerSocket taskSS;
    private ServerSocket newsSS;
    private TaskListener tl;
    private NewsListener nl;
    public Network() {
        System.out.println("\njust got into constr..\n");
        Network.connections = new ArrayList<Connection>();
        try{
            this.taskSS = new ServerSocket(Cts.PORT1);
            System.out.println("\njust created taskSS\n");
            this.newsSS = new ServerSocket(Cts.PORT2);
            System.out.println("\ncreated newsSS\n");
        } catch (IOException e) {e.printStackTrace();}
    }
    public void launchManagers() {
        tl = new TaskListener(taskSS);
        nl = new NewsListener(newsSS);
        System.out.println("\nno biggy, just got into launchManagers \n");
        tl.start();
        nl.start();
    }
    public void sendOb(int connectionID, Object ob, String type) throws IOException {
        for (Connection connection : Network.connections) {
            if(connection.getId() == connectionID) {
                if(type == "task") {
                    connection.getTaskSender().writeObject(ob);         //sending
                } else if(type == "news") {
                    connection.getNewsSender().writeObject(ob);         //sending
                }
            }
        }
    }
}

class TaskListener extends Thread {
    private ServerSocket taskSS;
    private ObjectOutputStream obSender;
    private ObjectInputStream obReciever;
    public TaskListener(ServerSocket taskSS) {
        this.taskSS = taskSS;
    }
    public void run() {
        while(true) {
            try{
                System.out.println("\ntrying to accept from tl \n");
                Socket taskS = taskSS.accept();
                System.out.println("\naccepted in tl, ... strange ?? \n");
                this.obSender = new ObjectOutputStream(taskS.getOutputStream());
                this.obReciever = new ObjectInputStream(taskS.getInputStream());
                int id = (int) obReciever.readObject();
                Boolean exists = false;
                for(Connection c : Network.connections) {
                    if(c.getId() == id) {
                        c.setTasks(taskS);
                        exists = true;
                    }
                }
                if(!exists) {
                    Network.connections.add(new Connection(id, taskS, obSender, obReciever, "tasks"));
                }
            } catch (IOException e) {e.printStackTrace();
            } catch (ClassNotFoundException e) { e.printStackTrace(); }
        }
    }
}

class NewsListener extends Thread {
    private ServerSocket newsSS;
    private ObjectOutputStream obSender;
    private ObjectInputStream obReciever;
    public NewsListener(ServerSocket newsSS) {
        this.newsSS = newsSS;
    }
    public void run() {
        while(true) {
            try{
                System.out.println("\ntrying to accept in nl\n");
                Socket newsS = newsSS.accept();
                System.out.println("\naccepted ! strange right ?? \n");
                this.obSender = new ObjectOutputStream(newsS.getOutputStream());
                this.obReciever = new ObjectInputStream(newsS.getInputStream());
                int id = (int) obReciever.readObject();
                Boolean exists = false;
                for(Connection c : Network.connections) {
                    if(c.getId() == id) {
                        c.setTasks(newsS);
                        exists = true;
                    }
                }
                if(!exists) {
                    Network.connections.add(new Connection(id, newsS, obSender, obReciever, "news"));
                }
            } catch (IOException e) {e.printStackTrace();
            } catch (ClassNotFoundException e) { e.printStackTrace(); }
        }
    }
}