
//其实有意用迭代器Iterator，毕竟那么高大上的用法，试一下吧......

import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class Test_QuadraticHash_2{
    public static void main(String[] args){

        Random rand = new Random();
        Scanner reader = new Scanner(System.in);
        QuadraticHash_2<Integer> hash_2 = new QuadraticHash_2<Integer>();

        System.out.print("请输入元素数：");
        int read = reader.nextInt();
 
        boolean[] bool = new boolean[read *2 +1];
        for(int i=0;i<read;i++){
             int num = rand.nextInt(read *2 +1);
             while(bool[num]){
                 num = rand.nextInt(read *2 +1);
             }
             hash_2.insert(num);
             bool[num] = true;
        }
        System.out.println("随机产生的数为：");
        hash_2.print();
        System.out.print("请输入要查找的数：");
        int num = reader.nextInt();
        if(hash_2.contains(num))
            System.out.println("...元素在...");
        else   
            System.out.println("...元素不在...");
        System.out.print("请输入要删除的数：");
        num = reader.nextInt();
        if(hash_2.contains(num)){
            hash_2.remove(num);
            System.out.println("删除后的：");
            hash_2.print();
        }
        else
            System.out.println("元素不在，不能删除");
        System.out.println("用迭代器访问元素：");
        Iterator iterator = hash_2.iterator();
        while(iterator.hasNext()){
            System.out.print((Integer)iterator.next() + " ");
        }
        System.out.println();
        System.out.println("删除最后一个元素后：");
        iterator.remove();
        hash_2.print();
        System.out.print("置空后打印：");
        hash_2.makeEmpty();
        hash_2.print();
        
    }
}