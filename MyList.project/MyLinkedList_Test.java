import java.util.Scanner;
import java.util.Iterator;

public class MyLinkedList_Test{
     public static void main(String[] args){

           Scanner reader = new Scanner(System.in); 
           MyLinkedList<Integer> list = new MyLinkedList<Integer>();
           System.out.println("请输入数字（-1结束）");
           int read = reader.nextInt();
           while(read!=-1){
               list.add(read);
               read = reader.nextInt();    
           }
           System.out.println("你输入的数组为：");
           Iterator its = list.iterator();
           while(its.hasNext()){
               System.out.print((Integer)its.next() + " ");
           }
           System.out.println();
           System.out.print("请输入要删除的数的下标（-1结束删除操作）");
           read = reader.nextInt();
           while(read!=-1){
               list.remove(read);
               System.out.println("删除后的新数组：");
               for(Iterator it = list.iterator();it.hasNext();){
                     System.out.print((Integer)it.next() + " ");
               }
               System.out.println();
               read = reader.nextInt();
           }
           System.out.println("删除最后一个数的数组为：");
           Iterator is = list.iterator();
           while(is.hasNext()){
               is.next();
           }
           is.remove();
           for(Object x:list){
               System.out.print((Integer)x + " ");
           }
           System.out.println();
           System.out.print("请输入要交换元素的下标（两个数，注意不要越界）");
           int a = reader.nextInt();
           int b = reader.nextInt();
           list.change(a,b);
           System.out.println("交换后的数组为：");
           for(Object x : list){
                 System.out.print((Integer)x + " ");
           }
           System.out.println();
           System.out.print("请输入要检测的数（-1结束操作）：");      //检测数字的例程不太对，数字一大就不能检测，会自动显示不存在
           Integer re = reader.nextInt();
           while(re != -1){
                 list.contains(re);
                 System.out.print("请输入要检测的数：");
                 re = reader.nextInt();
           }
           System.out.println();
     }
}
