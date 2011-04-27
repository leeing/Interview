package algorithms.interview;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * 两个1000W个元素的数组，如何有效的找出他们的交集。
 * 
 * @author leeing
 * Created at Apr 24, 2011
 */


public class Interset {
    public static int[] getIntersect(int[] list1, int[] list2) {
        BitSet bs = new BitSet();
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < list1.length; i++)
            bs.set(list1[i]);

        for (int i = 0; i < list2.length; i++)
            if (bs.get(list2[i]))
                set.add(list2[i]);

        int[] ret = new int[set.size()];
        int count = 0;

        for (Iterator<Integer> it = set.iterator(); it.hasNext();)
            ret[count++] = it.next();
        return ret;
    }

    public static void main(String[] args) {
//        int[] list1 = { 1, 3, 5, 7, 9, 13, 18, 20, 300 };
//        int[] list2 = { 2, 3, 6, 7, 10, 11, 18, 21, 3, 300 };
        int [] list1  = new int[10000000];
        int [] list2 = new int[10000000];

        for(int i = 0;i<list1.length;i++){
            list1[i] = Math.abs(new Random().nextInt(1000));
            list2[i] = Math.abs(new Random().nextInt(1000));
            
        }
        int[] list3 = getIntersect(list1, list2);
        System.out.println(Arrays.toString(list3));
    }
}

