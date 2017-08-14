import java.util.Scanner;
import java.util.Random;

public class Test_LeftHeap{
    public static void main(String[] args){

         Scanner scan = new Scanner(System.in);
         LeftHeap<Integer> heap_1 = new LeftHeap<>();
         Random rand = new Random();
         System.out.print("输入要产生的数字个数：");
         int read = scan.nextInt();
         int num = rand.nextInt(read*20+1);
         for(int i=0;i<read;i++){
             heap_1.insert(num);
             num = rand.nextInt(read*20+1);
         }
         System.out.println("产生的随机数为：");
         heap_1.print();
         System.out.println("排序后（由小到大）：");
         heap_1.printSort();
         System.out.println("删除最小元素：");
         heap_1.deleteMin();
         heap_1.printSort();
         System.out.println("乱序：");
         heap_1.print();
         System.out.println("清空后：");
         heap_1.makeEmpty();
         heap_1.print();
    }
}