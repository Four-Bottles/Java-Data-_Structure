//ʹ�������ͽ��ޣ��ڱȽ���������Ĵ�Сʱ��Ҫ�õ�compareTo����

public class Example{
     public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType [] A){
           AnyType x = A[0];
           for(AnyType val:A){
              if( x.compareTo(val)<0)   x = val;
           }
           return x;
     }
}