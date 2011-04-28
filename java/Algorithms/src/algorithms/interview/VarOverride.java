package algorithms.interview;

/**
 *
 * @author leeing
 * Created at Apr 28, 2011
 */
interface  A{
   int x = 0;
}
class B{
   int x =1;
}
class C extends B implements A {
   public void pX(){
//      System.out.println(x);  //*** 这句会编译不过
       System.out.println("class B:"+super.x);
       System.out.println("interface A:"+A.x);
   }
   public static void main(String[] args) {
      new C().pX();
   }
}

