package algorithms.interview;

import java.lang.reflect.Method;

/**
 * 一个类A，其中有方法int f(int a,int b),怎么通过反射调用此方法？
 * @author leeing
 * Created at Apr 22, 2011
 */
public class ReflectMethod {

    public static void main(String[] args) throws Exception {
        A a;
        a = (A) Class.forName("algorithms.interview.A").newInstance();
        Method m = A.class.getMethod("f", int.class, int.class);
        m.invoke(a, 5, 6);
    }
}
