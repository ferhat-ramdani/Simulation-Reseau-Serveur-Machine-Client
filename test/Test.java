import java.util.*;
import java.io.*;
import java.net.*;


class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Test t = new Test();

        Hashtable<String, Integer> data = new Hashtable<>();
        data.put("322", 48);
        data.put("100", 1);
        data.put("8937", 2);

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("disk.dat"));
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("disk.dat"));

        writer.writeObject(data);

        Hashtable<String, Integer> data2 = (Hashtable<String, Integer>) reader.readObject();

        t.print(data2);

        writer.close();
    
    }



    void print(Object obj) {
        System.out.println(obj + "\n");
    }
}