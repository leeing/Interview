package concurrent.delayqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue 是一个无界的BlockingQueue，用于放置实现了Delayed接口的对象，其中的对象只能
 * 在其到期时才能从队列中取走。这种队列是有序的，即队头对象的延迟到期时间最长。注意：不能将
 * null元素放置到这种队列中。
 * Delayed
 * 一种混合风格的接口，用来标记那些应该在给定延迟时间之后执行的对象。
 * 此接口的实现必须定义一个 compareTo 方法，该方法提供与此接口的 getDelay 方法一致的排序。
 *
 * 下面的代码模拟一个考试的日子，考试时间为120分钟，30分钟后才可交卷，当时间到了，或学生都
 * 交完卷了者考试结束。线程的关闭参考Java编程思想中例子，将exec传给Student的一个内部类，
 * 通过他来关闭。
 *
 * 模拟考试，时间为120分钟，学生可以再30分钟后交卷，
 * 当学生都交完了 或 时间到者考试结束
 * @author 小e
 *
 * 2010-4-30 下午11:14:25
 * @date Apr 13, 2011
 */
class Student implements Runnable, Delayed {

    private String name;
    private long submitTime;//交卷时间
    private long workTime;//考试时间

    public Student() {
        // TODO Auto-generated constructor stub
    }

    public Student(String name, long submitTime) {
        super();
        this.name = name;
        workTime = submitTime;
        //都转为转为ns
        this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public void run() {
        System.out.println(name + " 交卷,用时" + workTime / 100 + "分钟");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(submitTime - System.nanoTime(), unit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Student that = (Student) o;
        return submitTime > that.submitTime ? 1 : (submitTime < that.submitTime ? -1 : 0);
    }

    public static class EndExam extends Student {

        private ExecutorService exec;

        public EndExam(int submitTime, ExecutorService exec) {
            super(null, submitTime);
            this.exec = exec;
        }

        @Override
        public void run() {
            exec.shutdownNow();
        }
    }
}

class Teacher implements Runnable {

    private DelayQueue<Student> students;
    private ExecutorService exec;

    public Teacher(DelayQueue<Student> students, ExecutorService exec) {
        super();
        this.students = students;
        this.exec = exec;
    }

    @Override
    public void run() {
        try {
            System.out.println("考试开始……");
            while (!Thread.interrupted()) {
                students.take().run();
            }
            System.out.println("考试结束……");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class Exam {

    static final int STUDENT_SIZE = 45;

    public static void main(String[] args) {
        Random r = new Random();
        DelayQueue<Student> students = new DelayQueue<Student>();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < STUDENT_SIZE; i++) {
            students.put(new Student("学生" + (i + 1), 3000 + r.nextInt(9000)));
        }
        students.put(new Student.EndExam(12000, exec));//1200为考试结束时间
        exec.execute(new Teacher(students, exec));

    }
}
