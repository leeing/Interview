package algorithms.interview;

/**
 *
 * @date Mar 24, 2011
 * @author leeing
 */
public class Increment {
    public static void main(String[] args) {
        Increment inc = new Increment();
        int i = 0;
        inc.fermin(i);
         i = i++; // 直接用 i++ 则会打印 1
        System.out.println("i = "+i);
    }

    void fermin(int i){
        i++;
    }
}
