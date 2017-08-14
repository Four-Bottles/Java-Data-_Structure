import java.util.*;

/*  此处引入包java.util的目的是包中含：
    public interface Comparator<AnyType>{
       int compare(AnyType lhs,AnyType rhs);
    }
    引用此处接口实现的泛型是为了用到其中的compare方法，重写其方法以实现所需目的
*/

class DuiXiang{
    public static <AnyType> AnyType findMax(AnyType [] arr,Comparator<? super AnyType> cmp){
        int maxIndex = 0;
        for(int i=1;i<arr.length;i++){
            if(cmp.compare(arr[i],arr[maxIndex])>0){    //其实此处还用到了接口回调，貌似不重要，书上没提
                maxIndex = i;
            }
         }
         return arr[maxIndex];  
    }
}

class CaseInsensitiveCompare implements Comparator<String>{
    public int compare(String lhs,String rhs){
        return lhs.compareToIgnoreCase(rhs);    //compareToIngoreCase(String str)按字典序比较字符串
    }
}

public class TestProgram{
    public static void main(String [] args){
        String [] arr = {"Zeyrt","qwerf","asdfg"};
        System.out.println("Contents are: "+ DuiXiang.findMax(arr,new CaseInsensitiveCompare()));
    }
}






                 