package algorithms.util;

/**
 *
 * @author leeing
 * @date Mar 15, 2011
 */
public class ArrayUtil {
    
    public static void print(Comparable array[]){
        for(int i = 0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println("");
    }

    public static final int ARRAY_SIZE = 10000;
    
}
