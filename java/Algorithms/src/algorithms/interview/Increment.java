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

        int j = 0;
        for(i = 0;i<100;i++){
            j = j++;  // 注意这里！！
        }

        System.out.println("j = "+j);// 依旧得到的是 j = 0!!
    }

    void fermin(int i){
        i++;
    }
}
// 另外从效率来看：　i++　> x+=1 > x=x+1