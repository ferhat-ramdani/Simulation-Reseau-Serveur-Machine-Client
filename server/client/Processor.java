package server.client;

import java.io.IOException;
import java.util.*;

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
                String[] request = (String[]) client.objectReciever.readObject();
                // System.out.println("\nRead : " + Arrays.toString(request) + " in Processor\n");
                switch (request[0]) {
                    case "registered tasks":
                        client.objectSender.writeObject("\n" + (memory.getId() + 2) + "\n");        //sending
                        break;
                    case "per":
                        Boolean found = false;
                        String number = request[1];
                        // System.out.println("\nRecieved get persistance of " + number + "\n");
                            for(CompletedTask task : memory.getPages()) {
                                if(task != null && task.getData().containsKey(request[1])) {
                                    String line = "\nPersistance of " + number + " : ";
                                    line += task.getData().get(number).toString() + "\n";
                                    client.objectSender.writeObject(line);
                                    found = true;
                                    // System.out.println("\nFound and sent in pages\n");
                                }
                            }
                            if(!found) {
                                Hashtable<Integer, String[]> register = memory.getRegister();
                                for (Map.Entry<Integer, String[]> entry : register.entrySet()) {
                                    String[] value = entry.getValue();
                                    if(Calculator.inRange(value[0], value[1], number)) {
                                        CompletedTask task = memory.retrieve(entry.getKey());
                                        String line = "\nPersistance of " + number + " : ";
                                        line += (String) task.getData().get(number).toString() + "\n";
                                        memory.getPages()[2] = task;
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
                    
                    case "n of occ":
                        int persistance = Integer.parseInt(request[1]);
                        int occ = 0;
                        for (CompletedTask task : memory.getPages()) {
                            if(task != null) {
                                for (int p : task.getData().values()) {
                                    if(p == persistance) occ++;
                                }
                            }
                        }
                        // System.out.println("\nocc in pages : " + occ + "\n");
                        Hashtable<Integer, String[]> register = memory.getRegister();
                        for (Map.Entry<Integer, String[]> entry : register.entrySet()) {
                            // System.out.println("\nGetting task " + entry.getKey() + 
                            // " ...\n");
                            CompletedTask task = memory.retrieve(entry.getKey());
                            // System.out.println("Got task " + entry.getKey() + "");
                            for (int p : task.getData().values()) {
                                if(p == persistance) occ++;
                            }
                        }
                        client.objectSender.writeObject("\n" + occ + "\n");
                        break;

                    default:
                        client.objectSender.writeObject("\nBad Command\n");
                        break;
                }
            }
        } catch (ClassNotFoundException | IOException e) { e.printStackTrace(); }
    }
}
