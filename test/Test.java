import java.util.*;

import worker.manager.CompletedTask;

import java.io.*;
import java.net.*;


class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Test t = new Test();

        try {
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
            System.out.println("Hostname: " + hostname);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    void print(Object obj) {
        System.out.println(obj + "\n");
    }
}
