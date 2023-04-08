/*
 * Creates a socket, completes it, than immediately (in constructor)
 * sends max number of tasks
 */
package worker;
import java.io.IOException;

import config.Cts;
import worker.computer.Peasant;
import worker.manager.*;

public class Main {
    public static Boolean off = false;
    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {

        Network net = new Network();

        TaskQueue taskQueue = new TaskQueue();
        Peasant[] peasants = new Peasant[Cts.NB_CORES];
        for(int i = 0; i < Cts.NB_CORES; i++) {
            
            System.out.println("\nWorker Ended\n");
            peasants[i] = new Peasant(taskQueue, i);
            peasants[i].setName("peasant_" + i);
            peasants[i].start();
        }

        TaskManager taskManager = new TaskManager(taskQueue, net, peasants);
        taskManager.listen();

        net.closeResources();
    }
}