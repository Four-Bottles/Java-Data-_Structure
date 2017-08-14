import java.util.Random;
import java.util.Scanner;

public class Test_AVLTree{
    public static void main(String[] args){
         AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
         Scanner reader = new Scanner(System.in);
         Random rand = new Random();
         System.out.println("请输入AVL树的元素个数：");
         int read = reader.nextInt();
         boolean[] bool = new boolean[read];
         System.out.print("元素排序前：");
         for(int i=0;i<read;i++){
              int x = rand.nextInt(read);
              while(bool[x]){
                  x = rand.nextInt(read);
              }
              bool[x] = true;
              avl_tree.insert(x);
              System.out.print(x + "  ");
         } 
         System.out.println();
         System.out.println("元素排序后为：");
         avl_tree.printTree();
         System.out.println();
         System.out.print("请输入要删除的元素：");
         read = reader.nextInt();
         avl_tree.remove(read);
         System.out.println("删除后的：");
         avl_tree.printTree();
         System.out.println();
         System.out.println("层序遍历的结果为：");
         avl_tree.cengXuPrint();
         System.out.println();
         System.out.println("清空树啦");
         avl_tree.makeEmpty();
         avl_tree.printTree();
         System.out.println();
    }
}