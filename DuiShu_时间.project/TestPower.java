import java.util.Scanner;
import java.math.BigInteger;

public class TestPower{
    public static void main(String [] args){
       System.out.println();
       System.out.print("请依次输入底数m和次方n(m=0结束)：");
       Scanner read = new Scanner(System.in);
       int m=1;
       while(m!=0){
           System.out.print("         m = "); 
           m = read.nextInt();
           System.out.print(" , "+ "n  = ");
           int n = read.nextInt();
           System.out.println(m+" 的 "+ n+" 次方是 "+ Power.pow(BigInteger.valueOf(m),n));
       }
    }
}  