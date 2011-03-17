package algorithms.recursive;

/**
 *
 * @author leeing
 * @date Mar 4, 2011
 */
public class PrintDecimal {

    public static void main(String[] args) {
        printNum(13434);
    }

    public static void printNum(int num) {
        if (num >= 10) {
            printNum(num / 10);
        }
        System.out.println(num % 10);
    }
}
