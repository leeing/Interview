package concurrent.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author leeing
 * @date Mar 23, 2011
 */
public class MyBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue bq = new ArrayBlockingQueue(8);
        for(int i = 0;i<10;i++){
            System.out.println("put "+i+" to the bq~");
            bq.add(i);
            System.out.println("success!");
        }
    }
}
