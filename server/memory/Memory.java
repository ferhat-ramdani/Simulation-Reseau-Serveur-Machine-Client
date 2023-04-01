package server.memory;

import java.io.*;
import java.util.*;

import worker.manager.CompletedTask;

public class Memory {
    
    private int id;
    private CompletedTask[] pages;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private Hashtable<Integer, String[]> register;
    
    public Memory() {
        this.pages = new CompletedTask[3];
        this.register = new Hashtable<>();
        this.id = 1;
    }
    
    public synchronized void storeInDisk(CompletedTask task) throws FileNotFoundException, IOException {
        writer = new ObjectOutputStream(new FileOutputStream("server/memory/database/task_" + id + ".dat"));
        writer.writeObject(task);
        writer.close();
        register.put(id, task.getRange());
        // System.out.println("\nCreated task_" + id + ".dat with range " +
        // Arrays.toString(task.getRange()) + "\n");
        id++;
    }

    public synchronized CompletedTask retrieve(int id) throws FileNotFoundException, IOException, ClassNotFoundException {
        reader = new ObjectInputStream(new FileInputStream("server/memory/database/task_" + id + ".dat"));
        CompletedTask task = (CompletedTask) reader.readObject();
        reader.close();
        return task;
    }

    public synchronized void storeInRAM(CompletedTask task) throws FileNotFoundException, IOException {
        // System.out.println("\nRecieved task : " + Arrays.toString(task.getRange()) +
        // "\n trying to put in task_" + id + ".dat In storeInRAM\n");
        if(pages[1] != null) storeInDisk(pages[1]);
        pages[1] = pages[0];
        pages[0] = task;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        printPages();
        printFiles();
    }

    public int getId() {
        return this.id;
    }

    public synchronized CompletedTask[] getPages() {
        return pages;
    }
    
    public synchronized Hashtable<Integer, String[]> getRegister() {
        return register;
    }

    private void printPages() {
        System.out.println("\n________Pages________\n");
        for(int i = 1; i <= 3; i++){
            if(pages[i-1] != null){
                System.out.println("Page " + i + " : " + Arrays.toString(pages[i-1].getRange()) + "\n");
            } else {
                System.out.println("Page " + i + " : null\n");
            }
        }
    }
    private void printFiles() {
        System.out.println("\n________Files________\n");
        for (Map.Entry<Integer, String[]> entry : register.entrySet()) {
            System.out.println(entry.getKey() + " => " + Arrays.toString(entry.getValue()));
        }
    }

}
