package server.worker.scheduler;

public class Line {
    private Object line;
    public Line() {
        this.line = null;
    }
    public synchronized Object getLine() {
        return line;
    }
    public synchronized void setLine(Object line) {
        this.line = line;
        if(line != null) {
            // System.out.println("\nline became instance of " +
            // line.getClass() + " in Line\n");
        }
    }
}
