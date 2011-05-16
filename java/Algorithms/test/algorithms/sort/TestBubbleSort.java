package algorithms.sort;

import algorithms.util.ArrayUtil;
import algorithms.util.StopWatch;
import java.util.Arrays;
import java.util.Random;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * @date Mar 15, 2011
 * @author leeing
 */
public class TestBubbleSort {

    Comparable array[] = new Comparable[ArrayUtil.ARRAY_SIZE];
    Comparable test[] = new Comparable[ArrayUtil.ARRAY_SIZE];
    Random random = new Random(47);

    @Before
    public void init(){
        for(int i = 0;i< array.length;i++){
            Comparable element = random.nextInt(ArrayUtil.ARRAY_SIZE);
            array[i] = element;
            test[i] = element;
        }
    }
    @Test
    public void TestSort(){
       
        StopWatch timmer = new StopWatch();
        timmer.start();
        BubbleSort.bubbleSort2(array);
        timmer.stop();
        System.out.println("bubble: "+timmer.getNanoTime());

        timmer.start();
        Arrays.sort(test);
        timmer.stop();
        System.out.println("Arrays.sort:"+timmer.getNanoTime());
        for(int i = 0;i<array.length;i++){
           Assert.assertEquals(array[i], test[i]);
        }
        ArrayUtil.print(array);
        ArrayUtil.print(test);
    }
}
