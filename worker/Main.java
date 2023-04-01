/*
 * Creates a socket, completes it, than immediately (in constructor)
 * sends max number of tasks
 */
package worker;
import java.io.IOException;

import config.Cts;
import worker.computer.Peasant;
import worker.manager.*;

class Main {
    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {

        Network net = new Network();

        TaskQueue taskQueue = new TaskQueue();
        Peasant[] peasants = new Peasant[Cts.NB_CORES];
        for(int i = 0; i < Cts.NB_CORES; i++) {
            peasants[i] = new Peasant(taskQueue, i, net);
            peasants[i].setName("peasant_" + i);
            peasants[i].start();
        }

        TaskManager taskManager = new TaskManager(taskQueue, net, peasants);
        taskManager.listen();
    }
}