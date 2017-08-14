/*  那这个，就是传说中的别人家的算法了，这么复杂的问题，被人家一次过就搞定了，
运行时间为O(N)，聪明的算法，很棒，佩服。

public class MaxSubSum4{
     public static int maxSubSum4(int [] A){
         int maxSum = 0;
         int thisSum =0;
         for(int i=0;i<A.length;i++){
             thisSum += A[i];
             if(thisSum>maxSum){
                 maxSum = thisSum;
             } 
             else{ 
                 if(thisSum <0)
                      thisSum = 0;
             }
         }
         return maxSum;
     }
}
             