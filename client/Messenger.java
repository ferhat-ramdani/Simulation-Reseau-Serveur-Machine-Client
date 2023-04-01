package client;

import java.io.IOException;
import java.util.Scanner;

public class Messenger extends Thread{
    private Network net;
    public Messenger(Network net) {
        this.net = net;
    }
    public void run() {
        // System.out.println("\nCreating scanner in Messenger\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                // System.out.println("\nEnter Command:\n"); // syntax : "persistance of ; 893737"
                net.send(scanner.nextLine().split(";"));
                // System.out.println("\nRequest sent\n");
                System.out.println((String) net.recieve());
            } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        }
    }
}
