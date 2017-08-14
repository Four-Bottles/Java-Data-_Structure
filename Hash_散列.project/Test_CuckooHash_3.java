import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;

public class Test_CuckooHash_3{
   public static void main(String[] args){

       CuckooHash_3<Integer> hash_3 = new CuckooHash_3<>(new IntegerHashFamily(5));
       Scanner reader = new Scanner(System.in);
       Random rand = new Random();
       System.out.println("请输入要生成的数字个数：");
       int read = reader.nextInt();
       boolean[] bool = new boolean[read*2+1];
       for(int i=0;i<read;i++){
           int num = rand.nextInt(read*2+1);
           while(bool[num]){
               num = rand.nextInt(read*2+1);
           } 
           bool[num] = true;
           hash_3.insert(num);
       }
       System.out.println("随机数：");
       hash_3.print();
       System.out.print("请输入要删除的数(-1结束)：");
       read = reader.nextInt();
       while(read!=-1){
          if(hash_3.contains(read))
              hash_3.remove(read);
          else
              System.out.println("元素不在，不能删除");
          System.out.print("请输入要删除的数(-1结束)：");
          read = reader.nextInt();
       }
       System.out.println("删除后的：");
       hash_3.print();
       System.out.println("用迭代器访问：");
       Iterator iterator = hash_3.iterator();
       while(iterator.hasNext()){
           System.out.print(iterator.next() + " ");
       }
       System.out.println();
       System.out.println("删除最后一个数：");
       iterator.remove();
       hash_3.print();
       System.out.println("清空后打印：");
       hash_3.makeEmpty();
       hash_3.print();
   }
}