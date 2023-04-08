package server.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import config.Cts;
import config.G;
import server.memory.Memory;
import worker.computer.Calculator;
import worker.manager.CompletedTask;

public class Processor extends Thread{
    private Client client;
    private Memory memory;
    public Processor(Client client, Memory memory) {
        this.client = client;
        this.memory = memory;
    }
    public void run() {
        try {
            while (true) {
                String[] request = ((String) client.objectReciever.readObject()).split(";");
                switch (request[0]) {
                    case "done tasks":
                        client.objectSender.writeObject("Done tasks : " + 
                        Integer.toString(memory.getDoneTasks()));
                        break;
                    case "persistance":
                        Boolean found = false;
                        String number = request[1];
                        for(CompletedTask task : memory.getPages()) {
                            if(task != null && task.getData().containsKey(request[1])) {
                                G.print("\nIt contains key and not null\n");
                                G.print("request : " + request[1]);
                                String line = "Persistance of " + number + " : ";
                                line += task.getData().get(number).toString() + "\n";
                                client.objectSender.writeObject(line);
                                found = true;
                            }
                        }
                        if(!found) {
                            Hashtable<Integer, String[]> register = memory.getRegister();
                            for (Map.Entry<Integer, String[]> entry : register.entrySet()) {
                                String[] value = entry.getValue();
                                if(Calculator.inRange(value[0], value[1], number)) {
                                    CompletedTask task = memory.retrieve(entry.getKey());
                                    String line = "Persistance of " + number + " : ";
                                    line += (String) task.getData().get(number).toString();
                                    memory.getPages()[Cts.PAGES - 1] = task;
                                    client.objectSender.writeObject(line);
                                    found = true;
                                    break;
                                }
                            }
                            if(!found) {
                                client.objectSender.writeObject("Persistance of " + number + " not found");
                            }
                        }
                        break;
                    
                    case "occ of":
                        client.objectSender.writeObject(Integer.toString(occOf(Integer.parseInt(request[1]))));
                        break;

                    case "average":
                        int[] occurences = findOccurences();
                        int all = 0;
                        double average = 0;
                        for (int p = 1; p < 15; p++) {
                            all += occurences[p];
                            average += p * occurences[p];
                        }
                        average = average/all;
                        client.objectSender.writeObject("Average : " + Double.toString(average));
                        break;

                    case "median":
                        int[] occs = findOccurences();
                        int middle = (int) Math.floor(Arrays.stream(occs).sum()/2);
                        int accumulate = 0;
                        int median = 0;
                        for(int i = 1; i < 15; i++) {
                            accumulate += occs[i];
                            if(middle <= accumulate) {
                                median = i;
                                break;
                            }
                        }
                        client.objectSender.writeObject("Median : " + 
                        Integer.toString(median));
                        break;


                    default:
                        client.objectSender.writeObject("Bad Command");
                        break;
                }
                memory.showPages();
            }
        } catch (ClassNotFoundException | IOException e) { e.printStackTrace(); }
    }

    private int occOf(int persistance) throws FileNotFoundException, 
    ClassNotFoundException, IOException {
        int occ = 0;
        for (CompletedTask task : memory.getPages()) {
            if(task != null) {
                for (int p : task.getData().values()) {
                    if(p == persistance) occ++;
                }
            }
        }
        G.print("\nOcc in RAM : " + occ);
        Hashtable<Integer, String[]> register = memory.getRegister();
        for (Map.Entry<Integer, String[]> entry : register.entrySet()) {
            G.print("Checking in task_" + entry.getKey() + 
            ".dat ...");
            CompletedTask task = memory.retrieve(entry.getKey());
            for (int p : task.getData().values()) {
                if(p == persistance) occ++;
            }
        }
        return occ;
    }

    private int[] findOccurences() throws FileNotFoundException, 
    ClassNotFoundException, IOException {
        int[] occurences = new int[15];
        Arrays.fill(occurences, 0);
        for (CompletedTask task : memory.getPages()) {
            if(task != null) {
                for (int p : task.getData().values()) {
                    occurences[p] = occurences[p] + 1;
                }
            }
        }
        G.print("\nOcc in RAM : " + Arrays.toString(occurences));
        Hashtable<Integer, String[]> register = memory.getRegister();
        for (Map.Entry<Integer, String[]> entry : register.entrySet()) {
            G.print("Checking in task_" + entry.getKey() + 
            ".dat ...");
            CompletedTask task = memory.retrieve(entry.getKey());
            for (int p : task.getData().values()) {
                occurences[p] = occurences[p] + 1;
            }
        }
        G.print("\nOccurences : "  + Arrays.toString(occurences) + "\n");
        return occurences;
    }
}
