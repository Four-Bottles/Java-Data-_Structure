import java.math.BigInteger;
 
public class Power{
    public static BigInteger pow(BigInteger x , int n){
         if(n==0)
                  return BigInteger.valueOf(1);
         if(n==1)
                  return x;
         else{
            if(n % 2 ==0)
                 return pow(x.multiply(x),n/2);
            else    return x.multiply(pow(x.multiply(x),n/2));
         }
    }
}