//��һ����ֱ�ӵ�������forѭ����ʵ���㷨������ʱ��ΪO��N*N*N��

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
              