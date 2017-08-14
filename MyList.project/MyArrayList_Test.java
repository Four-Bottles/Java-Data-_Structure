import java.util.Scanner;
import java.util.Iterator;

public class MyArrayList_Test{
      public static void main(String[] args){
            MyArrayList<Integer> list = new MyArrayList<Integer>();
            System.out.println("请输入数字（-1结束）：");
            Scanner reader = new Scanner(System.in);
            int read = reader.nextInt();
            while(read!=-1){
                 list.add(read);
                 read = reader.nextInt();
            }
            System.out.println("你输入的数字为：");
            for(Iterator it = list.iterator();it.hasNext();){
                Integer x = (Integer)it.next();
                System.out.print( x +" ");
            }
            System.out.println();
            System.out.print("请输入要删除元素的下标(-1结束，注意不要过界)：");
            read = reader.nextInt();
            while(read!=-1 && list.size()>0){
                list.remove(read);
                read = reader.nextInt();
            }
            System.out.println("删除后的数组元素为：");
            for(Object x: list){
                System.out.print((Integer)x +" ");
            }
            System.out.println();
            Iterator its = list.iterator();
            do{
                its.next();
            }while(its.hasNext());
            System.out.print("请输入要删除的元素个数（从最后一个数依次删除,注意不要删多了）：");
            read = reader.nextInt();
            for(int i=0;i<read;i++){
                  its.remove();
            }
            for(Object x: list){
                System.out.print((Integer)x +" ");
            }
            System.out.println();
      }
}