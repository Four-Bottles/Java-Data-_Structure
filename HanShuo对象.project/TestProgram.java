import java.util.*;

/*  �˴������java.util��Ŀ���ǰ��к���
    public interface Comparator<AnyType>{
       int compare(AnyType lhs,AnyType rhs);
    }
    ���ô˴��ӿ�ʵ�ֵķ�����Ϊ���õ����е�compare��������д�䷽����ʵ������Ŀ��
*/

class DuiXiang{
    public static <AnyType> AnyType findMax(AnyType [] arr,Comparator<? super AnyType> cmp){
        int maxIndex = 0;
        for(int i=1;i<arr.length;i++){
            if(cmp.compare(arr[i],arr[maxIndex])>0){    //��ʵ�˴����õ��˽ӿڻص���ò�Ʋ���Ҫ������û��
                maxIndex = i;
            }
         }
         return arr[maxIndex];  
    }
}

class CaseInsensitiveCompare implements Comparator<String>{
    public int compare(String lhs,String rhs){
        return lhs.compareToIgnoreCase(rhs);    //compareToIngoreCase(String str)���ֵ���Ƚ��ַ���
    }
}

public class TestProgram{
    public static void main(String [] args){
        String [] arr = {"Zeyrt","qwerf","asdfg"};
        System.out.println("Contents are: "+ DuiXiang.findMax(arr,new CaseInsensitiveCompare()));
    }
}






                 