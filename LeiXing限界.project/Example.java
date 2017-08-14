//使用了类型界限，在比较两个对象的大小时，要用到compareTo方法

public class Example{
     public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType [] A){
           AnyType x = A[0];
           for(AnyType val:A){
              if( x.compareTo(val)<0)   x = val;
           }
           return x;
     }
}