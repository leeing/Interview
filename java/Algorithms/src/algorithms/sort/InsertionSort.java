package algorithms.sort;

import java.util.Random;

/**
 * 直接插入排序，O(N^2)
 *
 * @author leeing
 * @date Mar 15, 2011
 */
public class InsertionSort {
    public static void insertionSort(Comparable[] a){
        for(int i = 1;i<a.length;i++){
          Comparable key = a[i];
          int j = i;
          // 注意 j>0 必须放在前面，不然会出现下标越界异常！！
          for(;j>0 && a[j-1].compareTo(key)>0;j--){// 目的就是为了挪出 key 的位置
              a[j] = a[j-1];
          }
          a[j] = key;
        }
    }

    private static void insertSort2(int []a){
        for(int i =1;i<a.length;i++){
            int temp = a[i];
            int j;
            for(j=i;j>0&&a[j-1]>temp;j--){
                a[j] = a[j-1];
            }
            a[j] = temp;
        }

        for(int i = 0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        int a[] = new int[10];
        for(int i = 0;i<a.length;i++){
            a[i] = new Random().nextInt(15);
        }
        insertSort2(a);
    }
}
