package worker.manager;
import worker.Network;
import worker.computer.*;
import worker.manager.TaskQueue;
import config.Cts;

public class PeasantManager {
    private Peasant[] peasants;
    private TaskQueue taskQueue;
    private Network net;

    public PeasantManager(TaskQueue taskQueue, Network net) {
        this.taskQueue = taskQueue;
        this.peasants = new Peasant[(int) Cts.NB_CORES/2];
        this.net = net;
    }

    public void launchPeasants() {
        for(int i = 0; i < ((int) Cts.NB_CORES/2); i++) {
            peasants[i] = new Peasant(taskQueue, i, net);
            peasants[i].start();
        }
    }
}