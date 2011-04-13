package concurrent.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

class Producer implements Runnable {

    private final BlockingQueue queue;

    Producer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                queue.put(produce());
            }
        } catch (InterruptedException ex) {
        }
    }

    Object produce() {
        Object obj = new Object();
        System.out.println("produced:" + obj);
        return obj;
    }
}

class MyConsumer implements Runnable {

    private final BlockingQueue queue;

    MyConsumer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) {
        }
    }

    void consume(Object x) {
        queue.remove(x);
        System.out.println("consume :" + x);
    }
}

public class Consumer {

    public static void main(String[] args) {
        BlockingQueue q = new SynchronousQueue();
        Producer p = new Producer(q);
        MyConsumer c1 = new MyConsumer(q);
        MyConsumer c2 = new MyConsumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}
