package server.worker.scheduler;

public class Data {
    private Object line;
    public Data() {
        this.line = null;
    }
    public synchronized Object getData() {
        return line;
    }
    public synchronized void setData(Object line) {
        this.line = line;
    }
}
