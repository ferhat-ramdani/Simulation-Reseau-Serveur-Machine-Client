package client;

import java.io.IOException;
import java.util.Scanner;

import config.G;

public class Messenger extends Thread{
    private Network net;
    public Messenger(Network net) {
        this.net = net;
    }
    public void run() {
        G.clearScreen();
        Scanner scanner = new Scanner(System.in);
        G.print("\nAvailable commands :\n");
        G.print("'done tasks'\n'persistance;[n]'" + 
        "\n'occ of;[p]'\n'average'\n'median'\n");
        while (true) {
            try {
                String request = scanner.nextLine();
                net.send(request);
                G.clearScreen();
                G.print("\nAvailable commands :\n");
                G.print("'done tasks'\n'persistance;[n]'" + 
                "\n'occ of;[p]'\n'average'\n'median'\n");
                G.print("\nRequest : " + request + "\n");
                G.print("\nLoading response ... please wait\n");
                String response = (String) net.recieve();
                G.clearScreen();
                G.print("\nAvailable commands :\n");
                G.print("'done tasks'\n'persistance;[n]'" + 
                "\n'occ of;[p]'\n'average'\n'median'\n");
                G.print("\nRequest : " + request + "\n");
                G.print("\nResponse : " + response + "\n");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace(); 
            }
        }
    }
}
