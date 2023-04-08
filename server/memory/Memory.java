package server.memory;

import java.io.*;
import java.util.*;

import config.Cts;
import config.G;
import worker.manager.CompletedTask;

public class Memory {
    
    private int id;
    private int doneTasks;
    private CompletedTask[] pages;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private Hashtable<Integer, String[]> register;
    
    public Memory() {
        this.pages = new CompletedTask[Cts.PAGES];
        this.register = new Hashtable<>();
        this.id = 1;
        this.doneTasks = 1;
    }
    
    public synchronized void storeInDisk(CompletedTask task) throws FileNotFoundException, IOException {
        writer = new ObjectOutputStream(new FileOutputStream("server/memory/database/task_" + id + ".dat"));
        writer.writeObject(task);
        writer.close();
        register.put(id, task.getRange());
        id++;
    }

    public synchronized CompletedTask retrieve(int id) throws FileNotFoundException, IOException, ClassNotFoundException {
        reader = new ObjectInputStream(new FileInputStream("server/memory/database/task_" + id + ".dat"));
        CompletedTask task = (CompletedTask) reader.readObject();
        reader.close();
        return task;
    }

    public synchronized void storeInRAM(CompletedTask task) throws FileNotFoundException, IOException {
        if(pages[Cts.PAGES - 2] != null) storeInDisk(pages[Cts.PAGES - 2]);

        for (int i = Cts.PAGES - 3; i >= 0; i--) {
            pages[i+1] = pages[i];
        }
        pages[0] = task;
        showPages();
        doneTasks++;
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
        System.out.println("\n____________Pages__________\n");
        for(int i = 1; i <= Cts.PAGES; i++){
            if(pages[i-1] != null){
                System.out.println("Page " + i + " : " + Arrays.toString(pages[i-1].getRange()) + "\n");
            } else {
                System.out.println("Page " + i + " : null\n");
            }
        }
    }
    private void printFiles() {
        System.out.println("\n____________Files___________\n");
        int i = 0;
        for (Map.Entry<Integer, String[]> entry : register.entrySet()) {
            if(i<10) {
                System.out.println(entry.getKey() + " => " + Arrays.toString(entry.getValue()));
                i++;
            }
        }
    }

    public void showPages() {
        G.clearScreen();
        printPages();
        printFiles();
    }

    public int getDoneTasks() {
        return doneTasks;
    }

}
