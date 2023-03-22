package worker;
import worker.computer.*;
import worker.Network;
import config.Cts;
import worker.manager.*;

class Main {
    public static void main(String[] args) {
        
        //connect to server
        Network net = new Network();
        //create queue
        TaskQueue taskQueue = new TaskQueue();
        //launch task manager
        TaskManager tm = new TaskManager(net, taskQueue);
        tm.recieveTasks();
        //launch peasants
        PeasantManager pm = new PeasantManager(taskQueue, net);
        pm.launchPeasants();

    }

}