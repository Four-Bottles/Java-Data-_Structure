import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class Hash_1<AnyType>{

    public Hash_1(){                 //又用到了两个构造函数，OK！记住你了
         this(DEFAULT_TABLE_SIZE);
    }

    public Hash_1(int size){
         theList = new LinkedList[nextPrime(size)];      //不是说泛型不能使用数组么，为什么这里的theList可以？
         for(int i=0;i<theList.length;i++){
              theList[i] = new LinkedList<>();       //为数组每个成员实例化，这一步千万不要忘。前面只是把数组的大小实例化，而这一步是把每个链表实例化
         }
    }

    public void insert(AnyType x){                   //插入后，大小加一
         List<AnyType> whichList = theList[myhash(x)];
         if(!whichList.contains(x)){
             whichList.add(x);
             if(++currentCount > theList.length)
                  rehash();
         }
    }

    public void remove(AnyType x){                  //删除后，大小减一
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

    public void makeEmpty(){                              //记得把链表都清空......
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

    public void print_2(){                      //打印利用了List 里的迭代器，差点就忘了链表遍历还有这个特点
         for(List<AnyType> list : theList){
              Iterator iterator = list.iterator();
              while(iterator.hasNext())
                   System.out.print(iterator.next() + " ");
         }
         System.out.println();
    }

    private int currentCount;
    private List<AnyType>[] theList;                          //核心的内部成员。一个数组，数组的每个成员都是一个链表，这么多链表就构成了所谓的散列表
    private static final int DEFAULT_TABLE_SIZE = 7;          //定义数组的初始大小

    private int myhash(AnyType x){                    //哈希函数（这个可以随便写，怎么写都行，主要是开心啦......）
          int hashVal = x.hashCode();
          hashVal %= theList.length;
          if(hashVal<0)
                hashVal += theList.length;
          return hashVal;
    }

    private boolean isPrime(int x){             //这个和下面的一个函数目的是让链表数为素数，减少个别极端情况下的冲突        
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

    private void rehash(){                      //随时扩大链表数
          List<AnyType>[] oldList = theList;
          theList = new List[nextPrime(theList.length * 2 +1)];
          for(int i=0;i<theList.length;i++){                         //不要忘了为新链表实例化啊啊啊啊啊啊
               theList[i] = new LinkedList<>();
          }
    /*
          currentCount = 0;
          for(int i=0;i<oldList.length;i++){
               for(AnyType item : oldList[i])
                    insert(item);                    //本来以为直接把数组名一给它就行了，但是大小就没更新啊，所以还要一个个插入（个人觉得想法可以，我试试）
          }         
    */
          for(int i=0;i<oldList.length;i++){        //改了，没什么不好啊,当然可能我水平低，根本不知道哪不好
               theList[i] = oldList[i];
          }
    }

}