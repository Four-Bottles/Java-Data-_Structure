
//��ʵ�����õ�����Iterator���Ͼ���ô�ߴ��ϵ��÷�����һ�°�......

import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class Test_QuadraticHash_2{
    public static void main(String[] args){

        Random rand = new Random();
        Scanner reader = new Scanner(System.in);
        QuadraticHash_2<Integer> hash_2 = new QuadraticHash_2<Integer>();

        System.out.print("������Ԫ������");
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
        System.out.println("�����������Ϊ��");
        hash_2.print();
        System.out.print("������Ҫ���ҵ�����");
        int num = reader.nextInt();
        if(hash_2.contains(num))
            System.out.println("...Ԫ����...");
        else   
            System.out.println("...Ԫ�ز���...");
        System.out.print("������Ҫɾ��������");
        num = reader.nextInt();
        if(hash_2.contains(num)){
            hash_2.remove(num);
            System.out.println("ɾ����ģ�");
            hash_2.print();
        }
        else
            System.out.println("Ԫ�ز��ڣ�����ɾ��");
        System.out.println("�õ���������Ԫ�أ�");
        Iterator iterator = hash_2.iterator();
        while(iterator.hasNext()){
            System.out.print((Integer)iterator.next() + " ");
        }
        System.out.println();
        System.out.println("ɾ�����һ��Ԫ�غ�");
        iterator.remove();
        hash_2.print();
        System.out.print("�ÿպ��ӡ��");
        hash_2.makeEmpty();
        hash_2.print();
        
    }
}