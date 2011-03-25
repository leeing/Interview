package algorithms.interview;

import java.util.HashMap;
import java.util.Random;

/**
 * Generate 100 random numbers, and find the max count of the num.
 * @date Mar 25, 2011
 * @author leeing
 */
public class MaxRandom {

    public static void main(String[] args) {
        Random random = new Random();

        int array[] = new int[100];

        for(int i = 0;i<array.length;i++){
            array[i] = Math.abs(random.nextInt())%100 + 1;
        }

        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<array.length;i++){
            if(map.containsKey(array[i])){
                int count = map.get(array[i]);
                map.put(array[i], count+1);
            }else{
                map.put(array[i], 1);
            }
        }

        int maxCounter = 0;
        int maxValue = 0;
        for(int randomNum:map.keySet()){
            int counter = map.get(randomNum);
            System.out.print("Number is :"+randomNum);
            System.out.println(" ,counter is:"+counter);
            if(counter >= maxCounter){
                maxCounter = counter;
                maxValue = randomNum;
            }
        }

        System.out.println("Max number is:"+maxValue+" ,Max count is:"+maxCounter);

    }
}
