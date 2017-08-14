import java.util.Scanner;
import java.util.Random;

public class Test_BinomialQueue{
   public static void main(String[] args){
       
        BinomialQueue<Integer> b_queue = new BinomialQueue<> ();
        BinomialQueue<Integer> queue = new BinomialQueue<> ();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("输入要产生的数字个数：");
        int read = scan.nextInt();
        int number = rand.nextInt(read*10+1);
        int[] array = new int[read];
        for(int i=0;i<read;i++){
            array[i] = number;
            b_queue.insert(number);
            number = rand.nextInt(read*10+1);
        }
        System.out.print("第一组：");
        for(int i : array){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=0;i<read;i++){
            queue.insert(number);
            array[i] = number;
            number = rand.nextInt(read*10+1);
        }
        System.out.print("第二组：");
        for(int i: array){
             System.out.print(i+" ");
        }
        System.out.println();

        System.out.println("第一组删除最小："); 
        b_queue.deleteMin();
        b_queue.print();
        System.out.println("第二组删除最小："); 
        queue.deleteMin();
        queue.print();
        System.out.println("第一组排序：");      
        b_queue.printSort();
        System.out.println("第二组排序：");
        queue.printSort();
        System.out.println("两组合并后排序：");
        b_queue.merge(queue);
        b_queue.printSort();
        System.out.println("再乱序：");
        b_queue.print();
        System.out.println("再排序（神经病啊）：");
        b_queue.printSort();
   }
}