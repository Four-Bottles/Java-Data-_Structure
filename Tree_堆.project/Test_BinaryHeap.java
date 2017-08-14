import java.util.Random;
import java.util.Scanner;

public class Test_BinaryHeap{
   public static void main(String[] args){

       BinaryHeap<Integer> heap_1 = new BinaryHeap<>();
       Random rand = new Random();
       Scanner reader = new Scanner(System.in);
       System.out.print("输入要产生的数字个数：");
       int read = reader.nextInt();
       Integer[] item = new Integer[read+2];
       int num = rand.nextInt(read*100);
       for(int i=0;i<read;i++){
            item[i] = num;
            num = rand.nextInt(read*100);
       }
       BinaryHeap<Integer> heap_2 = new BinaryHeap<>(item);
       System.out.println("产生的随机数为：");
       heap_2.print();
       System.out.println();
       System.out.println("删除最小的两个元素后：");
       heap_2.deleteMin();
       heap_2.deleteMin();
       heap_2.print();
       System.out.println("排序后：");
       heap_2.paixu_print();
       System.out.println("清空：");
       heap_2.makeEmpty();
       heap_2.print();
   }
}