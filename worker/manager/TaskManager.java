package worker.manager;
import java.net.*;
import worker.Network;
import config.Cts;

public class TaskManager {

    private Network net;
    private TaskQueue taskQueue;

    public TaskManager(Network net, TaskQueue taskQueue) {
        this.net = net;
        this.taskQueue = taskQueue;
    }

    public void recieveTasks() {
        net.sendObject(Cts.NB_CORES);       //sending
        TaskReciever tr = new TaskReciever(net, taskQueue);
        tr.start();
    }

    public void sendTask(CompletedTask task) {
        net.sendObject(task);       //sending
    }
}

class TaskReciever extends Thread {

    private Network net;
    private TaskQueue taskQueue;

    public TaskReciever(Network net, TaskQueue taskQueue) {
        this.net = net;
        this.taskQueue = taskQueue;
    }

    public void run() {
        while(true){
            //listen to recieve a task from server
            String[] task = (String[]) net.recieveObject();       //reading
            //add task to taskQueue
            taskQueue.addTask(task);
            synchronized(taskQueue) {
                taskQueue.notifyAll();
            }
        }
    }
}