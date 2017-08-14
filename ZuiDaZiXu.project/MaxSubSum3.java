/*  ���������˵ݹ飬�Ѵ��������޷ֽ�Ϊ����С���⣬��Ȼ�Ƚ��鷳�����㷨��Ư������˵ʵ����������⣬�ݹ�Ļ�׼���⾹Ȼ��
left = right�����������ˡ������Ĳ�������������ʱ��ΪO(N*logN��*/

public class MaxSubSum3{
    private static int maxSumRec(int [] A,int left,int right){
        if(left == right){
             if(left>0)    return A[left];
             else   return 0;
        }
        int center = (left+right)/2;
        int maxLeft = maxSumRec(A,left,center);
        int maxRight = maxSumRec(A,center+1,right);
        
        int maxLeftBorderSum=0;
        int leftBorderSum=0;
        for(int i=center;i>=left;i--){
              leftBorderSum += A[i];
              if(leftBorderSum > maxLeftBorderSum)
                    maxLeftBorderSum = leftBorderSum;
        }
 
        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for(int i=center+1;i<=right;i++){
             rightBorderSum += A[i];
             if(rightBorderSum > maxRightBorderSum)            
                    maxRightBorderSum  = rightBorderSum;
        }
        int maxMiddle = maxLeftBorderSum + maxRightBorderSum;
        
        return   ((maxLeft>maxRight)?maxLeft:maxRight) > maxMiddle ?((maxLeft>maxRight)?maxLeft:maxRight) : maxMiddle;
     }
     
     public static int maxSubSum3(int []A){
         return maxSumRec(A,0,A.length-1);
     }
}        
                              