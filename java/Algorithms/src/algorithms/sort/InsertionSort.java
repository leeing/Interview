package algorithms.sort;

/**
 * 直接插入排序，O(N^2)
 *
 * @author leeing
 * @date Mar 15, 2011
 */
public class InsertionSort {

    public static void insertionSort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable key = a[i];
            int j = i - 1;
            // 注意 j>0 必须放在前面，不然会出现下标越界异常！！
            for (; j >= 0 && a[j].compareTo(key) > 0; j--) {// 目的就是为了挪出 key 的位置
                a[j + 1] = a[j];
            }
            a[j + 1] = key;
        }
    }
}
