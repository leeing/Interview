package algorithms.sort;

/**
 *
 * @author leeing
 * @date Mar 15, 2011
 */
public class ShellSort {

    public static void shellSort(Comparable[] a) {
        for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < a.length; i++) {
                Comparable key = a[i];
                int j = i-gap;
                // 注意这里是用 key 和 a[j-gap] 进行比较
                for (; j >= 0 && key.compareTo(a[j]) < 0; j = j - gap) {
                    a[j+gap] = a[j];
                }
                a[j+gap] = key;
            }
        }
    }

}
