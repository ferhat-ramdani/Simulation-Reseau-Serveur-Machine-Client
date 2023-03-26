package server.client;

import java.io.IOException;

import server.memory.Memory;

public class Processor extends Thread{
    private Client client;
    private Memory memory;
    public Processor(Client client, Memory memory) {
        this.client = client;
        this.memory = memory;
    }
    public void run() {
        try {
            // while (true) {
                System.out.println("\nReading request in Processor\n");
                String request = (String) client.objectReciever.readObject();
                System.out.println("\nRead : " + request + " in Processor\n");
                switch (request) {
                    case "t":
                        client.objectSender.writeObject(1);
                        client.objectSender.writeObject("\n" + memory.getId() + "\n");        //sending
                        break;
                
                    default:
                        client.objectSender.writeObject(0);
                        break;
                }
            // }
        } catch (ClassNotFoundException | IOException e) { e.printStackTrace(); }
    }
}
