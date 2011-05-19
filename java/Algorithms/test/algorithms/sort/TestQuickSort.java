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
public class TestQuickSort {

    Comparable array[] = new Comparable[ArrayUtil.ARRAY_SIZE];
    Comparable test[] = new Comparable[ArrayUtil.ARRAY_SIZE];
    Random random = new Random();

    @Before
    public void init() {
        for (int i = 0; i < array.length; i++) {
            Comparable element = random.nextInt(ArrayUtil.ARRAY_SIZE);
            array[i] = element;
            test[i] = element;
        }
    }

    @Test
    public void TestSort() {
        System.out.println("init:");
        ArrayUtil.print(array);
        System.out.println("sorted:");
        long start = System.nanoTime();
        QuickSort.quickSort(array);
        System.out.println("time: "+ (System.nanoTime() - start)+" ns");
        Arrays.sort(test);

        ArrayUtil.print(array);
        ArrayUtil.print(test);

        for (int i = 0; i < array.length; i++) {
            Assert.assertEquals(array[i], test[i]);
        }

    }
}
