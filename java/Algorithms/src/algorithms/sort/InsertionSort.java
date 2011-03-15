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
          for(;a[j-1].compareTo(key)>0;j--){
              a[j] = a[j-1];
          }
          a[j] = key;
        }
    }
}
