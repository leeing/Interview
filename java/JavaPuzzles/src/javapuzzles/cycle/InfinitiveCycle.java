package javapuzzles.cycle;

/**
 * 循环
 * 
 * @author leeing
 */
public class InfinitiveCycle {

    public static void main(String[] args) {
        puzzle29();
    }

    /**
     * NaN不等于任何浮点数，包括其本身在内
     */
    static void puzzle29(){
        double i = 0.0/0.0; // 此时 i = NaN
        // 可以用任何计算结果为 NaN's result to represent i.
        // also use: double i = Double.NaN;
        while(i!=i){
            System.out.println("Endless!");
        }
    }

    /**
     * + in String class
     */
    static void puzzle30(){
        String i = "hello world!";
        System.out.println("original length:"+i.length());
        System.out.println("i+0:"+(i+0));
        while(i!=i+0){ // 死循环
            System.out.println("Endless!");
        }
    }

    /**
     *  用一个 double 或 float 数值来表示无穷大是可以的。其次，将一个很小的浮点数加到
     *  一个很大的浮点数时，将不会改变大浮点数的值。第二点是违背直觉的，但是我们应该注意：
     *  二进制浮点运算只是对实际自述的一种近似！
     */
    static void puzzle28(){
        int start = Integer.MAX_VALUE - 1;
        for(int i = start;i<=start+1;i++){
            System.out.println("Endless!");
        }

        // also can use MAX:

        double i = Double.POSITIVE_INFINITY;
        // or: i = 1.0/0.0
        // or big enough *** i = 1.0e40 ****
        while(i==i+1){
            System.out.println("Endless!");
        }

        // 这个函数是用来表示浮点数值之间的一个距离，即 unit in the last place.
        Math.ulp(i);
    }

}
