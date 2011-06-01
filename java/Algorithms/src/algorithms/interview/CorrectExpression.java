package algorithms.interview;

/**
 *
 * @author leeing
 * Created at Apr 19, 2011
 */
public class CorrectExpression {
    public static void main(String[] args) {
        long l = 0xfffL;
        float d = 0.9239f;
        String s="你好";
        int i=3; s=i+s;
        System.out.println("d is:"+d);
        System.out.println("s:"+s);

        OK ok = new OK();
        ok.say();


    }

    public void add(int a){}
    public void add(float f){}

    public void modify(){
        int I,j,k;
        k=0;
        I=100;
        while(I>0){
            j = I*2;
            System.out.println("The value of j is :"+j);
            k = k+1;
            I--;
        }
    }
}

class MyThread implements Runnable{


    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

class ConcreteClass {
}

abstract class AThread extends ConcreteClass {

    public abstract void say();
}

class OK extends AThread {

    @Override
    public void say() {
        System.out.println("OK!");
    }
}

