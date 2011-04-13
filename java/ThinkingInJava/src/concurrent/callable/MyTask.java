package concurrent.callable;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author leeing
 * @date Mar 23, 2011
 */
public class MyTask implements Callable<Integer>{

    private int value;

    public MyTask(int value){
        this.value = value;
    }
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(10-value);
        return value*value;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<Integer>> results = new ArrayList<Future<Integer>>();

        for(int i = 0;i<10;i++){
            results.add(exec.submit(new MyTask(i)));
        }
        System.out.println("submitted.");

        for(int i = 0;i<10;i++){
            try {
                System.out.println(results.get(i).get(5, TimeUnit.SECONDS));
            } catch (TimeoutException ex) {
                System.out.println(i+" task is timeout!");
            }
        }
        System.out.println("try again:");
        System.out.println(results.get(0).get());
        exec.shutdown();

    }
}
