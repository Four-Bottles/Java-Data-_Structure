/*  ����������Ǵ�˵�еı��˼ҵ��㷨�ˣ���ô���ӵ����⣬���˼�һ�ι��͸㶨�ˣ�
����ʱ��ΪO(N)���������㷨���ܰ��������

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
             