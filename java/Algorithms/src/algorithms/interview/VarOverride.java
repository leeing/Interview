package algorithms.interview;

/**
 *
 * @author leeing
 * Created at Apr 28, 2011
 */
interface  AA{
   int x = 0;
}
class BB{
   int x =1;
}
class C extends BB implements AA {
   public void pX(){
//      System.out.println(x);  //*** 这句会编译不过
       System.out.println("class B:"+super.x);
       System.out.println("interface A:"+AA.x);
   }
   public static void main(String[] args) {
      new C().pX();
   }
}

