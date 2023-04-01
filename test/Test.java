import java.util.*;

import config.Cts;
import worker.computer.Calculator;
import worker.manager.CompletedTask;

import java.io.*;
import java.net.*;


class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Test t = new Test();

        String[] range = new String[] {"1000000000000000000", "2000000000000000000"};
        String number = range[0];
        CompletedTask task = new CompletedTask(range);
        for(int i = 0; i < Integer.parseInt(Cts.INTERVAL_SIZE); i++) {
            int p = Calculator.calculatePersistanceOf(number);
            task.addNumber(number, p);
            number = Calculator.incrementNumber(number);
            int progress = (int) ((i + 1) / Double.parseDouble(Cts.INTERVAL_SIZE) * 100);
            t.print(progress);
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
        }

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
