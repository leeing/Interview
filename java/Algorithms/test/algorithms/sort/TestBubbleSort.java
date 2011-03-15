package algorithms.sort;

import algorithms.util.ArrayUtil;
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

    Comparable array[] = new Comparable[10];
    Comparable test[] = new Comparable[10];
    Random random = new Random(47);

    @Before
    public void init(){
        for(int i = 0;i< array.length;i++){
            Comparable element = random.nextInt(100);
            array[i] = element;
            test[i] = element;
        }
    }
    @Test
    public void TestSort(){
        BubbleSort.bubbleStor(array);
        Arrays.sort(test);
        for(int i = 0;i<array.length;i++){
           Assert.assertEquals(array[i], test[i]);
        }
        ArrayUtil.print(array);
        ArrayUtil.print(test);
    }
}
