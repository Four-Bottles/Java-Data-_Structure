/*  法三：用了递归，把大问题无限分解为两个小问题，虽然比较麻烦，但算法很漂亮。（说实话，很难理解，递归的基准问题竟然是
left = right，这个，，，恕在下真的不懂，对了运行时间为O(N*logN）*/

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
                              