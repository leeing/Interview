package concurrent.barrier;

/**
 *
 * @author leeing
 * @date Apr 13, 2011
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier2 implements Runnable {

    private int times;

    private String tourName;

    private CyclicBarrier barrier = new CyclicBarrier(3);

    private ExecutorService exec = Executors.newFixedThreadPool(3);

    public TestCyclicBarrier2() {
        new TestCyclicBarrier2(barrier, "1 主线程", 0);
    }

    public TestCyclicBarrier2(CyclicBarrier barrier, String tourName, int times) {
        this.times = times;
        this.tourName = tourName;
        this.barrier = barrier;
        exec.submit(this);
    }

    public void run() {

        while (true) {

            try {
                //主线程，获取数据分发给两个子线程同时处理
                System.out.println(now() + "1 主线程获得 数据");
                //分发给第一个子线程处理数据
                exec.submit(new WriteFile(barrier, "2 子线程处理 数据 入库", 1));
                //分发给第二个子线程处理数据
                exec.submit(new WriteFile(barrier, "3 子线程处理 数据 写文件", 1));
                //等待信号，当所有子线程处理完毕解除同步。
                barrier.await();
                System.out.println("----------------一个 数据 使用 2个子线程处理完毕-----------------");
                Thread.sleep(times * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        new TestCyclicBarrier2();
    }

    static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date()) + ": ";
    }

    static class WriteFile implements Runnable {
        private int times;

        private CyclicBarrier barrier;

        private String tourName;

        public WriteFile(CyclicBarrier barrier, String tourName, int times) {
            this.times = times;
            this.tourName = tourName;
            this.barrier = barrier;
        }

        public void run() {
            try {
                Thread.sleep(times * 1000);
                System.out.println(now() + tourName);
                barrier.await();

            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
    }

}