package worker.manager;

import java.util.Hashtable;

public class CompletedTask {
    private String[] range;
    private int length;
    private Hashtable<String, Integer> data;
    public CompletedTask(String[] range) {
        this.range = range;
        this.length = Integer.parseInt(range[2]);
        this.data = new Hashtable<>();
    }
    public void addNumber(String number, int persistance) {
        data.put(number, persistance);
    }
    public Hashtable<String, Integer> getData() {
        return data;
    }
    public String[] getRange() {
        return range;
    }
    public int getLength() {
        return length;
    }
}