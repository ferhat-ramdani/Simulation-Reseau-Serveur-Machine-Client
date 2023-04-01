package server;
import server.client.ClientServer;
import server.memory.Memory;
import server.worker.net.*;

class Main {
    public static void main(String[] args) throws InterruptedException{
        
        Network net = new Network();
        Memory memory = new Memory();
        WorkerServer workerServer = new WorkerServer(net, memory);
        workerServer.setName("worker-server");
        workerServer.start();

        ClientServer clientServer = new ClientServer(memory);
        clientServer.setName("clientServer");
        clientServer.start();
        
    }
}