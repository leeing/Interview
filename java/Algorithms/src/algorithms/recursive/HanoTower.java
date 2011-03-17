package algorithms.recursive;

public class HanoTower {

    public static void main(String[] args) {
        hano(3,"A","B","C");
    }

    static void hano(int n,String left,String mid,String right){
        if(n>0){
            hano(n-1,left,right,mid);
            System.out.println(n+":"+left+"-->"+right);
            hano(n-1,mid,left,right);
        }
    }
}
