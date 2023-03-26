import java.util.*;

import worker.manager.CompletedTask;

import java.io.*;
import java.net.*;


class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Test t = new Test();

        String fruit = "apple";
        switch (fruit) {
            case "apple":
                System.out.println("This is an apple.");
                break;
            case "banana":
                System.out.println("This is a banana.");
                break;
            case "orange":
                System.out.println("This is an orange.");
                break;
            default:
                System.out.println("This is not a valid fruit.");
                break;
        }

    }



    void print(Object obj) {
        System.out.println(obj + "\n");
    }
}
