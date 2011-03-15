package algorithms.sort;

/**
 *
 * @author leeing
 * @date Mar 15, 2011
 */
public class SelectionSort {

    public static void selectionSort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            Comparable min= a[i];
            for(int j = i;j<a.length-1;j++){
                if(a[j+1].compareTo(min)<0){
                    Comparable temp = min;
                    min = a[j+1];
                    a[j+1] = temp;
                }
            }
            a[i] = min;
        }
    }
}
