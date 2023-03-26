import java.util.*;

import worker.manager.CompletedTask;

import java.io.*;
import java.net.*;


class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Test t = new Test();

        Hashtable hash = new Hashtable<>();
        hash.put("98739874794", 736);
        hash.put("98734", 390);

        int[] j = new int[] {3,4};
        ArrayList<Integer> i = new ArrayList<>();
        i.add(63);
        i.add(298763);

        t.print(i);

    }



    void print(Object obj) {
        System.out.println(obj + "\n");
    }

    static String incrementNumber(String number) {
        int index = number.length() - 1;
        while(index >= 0 && (number.charAt(index) == '9')) {
            number = replaceChar(number, index, '0');
            index--;
        }
        if(index >= 0) {
            number = replaceChar(number, index, (char) (number.charAt(index) + 1));
        } else {
            number = "1" + number;
        }
        return number;
    }

    static String replaceChar(String str, int index, char c) {
        return str.substring(0, index) + c + str.substring(index + 1);
    }

    public static String addIntervalToString(String number, String range) {        // "987398263" + 10000 = "987408263"
        int zerosInRange = range.length() - 1;
        String result = incrementNumber(number.substring(0, number.length()-zerosInRange));
        result += number.substring(number.length() - zerosInRange);
        return result;
    }
}
