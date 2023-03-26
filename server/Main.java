package server;
import server.memory.Memory;
import server.worker.net.*;

class Main {
    public static void main(String[] args) throws InterruptedException{

        Network net = new Network();
        Memory memory = new Memory();
        Server server = new Server(net, memory);
        server.setName("server");
        server.start();

    }
}