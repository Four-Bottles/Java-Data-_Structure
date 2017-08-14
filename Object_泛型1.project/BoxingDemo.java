//使用了泛型类GenericMemoryCell<Integer>,且完成了自动装箱、自动拆箱

public class BoxingDemo{
   public static void main(String []args){
       GenericMemoryCell<Integer> m = new GenericMemoryCell<>();
       m.write(50);              /*自动装箱，即把一个int 型变量传给一个Integer对象
                                             编译器在幕后插入一个对Integer构造方法的调用
       int val = m.read();       //自动拆箱，即把一个Integer对象放到需要int 型变量的地方
                                             编译器在幕后插入一个对intValue方法的调用
       System.out.println("Contents are: " + val);
   }
}