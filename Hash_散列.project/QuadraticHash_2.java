
//平方探测法，解决散列表的冲突问题，这个小程序的调错时间不亚于那个什么S...矩阵。现在，终于可以运行了......

import java.util.Iterator;

@SuppressWarnings("unchecked")

public class QuadraticHash_2<AnyType> implements Iterable<AnyType>{

    public QuadraticHash_2(){                //再一次用到了两个构造函数的形式
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticHash_2(int size){
        allocateArray(size);
        makeEmpty();	
    }

    public void makeEmpty(){                //置空，把数组的每个元素设为null
        currentSize = 0;
        realSize = 0;
        for(int i=0;i<array.length;i++){
             array[i] = null;
        }
    }

    public void insert(AnyType x){          //插入，记得第一次要初始化
        int currentPos = findPos(x);
        if(isActive(currentPos))
            return;

        array[currentPos] = new HashEntry<>(x,true);
        realSize++;
        if(++currentSize > array.length / 2)         //保证每次冲突都能解决，所以有必要扩容。书上已证明，当元素个数小于数组长度的一半时，每次的平方探测都能插入成功
             rehash();
    }

    public void remove(AnyType x){            //书上之前提到的所谓的懒惰删除。删除元素只是标记了它被删除（In fact，每次 rehash 后，被删除元素都不会被保留下来）
        int currentPos = findPos(x);
        if(isActive(currentPos)){
            array[currentPos].isActive = false;
            realSize--;
        }
    }

    public boolean contains(AnyType x){
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    public void print(){
        if(currentSize==0){
            System.out.println("Empty.No Element.");
            return;
        }
        System.out.println("array的长度： " + array.length);
        for(int i=0;i<array.length;i++){
             if(isActive(i))
                  System.out.print(array[i].element + " ");
        }
        System.out.println();
    }

    public Iterator<AnyType> iterator(){
        return  new Hash_Iterator();
    }

    private static final int DEFAULT_TABLE_SIZE = 11;           //数组的初始长度
    private HashEntry<AnyType>[] array;                         //自定义类型的数组，学到了。这个技能简直了，拓宽了我认知界的大门
    private int currentSize;                                    //加了一个数据成员，含义：实际的元素数目，构造迭代器所用
    private int realSize;

    private class Hash_Iterator implements Iterator<AnyType> {    //用了高大上的迭代器。试了多种方法，想了好久，最后决定加一个表示实际的元素数目realSize，
                                                                  //然后成功运行啦，开心死了......
        public boolean hasNext(){
            return ( (overFound < realSize) && (current < array.length)) ;
        }

        public AnyType next(){
            if(!hasNext())
               throw new java.util.NoSuchElementException();
            if(isActive(current)){
               overFound++;
               flag = true;
               return array[current++].element;
            }
            else{
               current++;
               return next();
            }
        }

        public void remove(){
            if(flag){
               flag = false;
               QuadraticHash_2.this.remove(array[--current].element); 
            } 
        }

        private int current = 0;
        private int overFound = 0;
        private boolean flag = false;
        
    }

    private static class HashEntry<AnyType>{          //自定义类型。每个对象含两个public型的数据成员
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType x){
             this(x,true);
        }

        public HashEntry(AnyType element,boolean isActive){
             this.element = element;
             this.isActive = isActive;
        }
    }

    private boolean isActive(int current){
        return (array[current] != null && array[current].isActive);
    } 

    private int findPos(AnyType x){
        int currentPos = myhash(x);
        int offset = 1;
        while(array[currentPos] != null && !array[currentPos].element.equals(x)){       //书上说这句话的测试顺序很重要，不能改变（个人觉得不过是牵扯到判断顺序，有什么
             currentPos += offset;                                                      //不能改的，当然可能我水平比较低，看不出来区别）
             offset += 2;                                                             
             if(currentPos >= array.length){
                   currentPos -= array.length;
             }
        }
        return currentPos;
    }

    private int myhash(AnyType x){             //一个普通的哈希函数
        int hashVal = x.hashCode();
        hashVal %= array.length;
        while(hashVal<0)
           hashVal += array.length;
        return hashVal;
    }

    private void rehash(){                     //扩大容量用的
        HashEntry<AnyType>[] oldArray = array;
        allocateArray(nextPrime(oldArray.length * 2 + 1));
        currentSize = 0;
        realSize = 0;
        for(int i=0;i<oldArray.length;i++){
             if(oldArray[i] != null && oldArray[i].isActive)
                 insert(oldArray[i].element);
        }
    }

    private void allocateArray(int size){                 //初始化数组
        array = new HashEntry[nextPrime(size)];
    }

    private boolean isPrime(int num){
        if(num == 2 || num == 3)
            return true;
        if(num ==1 || num % 2 == 0)
            return false;
        for(int i=3;i * i <= num;i++){
            if(num % i == 0)
                return false;
        } 
        return true;
    }

    private int nextPrime(int num){
        if(num==0 || num==1 || num==2)
            return 2;
        else{
            if(num % 2 == 0)
               num ++;
            while(!isPrime(num))
               num += 2;
            return num;
        }
    }

    
}