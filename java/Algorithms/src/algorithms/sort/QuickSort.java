package algorithms.sort;

/**
 *
 * @author leeing
 * @date Mar 15, 2011
 */
public class QuickSort {

    private static void quickSort(Comparable a[], int low, int high) {
        if (low < high) {
            int pivotloc = partition(a, low, high);
            quickSort(a, low, pivotloc - 1);
            quickSort(a, pivotloc + 1, high);
        }
    }

    public static void quickSort(Comparable a[]) {
        quickSort(a, 0, a.length - 1);
    }

    private static int partition(Comparable a[], int low, int high) {
        Comparable pivotkey = a[low];
        while (low < high) {
            while (low < high && a[high].compareTo(pivotkey) >= 0) {
                high--;
            }

            Comparable temp = a[low];
            a[low] = a[high];
            a[high] = temp;

            while (low < high && a[low].compareTo(pivotkey) <= 0) {
                low++;
            }
            
            temp = a[low];
            a[low] = a[high];
            a[high] = temp;
        }
        return low;
    }
}
