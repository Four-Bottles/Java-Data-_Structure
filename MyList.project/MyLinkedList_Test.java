import java.util.Scanner;
import java.util.Iterator;

public class MyLinkedList_Test{
     public static void main(String[] args){

           Scanner reader = new Scanner(System.in); 
           MyLinkedList<Integer> list = new MyLinkedList<Integer>();
           System.out.println("���������֣�-1������");
           int read = reader.nextInt();
           while(read!=-1){
               list.add(read);
               read = reader.nextInt();    
           }
           System.out.println("�����������Ϊ��");
           Iterator its = list.iterator();
           while(its.hasNext()){
               System.out.print((Integer)its.next() + " ");
           }
           System.out.println();
           System.out.print("������Ҫɾ���������±꣨-1����ɾ��������");
           read = reader.nextInt();
           while(read!=-1){
               list.remove(read);
               System.out.println("ɾ����������飺");
               for(Iterator it = list.iterator();it.hasNext();){
                     System.out.print((Integer)it.next() + " ");
               }
               System.out.println();
               read = reader.nextInt();
           }
           System.out.println("ɾ�����һ����������Ϊ��");
           Iterator is = list.iterator();
           while(is.hasNext()){
               is.next();
           }
           is.remove();
           for(Object x:list){
               System.out.print((Integer)x + " ");
           }
           System.out.println();
           System.out.print("������Ҫ����Ԫ�ص��±꣨��������ע�ⲻҪԽ�磩");
           int a = reader.nextInt();
           int b = reader.nextInt();
           list.change(a,b);
           System.out.println("�����������Ϊ��");
           for(Object x : list){
                 System.out.print((Integer)x + " ");
           }
           System.out.println();
           System.out.print("������Ҫ��������-1������������");      //������ֵ����̲�̫�ԣ�����һ��Ͳ��ܼ�⣬���Զ���ʾ������
           Integer re = reader.nextInt();
           while(re != -1){
                 list.contains(re);
                 System.out.print("������Ҫ��������");
                 re = reader.nextInt();
           }
           System.out.println();
     }
}
