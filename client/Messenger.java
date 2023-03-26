package client;

import java.io.IOException;
import java.util.Scanner;

public class Messenger extends Thread{
    private Network net;
    public Messenger(Network net) {
        this.net = net;
    }
    public void run() {
        while (true) {
            System.out.println("\nCreating scanner in Messenger\n");
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter Command:\n");
            try {
                net.send(scanner.nextLine());
                System.out.println("\nRequest sent\n");
                int n = (int) net.recieve();        //reading
                System.out.println("\n'n' recieved\n");
                for (int i = 0; i < n; i++) {
                    System.out.println( (String) net.recieve() );
                }
            } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }

        }
    }
}
