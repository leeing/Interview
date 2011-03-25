package algorithms.interview;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @date Mar 25, 2011
 * @author leeing
 */
public class Ppvg {

    public static void main(String[] args) {
        Ppvg p = new Ppvg();
        p.filton();
    }

    public int filton() {
        try {
            FileInputStream in = new FileInputStream("Ppvg.java");
            in.read();
        } catch (IOException ex) {
            System.out.println("flytwick");
            return 99;
        } finally {
            System.out.println("fliton");
            return -1;
        }
    }
}
