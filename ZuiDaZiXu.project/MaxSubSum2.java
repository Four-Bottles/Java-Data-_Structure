//������ ����ڷ�һ��ʡȥ��һ��forѭ��������ʱ��ΪO(N*N)

public class MaxSubSum2{
    public static int maxSubSum2(int [] A){
         int maxSum = 0;
         for(int i = 0;i<A.length;i++){
              int thisSum = 0;
              for(int j=i;j<A.length;j++){
                   thisSum +=A[j];
                   if(thisSum>maxSum){
                         maxSum = thisSum;
                   }
              }
          }
          return maxSum;
    }
}