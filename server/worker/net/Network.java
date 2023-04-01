/*
 * This class is a utilitary class that has an ArrayList of connections. 
 * It has multiple useful methods that make it easy to exchange between 
 * the server and the workers.
 */
package server.worker.net;
import java.io.*;
import java.util.*;

public class Network {
    private ArrayList<Connection> connections;
    public Network() {
        this.connections = new ArrayList<Connection>();
    }
    
    public void send(int connectionID, Object object) throws IOException, ClassNotFoundException {
        for (Connection connection : connections) {
            if(connection.getId() == connectionID) {
                // System.out.print("\nSending " + object + " to " + connectionID + " in Network\n");
                connection.getSender().writeObject(object);         //sending
            }
        }
    }

    public Object receive(int connectionID) throws IOException, ClassNotFoundException {
        for (Connection connection : connections) {
            // System.out.println("\nThere is a connection in connections of id : " +
            // connection.getId() + "\n found in Network receive method\n");
            if(connection.getId() == connectionID) {
                // System.out.print("\nReading from " + connectionID + " in Network\n");
                return connection.getReciever().readObject();         //reading
            }
        }
        return -1;
    }

    public synchronized ArrayList<Connection> getConnections() {
        return connections;
    }
}