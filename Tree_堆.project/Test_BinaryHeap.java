import java.util.Random;
import java.util.Scanner;

public class Test_BinaryHeap{
   public static void main(String[] args){

       BinaryHeap<Integer> heap_1 = new BinaryHeap<>();
       Random rand = new Random();
       Scanner reader = new Scanner(System.in);
       System.out.print("����Ҫ���������ָ�����");
       int read = reader.nextInt();
       Integer[] item = new Integer[read+2];
       int num = rand.nextInt(read*100);
       for(int i=0;i<read;i++){
            item[i] = num;
            num = rand.nextInt(read*100);
       }
       BinaryHeap<Integer> heap_2 = new BinaryHeap<>(item);
       System.out.println("�����������Ϊ��");
       heap_2.print();
       System.out.println();
       System.out.println("ɾ����С������Ԫ�غ�");
       heap_2.deleteMin();
       heap_2.deleteMin();
       heap_2.print();
       System.out.println("�����");
       heap_2.paixu_print();
       System.out.println("��գ�");
       heap_2.makeEmpty();
       heap_2.print();
   }
}