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
        System.out.println("我".getBytes().length); // 中文字符是3个字节
        System.out.println("a".getBytes().length);// 字母占用 1 个字节
    }

    /**
     * 感觉这道题出得是有错误的，（“我ABC”，4）应该返回 "我A"，但题意大概是把中文字符都认
     * 为占用两个字节了。
     * 
     * @param str 输入的字符串
     * @param size 字节数目
     * @return 截取的字符串
     */
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
