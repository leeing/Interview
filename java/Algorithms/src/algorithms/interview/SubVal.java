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

class B implements I{

    private int a = 4; // 接口中的变量被屏蔽

    public void print() {
        System.out.println("a is:" + a);
    }
}

interface I{
    String a = "hello world!";
}
