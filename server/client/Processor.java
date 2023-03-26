package server.client;

import java.io.IOException;

import server.memory.Memory;

public class Processor extends Thread{
    private Client client;
    private Memory memory;
    public Processor(Client client) {
        this.client = client;
    }
    public void run() {
        try {
            // while (true) {
                String request = (String) client.objectReciever.readObject();
                switch (request) {
                    case "Get number of files in database":
                        client.objectSender.writeObject(memory.getId());
                        break;
                
                    default:
                        break;
                }
            // }
        } catch (ClassNotFoundException | IOException e) { e.printStackTrace(); }
    }
}
