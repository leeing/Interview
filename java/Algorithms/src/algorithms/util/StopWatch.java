package algorithms.util;

/**
 *
 * @author leeing
 * @date Mar 19, 2011
 */
public class StopWatch {
    private long startTime;
    private long endTime;

    public void stop(){
        this.endTime = System.nanoTime();
    }

    public void start(){
        this.startTime = System.nanoTime();
    }

    public long getNanoTime(){
        return endTime - startTime;
    }
}
