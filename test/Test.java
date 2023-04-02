import java.util.*;

import config.Cts;
import worker.computer.Calculator;
import worker.manager.CompletedTask;

import java.io.*;
import java.net.*;


class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Test t = new Test();

        ObjectInputStream reader = new ObjectInputStream(
            new FileInputStream("server/memory/database/task_" +
            1 + ".dat")
        );

        CompletedTask task = (CompletedTask) reader.readObject();

        t.print(task.getData().containsKey("10000000"));

    }



    private static int compare(String  a, String  b) {
        //true : a>b
        int result = 0;
        if(a.length() > b.length()) {
            result = 1;
        } else if(a.length() < b.length()){
            result = -1;
        } else {
            int n = a.length();
            for(int i = 0; i <= n - 1; i++) {
                if(a.charAt(i) - '0' > b.charAt(i) - '0' ) {
                    result = 1;
                    break;
                } else if(a.charAt(i) - '0' < b.charAt(i) - '0'){
                    result = -1;
                    break;
                }
            }
        }
        return result;
    }

    public static Boolean inRange(String a, String b, String number) {
        if( compare(number, a) == 1 && compare(b, number) == 1) {
            return true;
        }
        return false;
    }

    void print(Object obj) {
        System.out.println(obj + "\n");
    }
}
