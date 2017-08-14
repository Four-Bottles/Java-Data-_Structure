import java.util.Scanner;
import java.util.Random;

public class Binary_Test{
     public static void main(String[] args){
          Scanner reader = new Scanner(System.in);
          BinarySearchTree<Integer> bin = new BinarySearchTree<> ();
          Random rand = new Random();
          System.out.print("请输入要添加的元素个数:");
          int read = reader.nextInt();
          boolean[] bool = new boolean[read];
          System.out.println("随机生成的数为：");
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
          System.out.println("随机生成的元素为(已排序)");
          bin.printTree();
          System.out.println();
          System.out.println("最小的数:" + bin.findMin() + "    最大的数:" + bin.findMax());
          System.out.print("请输入要查找的元素:");
          read = reader.nextInt();
          if(bin.contains(read))   System.out.println("元素在");
          else    System.out.println("元素不在");
          System.out.print("请输入要删除的元素：");
          read = reader.nextInt();
          if(bin.contains(read)){
               bin.remove(read);
               bin.printTree();
          }
          else   System.out.println("元素不在，不能删除");
          System.out.println("层序遍历的结果为：");
          bin.cengXuPrint();
          System.out.println();
     }
}