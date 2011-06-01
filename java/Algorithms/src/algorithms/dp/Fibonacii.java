package algorithms.dp;

/**
 *
 * @author leeing
 * Created at Jun 1, 2011
 */
public class Fibonacii {

    public  static int[] temp = new int[50];

    static {
        for(int i = 0;i<temp.length;i++){
            temp[i] = -1;
        }

        temp[1] = 1;
        temp[2] = 1;
    }

    /**
     *  采用了动态规划的方案，存储子问题的解
     * @param num
     * @return
     */
    public static int fib1(int num){

        if(temp[num]!=-1){
            return temp[num];
        }else{
            temp[num] = fib1(num-1)+fib1(num-2);
        }
        return temp[num];
    }


    public static int fib2(int num){
        if(num==1||num==2){
            return 1;
        }else{
            return fib2(num-1)+fib2(num-2);
        }
    }
    public static void main(String[] args) {
        for(int i = 1;i<50;i++){
            System.out.println(fib1(i));
        }
    }
}
