import java.util.Scanner;
import java.util.Random;

public class Test_LeftHeap{
    public static void main(String[] args){

         Scanner scan = new Scanner(System.in);
         LeftHeap<Integer> heap_1 = new LeftHeap<>();
         Random rand = new Random();
         System.out.print("����Ҫ���������ָ�����");
         int read = scan.nextInt();
         int num = rand.nextInt(read*20+1);
         for(int i=0;i<read;i++){
             heap_1.insert(num);
             num = rand.nextInt(read*20+1);
         }
         System.out.println("�����������Ϊ��");
         heap_1.print();
         System.out.println("�������С���󣩣�");
         heap_1.printSort();
         System.out.println("ɾ����СԪ�أ�");
         heap_1.deleteMin();
         heap_1.printSort();
         System.out.println("����");
         heap_1.print();
         System.out.println("��պ�");
         heap_1.makeEmpty();
         heap_1.print();
    }
}