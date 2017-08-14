import java.util.Scanner;
import java.util.Random;

public class Binary_Test{
     public static void main(String[] args){
          Scanner reader = new Scanner(System.in);
          BinarySearchTree<Integer> bin = new BinarySearchTree<> ();
          Random rand = new Random();
          System.out.print("������Ҫ��ӵ�Ԫ�ظ���:");
          int read = reader.nextInt();
          boolean[] bool = new boolean[read];
          System.out.println("������ɵ���Ϊ��");
          for(int i=0;i<read;i++){
               int x = rand.nextInt(read);
               while(bool[x]){
                   x = rand.nextInt(read);
               }
               bin.insert(x); 
               bool[x] = true;
               System.out.print(x + "  ");
          }
          System.out.println();
          System.out.println("������ɵ�Ԫ��Ϊ(������)");
          bin.printTree();
          System.out.println();
          System.out.println("��С����:" + bin.findMin() + "    ������:" + bin.findMax());
          System.out.print("������Ҫ���ҵ�Ԫ��:");
          read = reader.nextInt();
          if(bin.contains(read))   System.out.println("Ԫ����");
          else    System.out.println("Ԫ�ز���");
          System.out.print("������Ҫɾ����Ԫ�أ�");
          read = reader.nextInt();
          if(bin.contains(read)){
               bin.remove(read);
               bin.printTree();
          }
          else   System.out.println("Ԫ�ز��ڣ�����ɾ��");
          System.out.println("��������Ľ��Ϊ��");
          bin.cengXuPrint();
          System.out.println();
     }
}