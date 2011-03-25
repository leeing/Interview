package algorithms.interview;

/**
 * @date Mar 24, 2011
 * @author leeing
 */
public class Count {
    public static void main(String[] args) {
        int i = 0;
        i = i++ + ++i;
        int j = 0;
        j = ++j + j++ + j++ + j++;
        int k = 0;
        k = k++ + k++ + k++ + ++k;
        int h = 0;
        h = ++h + ++h;

        System.out.println("i = "+i);
        System.out.println("j = "+j);
        System.out.println("k = "+k);
        System.out.println("h = "+h);
    }
}
