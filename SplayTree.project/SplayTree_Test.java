// package kejun;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("unchecked")

public class SplayTree_Test{

    final static int TMD = 20;

    public static void main(String[] args){

        SplayTree<Integer> test = new SplayTree();
        Scanner read = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Input the size of the tree( size < " + TMD + " ): ");
        int size = read.nextInt();
        boolean[] bool = new boolean[TMD];
        for (int i = 0; i < size; ) {
            int k = rand.nextInt(TMD);
            if (!bool[k]) {
                test.insert(k);
                i += 1;
            }
            bool[k] = true;
        }
        // test.insert(12);
        // test.insert(3);
        // test.insert(5);
        // test.insert(1);
        // test.insert(0);
        // test.insert(12);
        // test.insert(10);
        // test.insert(8);
        // test.insert(78);
        // test.insert(97);
        // test.insert(100);
        // test.insert(110);
        // test.insert(100);

        System.out.println("The program begins.");
        System.out.println();
        test.print();
        System.out.println("The size of the tree is: " + test.size());
        System.out.println("Now the node of root is: " + test.find(test.root));
        System.out.println("The node which is found is: " + test.find(rand.nextInt(TMD)));
        System.out.println("Now the node of root is: " + test.find(test.root));
        System.out.print("Now the SplayTree is: ");
        test.print();

        // System.out.println("The node which is found is: " + test.find(3));
        // System.out.println("Now the node of root is: " + test.find(test.root));
        // System.out.print("Now the SplayTree is: ");
        // test.print();
        // System.out.println("Remove a node of 100.");
        // test.remove(100);
        // System.out.print("Now the SplayTree is: ");
        // test.print();
        // System.out.println("The size of the tree is: " + test.size());
        //
        // System.out.println("The minimum of the tree is: " + test.findMin());
        // System.out.println("The maximum of the tree is: " + test.findMax());
        // System.out.println("Is 211 in the tree: " + test.contains(211));
        // System.out.println("Is 100 in the tree:" + test.contains(100));
        // System.out.println("Is 97 in the tree: " + test.contains(97));
        // System.out.println("Insert a node of 211.");
        // System.out.println("Insert a node of -10.");
        // test.insert(211);
        // test.insert(-10);
        // test.print();
        // System.out.println("The size of the tree is: " + test.size());
        //
        // System.out.println("The minimum of the tree is: " + test.findMin());
        // System.out.println("The maximum of the is: " + test.findMax());
        // System.out.println("Is 211 in the tree: " + test.contains(211));
        // test.print();
        // System.out.println("The size of the tree is: " + test.size());
        //
        // System.out.println("The node is : " + test.findByPosition(4));
        // System.out.println("The node is : " + test.findByPosition(6));
        // System.out.println("The node is : " + test.findByPosition(1));
        // test.print();
        // System.out.println("The size of the tree is: " + test.size());
        //
        System.out.println("The program ends.");
    }
}
