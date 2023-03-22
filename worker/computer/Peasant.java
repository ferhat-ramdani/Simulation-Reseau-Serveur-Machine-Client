package worker.computer;

import worker.manager.*;
import worker.Network;
import worker.computer.Calculator;

public class Peasant extends Thread {

    private int id;
    private String[] range;
    private int progress;
    private TaskQueue taskQueue;
    private Calculator calc;
    private CompletedTask task;
    private Network net;

    public Peasant(TaskQueue taskQueue, int id, Network net) {
        this.taskQueue = taskQueue;
        this.range = new String[2];                // [a, b, length]
        this.progress = 0;
        this.id = id;
        this.calc = new Calculator();
        this.task = new CompletedTask(range);
        this.net = net;
    }

    public void run() {
        while(taskQueue.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String[] range = taskQueue.getTask();
        String number = range[0];
        for(int i = 0; i < Integer.parseInt(range[2]); i++) {
            int p = calc.calculatePersistanceOf(number);
            task.addNumber(number, p);
            number = calc.incrementNumber(number);
            progress = (int) ((i + 1) / Integer.parseInt(range[2])) * 100;
        }
        net.sendObject(task);       //sending
    }
}