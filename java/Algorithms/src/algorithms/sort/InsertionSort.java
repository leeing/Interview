package algorithms.sort;

/**
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
          for(;j>0 && a[j-1].compareTo(key)>0;j--){
              a[j] = a[j-1];
          }
          a[j] = key;
        }
    }
}
