package algorithms.interview;

import java.util.Random;

/**
 * 最大子序列和算法
 * @author leeing
 * @date Mar 2, 2011
 */
public class MaxSubSeq {

    public static void main(String[] args) {
        int[] a = new int[1000];
        for(int i = 0;i<a.length;i++){
            if(i%2==0)
                a[i] = new Random().nextInt(10);
            else
                a[i] = -new Random().nextInt(10);
        }

        System.out.println("max1: " + max1(a));
        System.out.println("max2: " + max2(a));
        System.out.println("max3: " + max3(a));
    }

    public static int max1(int[] a) {
        int max = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int current = 0;
                for (int k = i; k <= j; k++) {
                    current = current + a[k];
                }
                if (current > max) {
                    max = current;
                    start = i;
                    end = j;
                }
            }
        }
        System.out.println("start:" + start + ",end=" + end);
        return max;
    }

    public static int max2(int[] a) {
        int max = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < a.length; i++) {
            int current = 0;
            for (int j = i; j < a.length; j++) {
                current = current + a[j];
                if (current > max) {
                    max = current;
                    start = i;
                    end = j;
                }
            }
        }
        System.out.println("start:" + start + ",end=" + end);
        return max;
    }

    public static int max3(int[]a){
        int max = 0;
        int current = 0;
        int start = 0;
        int end = 0;
        for(int i = 0,j=0;j<a.length;j++){
            current = current + a[j];
            if(current > max){
                max = current;
                start = i;
                end = j;
            }else if(current<0){
                i = j+1;
                current = 0;
            }
        }
        System.out.println("start:"+start+",end="+end);
        return max;
    }
}
