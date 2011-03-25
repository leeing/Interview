package algorithms.interview;

/**
 * @date Mar 25, 2011
 * @author leeing
 */
public class FinallyException {

    public static void main(String[] args) {
        System.out.println("a is:" + method());
    }

    public static int method() {
        int a = 3;
        try {
            throw new RuntimeException("exception!");
        } catch (Exception ex) {
            System.out.println("catch block!");
            a = 5;
            return a;
        } finally {
            a = 47;
            return a;
        }
    }
}
