import java.util.Scanner;
import java.util.Random;

public class Test_BinomialQueue{
   public static void main(String[] args){
       
        BinomialQueue<Integer> b_queue = new BinomialQueue<> ();
        BinomialQueue<Integer> queue = new BinomialQueue<> ();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("����Ҫ���������ָ�����");
        int read = scan.nextInt();
        int number = rand.nextInt(read*10+1);
        int[] array = new int[read];
        for(int i=0;i<read;i++){
            array[i] = number;
            b_queue.insert(number);
            number = rand.nextInt(read*10+1);
        }
        System.out.print("��һ�飺");
        for(int i : array){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=0;i<read;i++){
            queue.insert(number);
            array[i] = number;
            number = rand.nextInt(read*10+1);
        }
        System.out.print("�ڶ��飺");
        for(int i: array){
             System.out.print(i+" ");
        }
        System.out.println();

        System.out.println("��һ��ɾ����С��"); 
        b_queue.deleteMin();
        b_queue.print();
        System.out.println("�ڶ���ɾ����С��"); 
        queue.deleteMin();
        queue.print();
        System.out.println("��һ������");      
        b_queue.printSort();
        System.out.println("�ڶ�������");
        queue.printSort();
        System.out.println("����ϲ�������");
        b_queue.merge(queue);
        b_queue.printSort();
        System.out.println("������");
        b_queue.print();
        System.out.println("�������񾭲�������");
        b_queue.printSort();
   }
}