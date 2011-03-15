package algorithms.sort;

/**
 * 
 * @author leeing
 * @date Mar 15, 2011
 */
public class BubbleSort {

    public static void bubbleSort(Comparable a[]){
        for(int i = 0;i<a.length;i++){
            for(int j = 0;j<a.length-i-1;j++){
                if(a[j].compareTo(a[j+1])>0){
                    Comparable temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

}
