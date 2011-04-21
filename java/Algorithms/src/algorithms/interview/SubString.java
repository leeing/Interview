package algorithms.interview;

/**
 * 写一个函数，2 个参数，1 个字符串，1 个字节数，返回截取的字符串，要求字符串中的中文不能
 * 出现乱码：如（“我ABC”，4）应该截为“我AB”，输入（“我ABC 汉DEF”，6）应该输出为“我ABC”
 * 而不是“我ABC+汉的半个”。 
 * 
 * @author leeing
 * Created at Apr 21, 2011
 */
public class SubString {

    public static void main(String[] args) {
        System.out.println(subString("我ABC",2));
        System.out.println(subString("我ABC汉DEF",6));
    }

    public static String subString(String str, int size) {
        int bytes = 0; // 用来存储字符串的总字节数
        for (int i = 0; i < str.length(); i++) {
            if (bytes == size) {
                return str.substring(0, i);
            }
            char c = str.charAt(i);
            if (c < 256) {
                bytes += 1; // 英文字符的字节数看作1
            } else {
                bytes += 2; // 中文字符的字节数看作2
                if (bytes - size == 1) {
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }
}
