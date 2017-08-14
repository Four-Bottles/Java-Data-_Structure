 
                              //事实证明，桶排序就是为了装逼（还没有希尔排序快），其他的一点用没有。。。

import java.util.ArrayList;
import java.util.Random;
  

@SuppressWarnings("unchecked")

public class Radix_Sort{

    public static void radixSort(String []arr){
        radixSort(arr,arr[0].length());
    }

    public static void numberRadix(int []a){
        String str[] = toStr(a);
        int max = max(str);
        radixSort(str,max);
        for(int i=0;i<str.length;i++){
            a[i] = Integer.parseInt(str[i]);
        }
    }

    private static void radixSort(String []arr,int stringLen){
        final int BUCKETS = 256;
        ArrayList<String> list[] = new ArrayList[BUCKETS];
        
        for(int i=0;i<list.length;i++){
             list[i] = new ArrayList<String>();
        }
        for(int pos=stringLen-1;pos>=0;pos--){
             for(String str : arr){
                  list[str.charAt(pos)].add(str);
             }
             int index = 0;
             for(ArrayList<String> arrayList : list){
                  for(String str : arrayList){
                       arr[index++] = str;
                  } 
                  arrayList.clear();
             }
        }
    }

    public static void countingSort(int []arr){
        String []str = toStr(arr);
        int max = max(str);
        countingRadixSort(str,max);
        for(int i=0;i<str.length;i++){
             arr[i] = Integer.parseInt(str[i]);
        }
    }

    public static void countingRadixSort(String arr[]){
        countingRadixSort(arr,arr[0].length());
    }

    private static void countingRadixSort(String []arr,int stringLen){
        final int BUCKETS = 256;

        int N = arr.length;
        String []buffer = new String[N];
        String []in = arr;
        String []out = buffer;
        for(int pos=stringLen-1;pos>=0;pos--){
             int []count = new int[BUCKETS+1];
             for(int i=0;i<N;i++){
                  count[in[i].charAt(pos)+1]++;
             }
             for(int i=1;i<=BUCKETS;i++){
                 count[i] += count[i-1];
             }
             for(int i=0;i<N;i++){
                 out[count[in[i].charAt(pos)]++] = in[i];
             }
             String temp[] = in;
             in = out;
             out = temp;
        }
        if(stringLen % 2 == 1){
             for(int i=0;i<N;i++){
                 out[i] = in[i];
             }
        }
    }

    public static void difRadixSort(String []arr){
        int max = arr[0].length();
        for(int i=1;i<arr.length;i++){
            if(max < arr[i].length()){
                 max = arr[i].length();
            }
        }
        difRadixSort(arr,max);
    }

    public static void numberSort(int []a){
        String arr[] = toStr(a);
        int max = max(arr);
        difRadixSort(arr,max);
        for(int i=0;i<arr.length;i++){
            a[i] = Integer.parseInt(arr[i]);
        }
    }
 
    private static int max(String []arr){
        int max = arr[0].length();
        for(int i=1;i<arr.length;i++){
             if(max < arr[i].length()){
                  max = arr[i].length();
             }
        }
        for(int i=0;i<arr.length;i++){
             int len = arr[i].length();
             if(len!=max){
                  for(int j=0;j<max-len;j++){
                       arr[i] = "0"+ arr[i];
                  }
             }
        }
        return max;
    }

    private static String[] toStr(int a[]){
        String str[] = new String[a.length];
        for(int i=0;i<a.length;i++){
            str[i] = String.valueOf(a[i]);
        }
        return str;
    }

