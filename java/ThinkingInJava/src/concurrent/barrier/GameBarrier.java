package concurrent.barrier;

/**
 * 有四个游戏玩家玩游戏，游戏有三个关卡，每个关卡必须要所有玩家都到达后才能允许通关。
 * 其实这个场景里的玩家中如果有玩家A先到了关卡1，他必须等待其他所有玩家都到达关卡1时才能通过，
 * 也就是说线程之间需要互相等待，这和CountDownLatch的应用场景有区别，CountDownLatch里的线程
 * 是到了运行的目标后继续干自己的其他事情，而这里的线程需要等待其他线程后才能继续完成下面的工作。
 * @author leeing
 * @date Apr 13, 2011
 */
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//GameBarrier类（关卡类，这里控制玩家必须全部到达第一关结束的关口才能进入第二关）
public class GameBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {

            private  int grade = 0;

            @Override
            public void run() {
                System.out.println("\n所有玩家进入下一关！");
            }
        });

        for (int i = 0; i < 4; i++) {
            new Thread(new Player(i, cyclicBarrier, 5)).start();
        }
    }
}

class Player implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private int id;
    private int grade;

    public Player(int id, CyclicBarrier cyclicBarrier, int grade) {
        this.cyclicBarrier = cyclicBarrier;
        this.id = id;
        this.grade = grade;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < grade; i++) {
                System.out.println("玩家" + id + "正在玩第 " + (i+1) + " 关...");
                cyclicBarrier.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
