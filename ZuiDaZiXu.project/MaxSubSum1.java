//法一：最直接的用三个for循环来实现算法，运行时间为O（N*N*N）

public class MaxSubSum1{
   public static int maxSubSum1(int [] A){
     int maxSum = 0;
     for(int i=0;i<A.length;i++){
         for(int j=i;j<A.length;j++){
              int thisSum = 0;
              for(int k=i;k<=j;k++){
                   thisSum += A[k];
              }
              if(thisSum>maxSum){
                   maxSum = thisSum;
              }
         }
      }
      return maxSum; 
   }
}
              