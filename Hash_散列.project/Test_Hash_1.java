import java.util.Random;
import java.util.Scanner;

public class Test_Hash_1{
    public static void main(String[] args){
         Hash_1<Integer> has = new Hash_1<>();
         Random rand = new Random();
         Scanner reader = new Scanner(System.in);
         System.out.print("请输入元素数：");
         int read = reader.nextInt();
         boolean[] bool = new boolean[read *2 +1];
         for(int i=0;i<read;i++){
             int x = rand.nextInt(read *2 +1);
             while(bool[x]){
                 x = rand.nextInt(read *2 +1);
             }
             bool[x] = true;
             has.insert(x);
         }
    
         System.out.println("OK,随机产生的这些数：");
         has.print_1();
         has.print_2();
    }
}