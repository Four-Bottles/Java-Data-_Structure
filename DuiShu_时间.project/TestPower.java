import java.util.Scanner;
import java.math.BigInteger;

public class TestPower{
    public static void main(String [] args){
       System.out.println();
       System.out.print("�������������m�ʹη�n(m=0����)��");
       Scanner read = new Scanner(System.in);
       int m=1;
       while(m!=0){
           System.out.print("         m = "); 
           m = read.nextInt();
           System.out.print(" , "+ "n  = ");
           int n = read.nextInt();
           System.out.println(m+" �� "+ n+" �η��� "+ Power.pow(BigInteger.valueOf(m),n));
       }
    }
}  