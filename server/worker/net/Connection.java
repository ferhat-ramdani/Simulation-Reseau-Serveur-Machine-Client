package server.worker.net;
import java.net.*;
import java.util.*;
import java.io.*;

public class Connection {
    private int id;
    private Socket news;
    private Socket tasks;
    private ObjectOutputStream taskSender;
    private ObjectInputStream taskReciever;
    private ObjectOutputStream newsSender;
    private ObjectInputStream newsReciever;
    private final int MAX_TASKS;
    private ArrayList<int[]> peasantsProgress;
    public Connection(int id, Socket s, ObjectOutputStream obSender, ObjectInputStream obReciever, String type) {
        this.id = id;
        if(type.equals("tasks")){
            this.taskSender = obSender;
            this.taskReciever = obReciever;
            this.tasks = s;
        } else if(type.equals("news")){
            this.newsSender = obSender;
            this.newsReciever = obReciever;
            this.news = s;
        }
        this.MAX_TASKS = 0;
    }
    public int getId() {
        return id;
    }
    public void setTasks(Socket tasks){
        this.tasks = tasks;
    }
    public ArrayList<int[]> getPeasantProgress() {
        return peasantsProgress;
    }
    public ObjectOutputStream getTaskSender() {
        return taskSender;
    }
    public ObjectInputStream getTaskReciever() {
        return taskReciever;
    }
    public ObjectOutputStream getNewsSender() {
        return newsSender;
    }
    public ObjectInputStream getNewsReciever() {
        return newsReciever;
    }
}