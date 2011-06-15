package algorithms.interview;

/**
 *
 * @author leeing
 * Created at Jun 14, 2011
 */
// 不可以同时是 static, native, synchronized 的。
public abstract  class Abstract extends Concrete{
    public static synchronized void method(){
        
    }

    // private/final 和 abstract 不能同时使用。
//    private abstract String doSomething ();
}
