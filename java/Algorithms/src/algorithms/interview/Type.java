package algorithms.interview;

/**
 * @date Mar 25, 2011
 * @author leeing
 */
public class Type {

    public static void main(String[] args) {
        byte b = 2, e= 3;
        byte f = 5+3;
//        f = 530 + 5; 这样写会出错，提示可以损失精度
        // 《Java面试题宝典》说这里需要显式转换，但其实不用。
        // 　但如果是　f = b + e 则无法编译
        System.out.println("f is:"+f); // 打印的是 5
    
    }
}
