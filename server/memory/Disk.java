package server.memory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import worker.manager.CompletedTask;


public class Disk {
    private int i = 0;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public void store(CompletedTask task) throws FileNotFoundException, IOException {
        writer = new ObjectOutputStream(new FileOutputStream("task" + i + ".dat"));
        i++;
        writer.close();
    }

    public CompletedTask retrieve(int id) throws FileNotFoundException, IOException, ClassNotFoundException {
        reader = new ObjectInputStream(new FileInputStream("disk" + id + ".dat"));
        CompletedTask task = (CompletedTask) reader.readObject();
        reader.close();
        return task;
    }

}
