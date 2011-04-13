package concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author leeing
 * @date Mar 23, 2011
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        final int count = 10;

        final CountDownLatch latch = new CountDownLatch(count);

        for(int i = 0;i<count;i++){
            Thread thread = new Thread("worker thread "+i){
                @Override
                public void run(){
                    System.out.println(this.getName());
                    latch.countDown();
                }
            };
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException ex) {

        }
    }
}
