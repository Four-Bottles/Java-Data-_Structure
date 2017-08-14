// �˴�����Shapeʵ����Comparable�Ľ�ڣ���ʵû�У����Ա��벻ͨ����

public class FindMaxDemo{
   public static Comparable findMax(Comparable [] arr){
      int maxIndex = 0;
      for(int i=1;i<arr.length;i++){
           if(arr[maxIndex].compareTo(arr[i])<0)   maxIndex = i;
      }
      return arr[maxIndex];
   }

   public static void main(String args[]){
      Shape [] sh = { new Circle(2.0) ,
                      new Square(3.0),
                      new Rectangle(3.0,4.0)};
      String [] str = {"Joe","Bob","Bill","Zeke"};
      System.out.printin(findMax(sh));
      System.out.println(findMax(str));
   }
}