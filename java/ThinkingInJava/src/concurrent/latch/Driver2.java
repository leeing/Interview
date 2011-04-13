package concurrent.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 另一种典型用法是，将一个问题分成 N 个部分，用执行每个部分并让锁存器倒计数的 Runnable
 * 来描述每个部分，然后将所有 Runnable 加入到 Executor 队列。当所有的子部分完成后，协调
 * 线程就能够通过 await。（当线程必须用这种方法反复倒计数时，可改为使用 CyclicBarrier。） 
 * @date Apr 13, 2011
 * @author leeing
 */
class Driver2 { // ...

    private static final int N = 10;

    public static void main(String args[]) throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(N);
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < N; ++i){
            exec.execute(new WorkerRunnable(doneSignal, i));
        }

        doneSignal.await();           // wait for all to finish
        exec.shutdown();
    }
}

class WorkerRunnable implements Runnable {

    private final CountDownLatch doneSignal;
    private final int i;

    WorkerRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }

    public void run() {
        try {
            doWork(i);
            doneSignal.countDown();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } // return;
    }

    private void doWork(int i) throws InterruptedException {
        System.out.println("Doing work " + i);
    }
}
