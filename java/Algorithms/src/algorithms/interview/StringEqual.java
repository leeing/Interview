package algorithms.interview;

/**
 *
 * @author leeing
 * Created at Apr 21, 2011
 */
public class StringEqual {
    public static void main(String[] args) {
        String a = "hello"+" "+"world";
        String b = "hello";
        String c = " world";

        System.out.println("hello world" == a);
        System.out.println(a == "hello world");
        System.out.println(a == (b + c)); // 注意~
        System.out.println((b + c) == a); // 注意 ~
        System.out.println((b + c) == (b + c)); // 注意 ~
    }
}
