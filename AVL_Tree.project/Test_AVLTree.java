import java.util.Random;
import java.util.Scanner;

public class Test_AVLTree{
    public static void main(String[] args){
         AVL_Tree<Integer> avl_tree = new AVL_Tree<>();
         Scanner reader = new Scanner(System.in);
         Random rand = new Random();
         System.out.println("������AVL����Ԫ�ظ�����");
         int read = reader.nextInt();
         boolean[] bool = new boolean[read];
         System.out.print("Ԫ������ǰ��");
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
         System.out.println("Ԫ�������Ϊ��");
         avl_tree.printTree();
         System.out.println();
         System.out.print("������Ҫɾ����Ԫ�أ�");
         read = reader.nextInt();
         avl_tree.remove(read);
         System.out.println("ɾ����ģ�");
         avl_tree.printTree();
         System.out.println();
         System.out.println("��������Ľ��Ϊ��");
         avl_tree.cengXuPrint();
         System.out.println();
         System.out.println("�������");
         avl_tree.makeEmpty();
         avl_tree.printTree();
         System.out.println();
    }
}