    private static void difRadixSort(String []arr,int maxLen){

        final int BUCKETS = 256;

        ArrayList<String> wordsByLength[] = new ArrayList[maxLen+1];
        ArrayList<String> list[] = new ArrayList[BUCKETS];

        for(int i=0;i<wordsByLength.length;i++){
             wordsByLength[i] = new ArrayList<String>();
        }

        for(int i=0;i<list.length;i++){
             list[i]=  new ArrayList<String>();
        }

        for(String str : arr){
             wordsByLength[str.length()].add(str);
        }

        int index = 0;
        for(ArrayList<String> arrayList : wordsByLength){
             for(String str : arrayList){
                 arr[index++] = str;
             }
        }

        int startingIndex = arr.length;
        for(int pos = maxLen-1;pos>=0;pos--){
             startingIndex -= wordsByLength[pos+1].size();

             for(int i=startingIndex;i<arr.length;i++){
                  list[arr[i].charAt(pos)].add(arr[i]);
             }

             index = startingIndex;
             for(ArrayList<String> arrayList : list){
                  for(String str : arrayList){
                      arr[index++] = str;
                  }
                  arrayList.clear();
             }
        }         
    }

    public static void shellSort(int []arr){

        int j;
        for(int gap=arr.length/2;gap>0;gap =gap/ 2){
             for(int i=gap;i<arr.length;i++){
                 int temp = arr[i];
                 for(j=i;j>=gap && temp<arr[j-gap];j -=gap){               //这个地方注意了，用的是temp。不要再犯傻啦！
                     arr[j] = arr[j-gap];

                 }
                 arr[j] = temp;
             }
        }
    }

    public static void main(String []args){
        String []arr_1 = {"346","167","176"};
        String []arr_2 = {"346","167","888","176"};
        int number =30;
        int number_ = number * 100;
        Random rand = new Random();

        int num[] = new int[number];
        int num_[] = new int[number];
        
        for(int i=0;i<number;i++){
            num[i] = rand.nextInt(number_);
            num_[i] = num[i];
        }

        radixSort(arr_1);
        countingRadixSort(arr_2);

        System.out.print("（定长字符串）基数排序：");
        for(String str : arr_1){
            System.out.print(str + " ");
        }
        System.out.println();   
        System.out.print("（定长字符串）计数基数排序：");
        for(String str : arr_2){
            System.out.print(str + " ");
        }
        System.out.println();   

        long t1 = System.currentTimeMillis();
        numberSort(num);   
        long t2 = System.currentTimeMillis();
        shellSort(num_);
        long t2_ = System.currentTimeMillis();
        
        System.out.println("数字的基数排序(ms)：" + (t2-t1));
        System.out.println("数字的希尔排序(ms)：" + (t2_-t2));
        for(int n : num){
           System.out.print(n + " ");
        }   
        System.out.println();     
        for(int n : num_){
           System.out.print(n + " ");
        }               
        System.out.println();   
        for(int i=0;i<number;i++){
            num[i] = rand.nextInt(number_);
            num_[i] = num[i];
        }
        long t3 = System.currentTimeMillis();
        numberRadix(num);   
        long t4 = System.currentTimeMillis(); 
        shellSort(num_);
        long t4_ = System.currentTimeMillis(); 
        
        System.out.println("数字的基数排序(ms)：" + (t4-t3));
        System.out.println("数字的希尔排序(ms)：" + (t4_-t4));
        for(int n : num){
            System.out.print(n + " ");
        }   
        System.out.println();  
        for(int n : num_){
           System.out.print(n + " ");
        }                
        System.out.println();    
        for(int i=0;i<number;i++){
            num[i] = rand.nextInt(number_);
            num_[i] = num[i];
        }
        long t5 = System.currentTimeMillis();
        countingSort(num);   
        long t6 = System.currentTimeMillis(); 
        shellSort(num_);
        long t6_ = System.currentTimeMillis(); 
        
        System.out.println("数字的基数排序(ms)：" + (t6-t5));
        System.out.println("数字的希尔排序(ms)：" + (t6_-t6));
        for(int n : num){
            System.out.print(n + " ");
        }   
        System.out.println();    
        for(int n : num_){
           System.out.print(n + " ");
        }           
        System.out.println();  
    }
}



