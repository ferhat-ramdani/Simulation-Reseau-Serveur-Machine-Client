package worker.manager;

import java.util.*;

public class TaskQueue {
    private Queue<String[]> taskQueue;

    public TaskQueue() {
        this.taskQueue = new LinkedList<>();
    }

    public void addTask(String[] task) {
        taskQueue.add(task);
    }

    public synchronized String[] getTask() {
        return taskQueue.poll();
    }

    public Boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}