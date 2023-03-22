/**
    Architecure : 
    class CompletedTask
    class QueueManager
    class Network
    class MemoryManager
    package worker-server
    package client-server
    class CommandsManager

 */
package server;
import server.worker.net.*;
import java.util.*;

import config.Cts;

class Main {
    public static void main(String[] args){

        Network net = new Network();
        net.launchManagers();













        
        //manage the tasks in the queue
            //create the queue
            //wait for a connection, and retrieve information about machine capacity
            //send the proper amout of tasks to worker
            //periodicly check upon the progress of a worker
        
        
        //manage tasks recieved from worker
            //listen to worker through socket
            //manage the retrieved task in memory
    }
}