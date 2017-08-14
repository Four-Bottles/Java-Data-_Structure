
                                         //堆排序是王者！！！堆排序是王者！！！堆排序是王者！！！（重要的事情说三遍......）
import java.util.Random;

@SuppressWarnings("unchecked")

public class Sort_All{

    public static <AnyType extends Comparable<? super AnyType>> void insertSort(AnyType [] a){
        int j;
        for(int i=1;i<a.length;i++){
            AnyType temp = a[i];
            for(j=i;j>0 && temp.compareTo(a[j-1])<0;j--){
                    a[j] = a[j-1];
            }
            a[j] = temp;
        }
    } 

    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType [] a){
        int j;
        for(int gap=a.length/2;gap>0;gap=gap/2){
            for(int i=gap;i<a.length;i++){
                AnyType temp = a[i];
                for(j=i;j>=gap && temp.compareTo(a[j-gap])<0;j-=gap){
                    a[j] = a[j-gap];
                }
                a[j] = temp;
            }
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType [] a){
        quickSort(a,0,a.length-1);
    }
  
    private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType [] a,int left,int right){
      if(right-left>15){
        AnyType center = medain3(a,left,right);
        int leftPos = left;
        int rightPos = right-1;
        while(true){
             while(a[++leftPos].compareTo(center)<0){}
             while(a[--rightPos].compareTo(center)>0){}
             if(leftPos<rightPos){
                 swapReference(a,leftPos,rightPos);
             }
             else  break;
        }
        swapReference(a,leftPos,right-1);
        quickSort(a,left,leftPos-1);
        quickSort(a,leftPos+1,right);
      }
      else  insertSort(a);
    }
 
    public static <AnyType extends Comparable<? super AnyType>> AnyType medain3(AnyType [] a,int left,int right){
        int center = (left+right)/2;
        if(a[left].compareTo(a[center])>0){
            swapReference(a,left,center);
        }
        if(a[right].compareTo(a[left])<0){
            swapReference(a,left,right);
        }
        if(a[right].compareTo(a[center])<0){
            swapReference(a,center,right);
        }
        swapReference(a,center,right-1);
        return a[right-1];
    }

    public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType [] a){
        for(int i=a.length/2;i>=0;i--){
            percDown(a,i,a.length);
        }
        for(int i=a.length-1;i>0;i--){
            swapReference(a,0,i);
            percDown(a,0,i);
        }
    }
 
    private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType [] a,int i,int length){
        swapReference(a,i,length-1);
        int child;
        AnyType temp = a[i];
        for(;leftChild(i)<length;i=child){
            child = leftChild(i);
            if(child!=length-1 && a[child].compareTo(a[child+1])<0){
                child++;
            }
            if(temp.compareTo(a[child])<0){
                a[i] = a[child];
            }
            else   break;
        }
        a[i] = temp;      
    }

    private static int leftChild(int i){
        return 2*i+1;
    }

    public static <AnyType extends Comparable<? super AnyType>> void swapReference(AnyType [] a,int x,int y){
        AnyType temp = a[y];
        a[y] = a[x];
        a[x] = temp;
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType [] a){
        AnyType tmpArray [] =(AnyType []) new Comparable[a.length];
        mergeSort(a,tmpArray,0,a.length-1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType [] a,AnyType []tmpArray,int left,int right){
        if(left<right){
            int center = (left+right)/2;
            mergeSort(a,tmpArray,left,center);
            mergeSort(a,tmpArray,center+1,right);
            merge(a,tmpArray,left,center+1,right);
        }
    }
    
    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType []a,AnyType []tmpArray,int leftPos,int rightPos,int rightEnd){
        int numElement = rightEnd - leftPos + 1,action = leftPos;
        int pos = leftPos;
        if(numElement>15){
            int leftEnd = rightPos-1;
            while(leftPos<=leftEnd && rightPos<=rightEnd){
                if(a[leftPos].compareTo(a[rightPos])<0){
                    tmpArray[pos++] = a[leftPos++];
                }
                else{
                    tmpArray[pos++] = a[rightPos++];
                }
            }
            while(leftPos<=leftEnd){
                tmpArray[pos++] = a[leftPos++];
            }
            while(rightPos<=rightEnd){
                tmpArray[pos++] = a[rightPos++];
            }
            for(int i=0;i<numElement;i++,rightEnd--){
                a[rightEnd] = tmpArray[rightEnd];
            }
        }
        else    insertSort(a);
    }
    
    public static void main(String []args){
        Random rand = new Random();
        int number = 30;
        int number_ = number*10;

                                                      // 插入排序。。。。。。

        Integer [] list = new Integer[number];
        for(int i=0;i<number;i++){
            list[i] = rand.nextInt(number_);
        }
        long t1 = System.currentTimeMillis();
        insertSort(list);
        long t2 = System.currentTimeMillis();
        for(Integer num : list){
            System.out.print(num+" ");
        }
        System.out.println("(这是插入排序......)");
        System.out.println("插入排序:(ms)"+ (t2 -t1));

                                                      //希尔排序。。。。。。

        Integer [] list_1 = new Integer[number];
        for(int i=0;i<number;i++){
            list_1[i] = rand.nextInt(number_);
        }
        long t3 = System.currentTimeMillis();
        shellSort(list_1);
        long t4 = System.currentTimeMillis();
        for(Integer num : list_1){
            System.out.print(num+" ");
        }
        System.out.println("(这是希尔排序......）");   
        System.out.println("希尔排序:(ms)"+ (t4 -t3));

                                                      //堆排序。。。。。。

        Integer [] list_2 = new Integer[number];
        for(int i=0;i<number;i++){
            list_2[i] = rand.nextInt(number_);
        }
        long t5 = System.currentTimeMillis();
        heapSort(list_2);
        long t6 = System.currentTimeMillis();
        for(Integer num : list_2){
            System.out.print(num+" ");
        }
        System.out.println("(这是堆排序......）");
        System.out.println("堆排序:(ms)"+ (t6 -t5));

                                                      //归并排序。。。。。。
        
        Integer [] list_3 = new Integer[number];
        for(int i=0;i<number;i++){
            list_3[i] = rand.nextInt(number_);
        }
        long t7 = System.currentTimeMillis();
        mergeSort(list_3);
        long t8 = System.currentTimeMillis();
        for(Integer num : list_3){
            System.out.print(num + " " );
        }
        System.out.println("(这是归并排序......)");
        System.out.println("归并排序:(ms)"+ (t8 -t7));

                                                    //快序排序。。。。。。

        Integer [] list_4 = new Integer[number];
        for(int i=0;i<number;i++){
            list_4[i] = rand.nextInt(number_);
        }
        long t9 = System.currentTimeMillis();
        quickSort(list_4);
        long t10 = System.currentTimeMillis();

        for(Integer num : list_4){
            System.out.print(num+" ");
        }
        System.out.println("(这是快速排序......）");
        System.out.println("快速排序:(ms)"+ (t10 -t9));

        System.out.println(number + "个数排序......");

        System.out.println("插入排序:(ms)"+ (t2 -t1));
        System.out.println("希尔排序:(ms)"+ (t4 -t3));
        System.out.println("堆排序:(ms)"+ (t6 -t5));
        System.out.println("归并排序:(ms)"+ (t8 -t7));
        System.out.println("快速排序:(ms)"+ (t10 -t9));
    }  
}