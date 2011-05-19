package algorithms.sort;

import algorithms.util.ArrayUtil;
import java.util.Random;

/**
 *
 * @author leeing
 * @date Mar 18, 2011
 */
public class HeapSort {

    public  static void heapSort(int array[]) {
        heapSort(array, array.length - 1);
    }

    private static void heapSort(int array[], int heapsize) {
        for (int i = heapsize / 2; i >= 0; i--) /* build max-heap */ {
            maxheapify(array, i, heapsize);
        }

        for (int i = heapsize; i >= 0; i--) /* delete max */ {
            ArrayUtil.swap(array, 0, heapsize);
            heapsize--;
            maxheapify(array, 0, heapsize);
        }

    }

    private static void maxheapify(int a[], int i, int heapsize) {

        int largest;

        int left = 2 * i;
        int right = 2 * i + 1;

        // 求出 根，左子，右子之中，值最大的下标
        if (left <= heapsize && a[left] > a[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= heapsize && a[right] > a[largest]) {
            largest = right;
        }

        // 如果不是根的值最大，则说明需要进行调整，所以要进行交换。
        if (largest != i) {
            ArrayUtil.swap(a, i, largest);
            maxheapify(a, largest, heapsize); /* recurrsion */
        }

        return; /* return when largest == i */
    }

    public static void main(String[] args) {
         int a[] = new int[20];
         for(int i = 0;i<a.length;i++){
             a[i] = new Random().nextInt(100);
         }

         heapSort(a);

         for(int i = 0;i<a.length;i++){
             System.out.println(a[i]);
         }

    }
}
