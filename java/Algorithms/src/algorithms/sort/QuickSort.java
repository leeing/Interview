package algorithms.sort;

/**
 *
 * @author leeing
 * @date Mar 15, 2011
 */
public class QuickSort {

    private static void quickSort(Comparable a[], int low, int high) {
        if (low < high) {
            int pivotloc = partition2(a, low, high);
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

    /**
     * 比上面的版本速度快，因为排序过程中对枢轴的赋值是多余的
     * 
     * @param a
     * @param low
     * @param high
     * @return
     */
    private static int partition2(Comparable a[], int low, int high) {
        Comparable pivotkey = a[low];
        Comparable temp = a[low];

        while (low < high) {
            while (low < high && a[high].compareTo(pivotkey) >= 0) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low].compareTo(pivotkey) <= 0) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }
}
