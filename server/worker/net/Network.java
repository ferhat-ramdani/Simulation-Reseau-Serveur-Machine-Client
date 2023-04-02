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
                connection.getSender().writeObject(object);
            }
        }
    }

    public Object receive(int connectionID) throws IOException, ClassNotFoundException {
        for (Connection connection : connections) {
            if(connection.getId() == connectionID) {
                return connection.getReciever().readObject();
            }
        }
        return -1;
    }

    public synchronized ArrayList<Connection> getConnections() {
        return connections;
    }

    public Connection getConnection(int connectionID) {
        Connection c = new Connection(0, null, null, null);
        for (Connection connection : connections) {
            if(connection.getId() == connectionID) {
                c = connection;
            }
        }
        return c;
    }

    public void deleteConnection(int connectionID) {
        for (Connection connection : connections) {
            if(connection.getId() == connectionID) {
                connections.remove(connection);
                break;
            }
        }
    }
}