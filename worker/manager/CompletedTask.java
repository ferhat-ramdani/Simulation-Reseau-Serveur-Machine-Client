/*
 * In this class, we have a Hashtable of type {number : perisistance}
 * we also have methods to modify and manage the Hashtable
 */
package worker.manager;

import java.io.Serializable;
import java.util.Hashtable;

public class CompletedTask implements Serializable{
    private String[] range;
    private Hashtable data;
    public CompletedTask(String[] range) {
        this.range = range;
        this.data = new Hashtable<>();
    }
    public void addNumber(String number, int persistance) {
        data.put(number, persistance);
    }
    public Hashtable getData() {
        return data;
    }
    public String[] getRange() {
        return range;
    }
}