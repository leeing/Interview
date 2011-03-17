package algorithms.recursive;

/**
 * 递归计算一个数列的全排列
 * 固定前 0~k-1个元素，k~m 则为全排列
 * @author leeing
 * @date Mar 3, 2011
 */
public class Permutation {
    public static void main(String[] args) {
        Object objs[] = new Object[10];
        for(int i = 0;i<objs.length;i++){
            objs[i] = i;
        }
        
        perm(objs,5,7);
    }

    public static void perm(Object objs[],int k,int m){
        if(k>m || m>objs.length-1){
            throw new IllegalArgumentException("k<=m,&& m<objs.length");
        }
        if(k==m){
            for(int i = 0;i<=k;i++){
                System.out.print(objs[i]+"  ");
            }
            System.out.println("");
        }else{
            for(int i = k;i<=m;i++){
                System.err.println("k= "+k);
                swap(objs,k,i);
                perm(objs,k+1,m);
                swap(objs,k,i);
            }
        }

    }

    private static void swap(Object[] objs,int k,int m){
        Object temp = objs[k];
        objs[k] = objs[m];
        objs[m] = temp;
    }

}
