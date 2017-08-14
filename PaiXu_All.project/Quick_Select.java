
import java.util.Random;

public class Quick_Select{

    public static void main(String []args){
        int number = 30;
        int number_ = number*10;
        int k = 3;
        Random rand = new Random();
        Integer []list = new Integer[number];
        for(int i=0;i<number;i++){
            list[i] = rand.nextInt(number_);
        }
        QuickSelect(list,k);
        for(Integer num : list){
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("第"+ k + "个最小元是" + list[k-1]);

        insertSort(list,0,list.length-1);
        System.out.println("排序后：");
        for(Integer num : list){
             System.out.print(num + " ");
        }
        System.out.println();   
    }

    private static <AnyType extends Comparable<? super AnyType>> void QuickSelect(AnyType [] a,int k){
        QuickSelect(a,0,a.length-1,k);
    }

    private static <AnyType extends Comparable<? super AnyType>> void QuickSelect(AnyType [] a,int left,int right,int k){  
 
        if(left+10<=right){
             AnyType center = Sort_All.medain3(a,left,right);
             int leftPos = left;
             int rightPos = right-1;
             while(true){
                 while(a[++leftPos].compareTo(center)<0){}
                 while(a[--rightPos].compareTo(center)>0){}
                 if(leftPos<rightPos){
                      Sort_All.swapReference(a,leftPos,rightPos);
                 }
                 else   break;
             }
             Sort_All.swapReference(a,leftPos,right-1);
             if(k<=leftPos){
                  QuickSelect(a,left,leftPos-1,k);
             }
             else  if(k>leftPos+1){
                       QuickSelect(a,leftPos+1,right,k);
                   }
        }
        else   insertSort(a,left,right);
    }

    private static <AnyType extends Comparable<? super AnyType>> void insertSort(AnyType [] a,int left,int right){
        int j;
        for(int i=left+1;i<=right;i++){
             AnyType temp = a[i];
             for(j=i;j>left && temp.compareTo(a[j-1])<0;j--){
                a[j] = a[j-1];
             }
             a[j] = temp;
        }
    } 
}