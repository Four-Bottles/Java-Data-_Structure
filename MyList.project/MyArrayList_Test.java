import java.util.Scanner;
import java.util.Iterator;

public class MyArrayList_Test{
      public static void main(String[] args){
            MyArrayList<Integer> list = new MyArrayList<Integer>();
            System.out.println("���������֣�-1��������");
            Scanner reader = new Scanner(System.in);
            int read = reader.nextInt();
            while(read!=-1){
                 list.add(read);
                 read = reader.nextInt();
            }
            System.out.println("�����������Ϊ��");
            for(Iterator it = list.iterator();it.hasNext();){
                Integer x = (Integer)it.next();
                System.out.print( x +" ");
            }
            System.out.println();
            System.out.print("������Ҫɾ��Ԫ�ص��±�(-1������ע�ⲻҪ����)��");
            read = reader.nextInt();
            while(read!=-1 && list.size()>0){
                list.remove(read);
                read = reader.nextInt();
            }
            System.out.println("ɾ���������Ԫ��Ϊ��");
            for(Object x: list){
                System.out.print((Integer)x +" ");
            }
            System.out.println();
            Iterator its = list.iterator();
            do{
                its.next();
            }while(its.hasNext());
            System.out.print("������Ҫɾ����Ԫ�ظ����������һ��������ɾ��,ע�ⲻҪɾ���ˣ���");
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