
//������ɢ�еľ���ʵ�֣��뷨�ܰ���Ҫ�ú���ĥ......

import java.util.Random;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class CuckooHash_3<AnyType>{

    public CuckooHash_3(HashFamily<? super AnyType> tf){     //���ȣ����캯��������һ��HashFamily �ӿڵĶ�����ʲô���أ���Ȼ��Ҫ�����Լ����ӿڵĶ���ȷʵ����ʵ������
        this(tf,DEFAULT_TABLE_SIZE);                         //���Դ˴���Ŀ�ľ������ýӿڻص���ʹ��HashFamily�ڲ��ĺ�����
    }                                                        //����ط���һ��Ҫ�������ýӿڡ�����벻��,������������......

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

    public boolean insert(AnyType x){               //�������̡���ӵ�һ���֣�OK,�����Ҿ��л���˵��
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

    public Iterator<AnyType> iterator(){            //��������ʵ��
        return new CuckooIterator();
    }

    private class CuckooIterator implements Iterator<AnyType>{

        private int current = 0;
        private int overCurrent = 0;
        private boolean flag = false;

        public boolean hasNext(){                        //����һ��ʵ����������ָ���overCurrent�������ӹ̶�hasNext()���ж�
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

    private AnyType[] array;             //���飬���Ĳ���
    private int currentSize;             //�ѱ������ݵĸ���
    private static final double MAX_LOAD = 0.4;            //װ�����ӣ�����Ѵ�Ķ����������ı�ֵ����������ޣ���������������
    private static final int ALLOWED_REHASHES = 1;    //��ɢ�еĽ���
    private static final int DEFAULT_TABLE_SIZE = 101;   
    private final HashFamily<? super AnyType> hashFunctions;        //Ϊ��ʹ�ù�ϣ�����壬��һ��������Ϊ�ڲ���Ա
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
                for(int j=0;j<hashNum;j++){                  //���ȣ���������������������еĹ�ϣ���������ҵ���λ���Ǻã�ֱ�Ӳ��룬����Ҳ���
                   pos = myhash(x,j);
                   if(array[pos]==null){
                      array[pos] = x;
                      currentSize++;
                      return true;
                   }
                }

                int i = 0;                                //�Ǿʹ����еĹ�ϣ����������ѡ��һ�������������ֲ���
                do{
                   pos = myhash(x,rand.nextInt(hashNum));    
                }while(pos == lastPos && i++<5);          //��֤��ѡ��λ�ò����ϴ�ѡ��λ�ó�ͻ����Ȼ�������������������ͬһ����������̫С�ˣ��Ǿͷ�������

                AnyType num = array[lastPos = pos];       //���ϴ�ѡ��λ�õ������������Ϊ�µĴ����������Ȼ�����α�����ϣ��������������滻�Ĺ������������һ�ٴ�
                array[pos] = x;                           //��û�н����Ļ�
                x = num;
            }
            if(++rehashes > ALLOWED_REHASHES){            //�Ǿ�ʵ����ɢ�У�����Ĵ�С���䣬ֻ�ѹ�ϣ�������һ�£����������rehash����������
                expand();                                 //�����ɢ�еĴ�������һ�Σ��ǾͰ�������������󣬼����ظ���������
                rehashes = 0;
            }       
            else
                rehash();
        }
    }
}