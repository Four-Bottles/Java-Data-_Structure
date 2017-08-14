import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class Hash_1<AnyType>{

    public Hash_1(){                 //���õ����������캯����OK����ס����
         this(DEFAULT_TABLE_SIZE);
    }

    public Hash_1(int size){
         theList = new LinkedList[nextPrime(size)];      //����˵���Ͳ���ʹ������ô��Ϊʲô�����theList���ԣ�
         for(int i=0;i<theList.length;i++){
              theList[i] = new LinkedList<>();       //Ϊ����ÿ����Աʵ��������һ��ǧ��Ҫ����ǰ��ֻ�ǰ�����Ĵ�Сʵ����������һ���ǰ�ÿ������ʵ����
         }
    }

    public void insert(AnyType x){                   //����󣬴�С��һ
         List<AnyType> whichList = theList[myhash(x)];
         if(!whichList.contains(x)){
             whichList.add(x);
             if(++currentCount > theList.length)
                  rehash();
         }
    }

    public void remove(AnyType x){                  //ɾ���󣬴�С��һ
         List<AnyType> whichList = theList[myhash(x)];
         if(whichList.contains(x)){
              whichList.remove(x);
              currentCount--;
         }
    }

    public boolean contains(AnyType x){
         List<AnyType> whichList = theList[myhash(x)];
         return whichList.contains(x);
    }

    public void makeEmpty(){                              //�ǵð��������......
         currentCount = 0;
         for(int i=0;i<theList.length;i++){
               theList[i].clear();
         }
    }

    public void print_1(){
         for(List<AnyType> list : theList){
              for(AnyType item : list)
                    System.out.print(item + " ");
         }
         System.out.println();
    }

    public void print_2(){                      //��ӡ������List ��ĵ������������������������������ص�
         for(List<AnyType> list : theList){
              Iterator iterator = list.iterator();
              while(iterator.hasNext())
                   System.out.print(iterator.next() + " ");
         }
         System.out.println();
    }

    private int currentCount;
    private List<AnyType>[] theList;                          //���ĵ��ڲ���Ա��һ�����飬�����ÿ����Ա����һ��������ô������͹�������ν��ɢ�б�
    private static final int DEFAULT_TABLE_SIZE = 7;          //��������ĳ�ʼ��С

    private int myhash(AnyType x){                    //��ϣ����������������д����ôд���У���Ҫ�ǿ�����......��
          int hashVal = x.hashCode();
          hashVal %= theList.length;
          if(hashVal<0)
                hashVal += theList.length;
          return hashVal;
    }

    private boolean isPrime(int x){             //����������һ������Ŀ������������Ϊ���������ٸ��𼫶�����µĳ�ͻ        
          if(x==2 || x==3)
               return true;
          if(x==1 || x%2==0)
               return false;
          for(int i=3;i*i<=x;i+=2){
               if(x % i ==0) 
                     return false;
          }
          return true;
    }

    private int nextPrime(int x){
          if(x==0 || x==1 || x==2)
                return 2;
          if(x % 2 ==0)
                x++;
          while(!isPrime(x))
                x += 2;
          return x;
    }

    private void rehash(){                      //��ʱ����������
          List<AnyType>[] oldList = theList;
          theList = new List[nextPrime(theList.length * 2 +1)];
          for(int i=0;i<theList.length;i++){                         //��Ҫ����Ϊ������ʵ����������������
               theList[i] = new LinkedList<>();
          }
    /*
          currentCount = 0;
          for(int i=0;i<oldList.length;i++){
               for(AnyType item : oldList[i])
                    insert(item);                    //������Ϊֱ�Ӱ�������һ���������ˣ����Ǵ�С��û���°������Ի�Ҫһ�������루���˾����뷨���ԣ������ԣ�
          }         
    */
          for(int i=0;i<oldList.length;i++){        //���ˣ�ûʲô���ð�,��Ȼ������ˮƽ�ͣ�������֪���Ĳ���
               theList[i] = oldList[i];
          }
    }

}