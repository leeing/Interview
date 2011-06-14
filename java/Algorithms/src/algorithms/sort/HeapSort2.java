package algorithms.sort;

import java.util.Random;

/**
 * Created on  May 21, 2011
 * @author leeing
 */
public class HeapSort2 {

    public static void heapAjust(int a[], int s, int m) {
        //在a[s...m]中，除了a[s]外其余元素均满足堆的定义，调整a[s]的位置，使整个都满足堆的定义,成一个大顶堆
        int rc = a[s];
        for (int i = 2 * s; i < m; i *= 2) {//这里应该是i<m,而不是i<=m，因为下面有a[i+1]
            if (a[i] < a[i + 1]) {
                i++;
            }
            if (a[i] <= rc) {//这里没有=号会出问题
                break;
            }
            a[s] = a[i];
            s = i;
        }
        a[s] = rc;
    }

    public static void heapSort(int a[]) {
        for (int i = (a.length - 1) / 2; i >= 0; i--) {//从第一个非叶子节点开始依次调整成该节点为根的树为大顶堆
            heapAjust(a, i, a.length - 1);
        }
        for (int i = a.length - 1; i >= 0; i--) {
            int temp = a[0];//将根结点和最后一个未排序的节点交互，
            a[0] = a[i];
            a[i] = temp;
            heapAjust(a, 0, i - 1);//调整
        }
    }

    public static void main(String[] args) {
        int a[] = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(100);
        }

        heapSort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}
