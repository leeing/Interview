package algorithms.interview;

public class RunThread {

//    private String name;
//    private int num;
//
//    public RunThread() {
//        num = 10;
//    }
//
//    public RunThread(String name) {
//        this.name = name;
//        num = 10;
//    }
//
//    public void run() {
//        while (true && num > 0) {
//            System.out.println(Thread.currentThread().getName() + num++ + "������");
//        }
//        /*while(num-->0)
//        {
//        System.out.println(Thread.currentThread().getName()+":"+num);
//        }*/
//    }
    public static void main(String[] args) {
        Info in = new Info("", "");
        Product p = new Product(in);
        Consumer c = new Consumer(in);
        new Thread(p).start();
        new Thread(c).start();

    }
}

class Info {

    private String name;
    private String num;
    private boolean flag = true;

    public Info(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public void get() {
//        if (flag) {
//            try {
//                super.wait();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }

//		System.out.println("get����ʼ����");
        System.out.println(name + "---->" + num);
//		System.out.println("get����������");

//        flag = true;
//        super.notify();
    }

    public void set(String name, String num) {
//        if (!flag) {
//            try {
//                super.wait();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//		System.out.println("set����ʼ����");

        this.name = name;
        this.num = num;

//		System.out.println("set����������");

//        flag = false;
//        super.notify();
    }
}

class Product implements Runnable {

    private Info in;
    private static final Object lock = new Object();

    public Product(Info in) {
        this.in = in;
    }

    public void run() {
        boolean b = false;
        while (true) {


            if (b) {
                synchronized (in) {
                    in.set("hange", "hangenum");

                }
                b = false;
            } else {
                synchronized (in) {
                    in.set("sj", "sjnum");
                }
                b = true;

            }

            /*try {
            super.wait();
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }*/
        }
    }
}

class Consumer implements Runnable {

    private Info in;

    public Consumer(Info in) {
        this.in = in;
    }

    public void run() {
        while (true) {
            synchronized (in) {
                in.get();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
