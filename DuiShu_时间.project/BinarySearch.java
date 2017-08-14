
public class BinarySearch{
    public static <AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType [] A, AnyType x){
        int low=0;
        int high=A.length -1;
        int mid =0 ;
        while(low<= high){
           mid=(low+high)/2;
           if(x.compareTo(A[mid]) >0)
              low = mid +1;
           else {
               if(x.compareTo(A[mid]) <0)
                  high = mid -1;
               else   return mid+1;
           }
         }
         return -1;
    }
}
        
        