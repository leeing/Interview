package algorithms.interview;

/**
 *
 * 在test方法中，直接调用getClass().getName()方法，返回的是Test类名由于getClass()在
 * Object类中定义成了final，子类不能覆盖该方法，所以，在test方法中调用getClass().getName()
 * 方法，其实就是在调用从父类继承的getClass()方法，等效于调用super.getClass().getName()方法，
 * 所以，super.getClass().getName()方法返回的也应该是Test。如果想得到父类的名称，应该用如下代码：
 * getClass().getSuperClass().getName();
 * 
 * @author leeing
 * Created at Apr 19, 2011
 */
import java.util.Date;
public  class Super extends Date{
	public static void main(String[] args) {
		new Super().test();
	}

	public void test(){
		System.out.println(super.getClass().getName());
                System.out.println(this.getClass().getSuperclass().getName());
	}
}
