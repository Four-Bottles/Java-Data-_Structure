
//ƽ��̽�ⷨ�����ɢ�б�ĳ�ͻ���⣬���С����ĵ���ʱ�䲻�����Ǹ�ʲôS...�������ڣ����ڿ���������......

import java.util.Iterator;

@SuppressWarnings("unchecked")

public class QuadraticHash_2<AnyType> implements Iterable<AnyType>{

    public QuadraticHash_2(){                //��һ���õ����������캯������ʽ
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticHash_2(int size){
        allocateArray(size);
        makeEmpty();	
    }

    public void makeEmpty(){                //�ÿգ��������ÿ��Ԫ����Ϊnull
        currentSize = 0;
        realSize = 0;
        for(int i=0;i<array.length;i++){
             array[i] = null;
        }
    }

    public void insert(AnyType x){          //���룬�ǵõ�һ��Ҫ��ʼ��
        int currentPos = findPos(x);
        if(isActive(currentPos))
            return;

        array[currentPos] = new HashEntry<>(x,true);
        realSize++;
        if(++currentSize > array.length / 2)         //��֤ÿ�γ�ͻ���ܽ���������б�Ҫ���ݡ�������֤������Ԫ�ظ���С�����鳤�ȵ�һ��ʱ��ÿ�ε�ƽ��̽�ⶼ�ܲ���ɹ�
             rehash();
    }

    public void remove(AnyType x){            //����֮ǰ�ᵽ����ν������ɾ����ɾ��Ԫ��ֻ�Ǳ��������ɾ����In fact��ÿ�� rehash �󣬱�ɾ��Ԫ�ض����ᱻ����������
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
        System.out.println("array�ĳ��ȣ� " + array.length);
        for(int i=0;i<array.length;i++){
             if(isActive(i))
                  System.out.print(array[i].element + " ");
        }
        System.out.println();
    }

    public Iterator<AnyType> iterator(){
        return  new Hash_Iterator();
    }

    private static final int DEFAULT_TABLE_SIZE = 11;           //����ĳ�ʼ����
    private HashEntry<AnyType>[] array;                         //�Զ������͵����飬ѧ���ˡ�������ܼ�ֱ�ˣ��ؿ�������֪��Ĵ���
    private int currentSize;                                    //����һ�����ݳ�Ա�����壺ʵ�ʵ�Ԫ����Ŀ���������������
    private int realSize;

    private class Hash_Iterator implements Iterator<AnyType> {    //���˸ߴ��ϵĵ����������˶��ַ��������˺þã���������һ����ʾʵ�ʵ�Ԫ����ĿrealSize��
                                                                  //Ȼ��ɹ�����������������......
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

    private static class HashEntry<AnyType>{          //�Զ������͡�ÿ����������public�͵����ݳ�Ա
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
        while(array[currentPos] != null && !array[currentPos].element.equals(x)){       //����˵��仰�Ĳ���˳�����Ҫ�����ܸı䣨���˾��ò�����ǣ�����ж�˳����ʲô
             currentPos += offset;                                                      //���ܸĵģ���Ȼ������ˮƽ�Ƚϵͣ�������������
             offset += 2;                                                             
             if(currentPos >= array.length){
                   currentPos -= array.length;
             }
        }
        return currentPos;
    }

    private int myhash(AnyType x){             //һ����ͨ�Ĺ�ϣ����
        int hashVal = x.hashCode();
        hashVal %= array.length;
        while(hashVal<0)
           hashVal += array.length;
        return hashVal;
    }

    private void rehash(){                     //���������õ�
        HashEntry<AnyType>[] oldArray = array;
        allocateArray(nextPrime(oldArray.length * 2 + 1));
        currentSize = 0;
        realSize = 0;
        for(int i=0;i<oldArray.length;i++){
             if(oldArray[i] != null && oldArray[i].isActive)
                 insert(oldArray[i].element);
        }
    }

    private void allocateArray(int size){                 //��ʼ������
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