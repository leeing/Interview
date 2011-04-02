package algorithms.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * question : list.remove(Object) and list.remove(int)
 * @date Apr 2, 2011
 * @author leeing
 */
public class Contains {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0;i<=10;i++){
            list.add(i);
        }
//        list.remove(10);
        Integer i = list.remove(10);
        
        System.out.println(list.contains(10));
    }
}
