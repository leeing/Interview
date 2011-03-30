package algorithms.interview;

/**
 * 循环比赛日程表
 * @author leeing
 * @date Mar 30, 2011
 */
public class CycleRace {
    
    public static void main(String[] args) {
        int [][] cycle = new int[8][8];
        int k = 3;
        table(k,cycle);
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                System.out.print(cycle[i][j]+"\t");
            }
            System.out.println("");
        }
    }

    /**
     * 
     * @param k
     * @param a
     */
    public static void table(int k,int[][]a){
        int n = 1;
        for(int i = 1;i<=k;i++){
            n*=2;
        }

        for(int i = 1;i<=n;i++){
            a[1][i] = 1;
        }

        int m = 1;
        for(int s = 1;s<=k;s++){
            n/=2;
            for(int t = 1;t<=n;t++){
                for(int i = m+1;i<2*m;i++){
                    for(int j = m+1;j<=2*m;j++){
                        a[i][j+(t-1)*m*2] = a[i-m][j+(t-1)*m*2-m];
                        a[i][j+(t-1)*m*2-m] = a[i-m][j+(t-1)*m*2];
                        m*=2;
                    }
                }
            }
        }
    }
}
