
//布谷鸟散列的具体实现，想法很棒，要好好琢磨......

import java.util.Random;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class CuckooHash_3<AnyType>{

    public CuckooHash_3(HashFamily<? super AnyType> tf){     //首先，构造函数传递了一个HashFamily 接口的对象，有什么用呢？当然不要怀疑自己，接口的对象确实不能实例化，
        this(tf,DEFAULT_TABLE_SIZE);                         //所以此处的目的就是利用接口回调来使用HashFamily内部的函数，
    }                                                        //这个地方，一定要熟练运用接口。真的想不到,还可以这样玩......

    public CuckooHash_3(HashFamily<? super AnyType> tf,int size){
        hashFunctions = tf;
        hashNum = hashFunctions.getNumberOfFunctions();
        hashFunctions.generateNewFunctions();
        allocateArray(nextPrime(size));
    }

    public void makeEmpty(){
        currentSize = 0;
        for(int i=0;i<array.length;i++){
            array[i] = null;
        }
    }

    public boolean insert(AnyType x){               //插入例程。最复杂的一部分，OK,这里我就有话可说了
        if(contains(x))
           return false;
        if(currentSize > array.length * MAX_LOAD)
           expand();
        return insertHelp(x); 
    }

    public boolean contains(AnyType x){
        return (findPos(x) != -1);
    }

    public boolean remove(AnyType x){
        if(contains(x)){
            int pos = findPos(x);
            array[pos] = null;
            currentSize--;
            return true;
        }
        return false;
    }

    public void print(){
        if(currentSize==0){
             System.out.println("Empty .");
             return;
        }
        else{
             for(int i=0;i<array.length;i++){
                  if(array[i] != null)
                      System.out.print(array[i] + " ");
                  if((i+1) % 20==0)
                      System.out.println();
             }
        }
        System.out.println();
    }

    public Iterator<AnyType> iterator(){            //迭代器的实现
        return new CuckooIterator();
    }

    private class CuckooIterator implements Iterator<AnyType>{

        private int current = 0;
        private int overCurrent = 0;
        private boolean flag = false;

        public boolean hasNext(){                        //加了一个实际输出的数字个数overCurrent，用来加固对hasNext()的判断
           return (current < array.length) && (overCurrent < currentSize);
        }
    
        public AnyType next(){
           if(!hasNext())
               throw new java.util.NoSuchElementException();
           if(array[current]!=null){
               overCurrent++;
               flag = true;
               return array[current++];
           }
           else{
               current++;
               return next();
           }
        }

        public void remove(){
           if(flag){
              flag = false;
              CuckooHash_3.this.remove(array[--current]);
           }
        }
    }

    private AnyType[] array;             //数组，核心部分
    private int currentSize;             //已保存内容的个数
    private static final double MAX_LOAD = 0.4;            //装填因子，如果已存的东西和容量的比值大于这个界限，就扩大数组容量
    private static final int ALLOWED_REHASHES = 1;    //再散列的界限
    private static final int DEFAULT_TABLE_SIZE = 101;   
    private final HashFamily<? super AnyType> hashFunctions;        //为了使用哈希函数族，用一个对象作为内部成员
    private final int hashNum;          

    private void expand(){
        rehash((int)(array.length / MAX_LOAD));
    }

    private void rehash(){
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int size){
        AnyType[] oldArray = array;
        allocateArray(nextPrime(size));
        for(int i=0;i<oldArray.length;i++){
            if(oldArray[i] != null)
                 insert(oldArray[i]);
        } 
    }

    private boolean isPrime(int num){
        if(num == 2 || num == 3){
            return true;
        }
        if(num==1 || num %2==0){
            return false;
        }
        for(int i=3;i*i <= num;i++){
            if(num % i ==0)
                return false;
        }
        return true;
    }

    private int nextPrime(int num){
        if(num==0 || num==1 || num==2){
            return 2;
        }
        if(num % 2 ==0){
            num++;
        }
        while(!isPrime(num)){
            num += 2;
        }
        return num;  
    }

    private void allocateArray(int size){
        array = (AnyType[])new Object[nextPrime(size)];
        makeEmpty();
    }

    private int myhash(AnyType x,int which){
        int hashVal =0;
        hashVal = hashFunctions.hash(x,which);
        hashVal %= array.length;
        if(hashVal<0)
            hashVal += array.length;
        return hashVal;
    }

    private int findPos(AnyType x){
        int pos;
        for(int i=0;i<hashNum;i++){
            pos = myhash(x,i);
            if(array[pos]!=null && array[pos].equals(x))
                return pos;
        }
        return -1;
    }    

    private boolean insertHelp(AnyType x){             
        int rehashes = 0;
        Random rand = new Random();
        final int COUNT_C = 100;

        while(true){
            int pos ;
            int lastPos = -1;

            for(int count=0;count<COUNT_C;count++){              
                for(int j=0;j<hashNum;j++){                  //首先，最基本的情况，如果在现有的哈希函数中能找到空位，那好，直接插入，如果找不到
                   pos = myhash(x,j);
                   if(array[pos]==null){
                      array[pos] = x;
                      currentSize++;
                      return true;
                   }
                }

                int i = 0;                                //那就从已有的哈希函数中任意选择一个函数，把数字插入
                do{
                   pos = myhash(x,rand.nextInt(hashNum));    
                }while(pos == lastPos && i++<5);          //保证新选的位置不和上次选的位置冲突，当然如果试了五次随机数都是同一个数（概率太小了）那就放弃尝试

                AnyType num = array[lastPos = pos];       //把上次选的位置的数提出来，作为新的待插入的数，然后依次遍历哈希函数。这个依次替换的过程如果进行了一百次
                array[pos] = x;                           //还没有结束的话
                x = num;
            }
            if(++rehashes > ALLOWED_REHASHES){            //那就实现再散列（数组的大小不变，只把哈希函数族变一下），即下面的rehash（）函数，
                expand();                                 //如果再散列的次数超过一次，那就把数组的容量扩大，继续重复上述操作
                rehashes = 0;
            }       
            else
                rehash();
        }
    }
}