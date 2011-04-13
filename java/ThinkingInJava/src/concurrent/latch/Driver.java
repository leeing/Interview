package concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 的一个有用特性是，它不要求调用 countDown 方法的线程等到计数到达零时才继续，
 * 而在所有线程都能通过之前，它只是阻止任何线程继续通过一个 await。
 * 示例用法： 下面给出了两个类，其中一组 worker 线程使用了两个倒计数锁存器：
 *   第一个类是一个启动信号，在 driver 为继续执行 worker 做好准备之前，它会阻止所有的 worker 继续执行。
 *   第二个类是一个完成信号，它允许 driver 在完成所有 worker 之前一直等待。

 * @date Apr 13, 2011
 * @author leeing
 */
class Driver { // ...

    private static int N = 10;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) // create and start threads
        {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        doSomethingElse();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse();
        doneSignal.await();           // wait for all to finish
    }

    private static void doSomethingElse() {
        System.out.println("Do something else.");
    }
}

class Worker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    void doWork() {
        System.out.println("doWork()");
    }
}
