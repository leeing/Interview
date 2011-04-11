package algorithms.interview;

/**
 *
 * @author leeing
 * @date Apr 11, 2011
 */
public class SubVal extends B {

    private int a = 3;

    @Override
    public void print() {
        System.out.println("a is:" + a);
    }

    public static void main(String[] args) {
        SubVal a = new SubVal();
        a.print();
    }
}

class B {

    private int a = 4;

    public void print() {
        System.out.println("a is:" + a);
    }
}
