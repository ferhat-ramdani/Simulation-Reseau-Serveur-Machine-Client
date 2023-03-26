package server.memory;

import java.io.*;
import java.util.*;

import worker.manager.CompletedTask;

public class Memory {
    
    private int id;
    private CompletedTask[] pages;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private Hashtable register;
    
    public Memory() {
        this.pages = new CompletedTask[3];
        this.register = new Hashtable<>();
        this.id = 1;
    }
    
    public void storeInDisk(CompletedTask task, int id) throws FileNotFoundException, IOException {
        writer = new ObjectOutputStream(new FileOutputStream("server/memory/database/task_" + id + ".dat"));
        writer.writeObject(task);
        writer.close();
    }

    public CompletedTask retrieve(int id) throws FileNotFoundException, IOException, ClassNotFoundException {
        reader = new ObjectInputStream(new FileInputStream("server/memory/database/task" + id + ".dat"));
        CompletedTask task = (CompletedTask) reader.readObject();
        reader.close();
        return task;
    }

    public void storeInRAM(CompletedTask task) throws FileNotFoundException, IOException {
        System.out.println("\nRecieved task : " + Arrays.toString(task.getRange()) +
        " put in task_" + id + ".dat In storeInRAM\n");
        register.put(id, task.getRange());
        storeInDisk(task, id);
        pages[0] = pages[1];
        pages[1] = task;
        id++;
    }

    public int getId() {
        return this.id;
    }
    
}
