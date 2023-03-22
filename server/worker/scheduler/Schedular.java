package server.worker.scheduler;

import java.util.Queue;
import server.worker.net.Connection;

public class Schedular {
    public static Queue queue;          //a queue of [a, b, id]
    public void checkWorker() {         //create threads that ckeck on every peasant
    }
}

class CheckPeasant extends Thread{

    private Connection c;
    public CheckPeasant(Connection c) {
        this.c = c;
    }
    public void run() {
        for (int[] progress : c.getPeasantProgress()) {
            if(progress[1] > 80) {

            }
        }
    }
}

class Pusher extends Thread{
    public void run() {
        while(true) {
            while(Schedular.queue.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}