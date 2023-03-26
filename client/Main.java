package client;

public class Main {
    public static void main(String[] args) {
        Network net = new Network();
        System.out.println("\nLaunching Messenger in main\n");
        Messenger messenger = new Messenger(net);
        messenger.setName("messenger");
        messenger.start();
    }
}
