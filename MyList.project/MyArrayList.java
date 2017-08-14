
//提供ArrayList泛型类的实现，为避免和类库中的ArrayList相混淆，把此类叫MyArrayList

@SuppressWarnings("unchecked")                //讲真，我不知道这句话能陪我到什么时候

public class MyArrayList<AnyType> implements Iterable<AnyType>{
      private static final int DEFAULT_CAPACITY = 10;                    
      
      private int theSize;
      private AnyType[] theItem;

      public MyArrayList(){                   //构造函数，定义数组的初始容量为10
          doClear();
      }

      public void clear(){
          doClear();
      }

      public void doClear(){
           theSize = 0;
           ensureCapacity(DEFAULT_CAPACITY);
      }

      public int size(){
           return theSize;
      }
      public boolean isEmpty(){
           return size()==0 ;
      }
      public void trimToSize(){             //其实这个函数是为了节省空间的，把空的位置全部删除，只留有元素的位置（个人觉得为节省空间在add(int，AnyType)函数中做点文章就可以了，没必要写这个函数）
           ensureCapacity(size());
      }
        
      public AnyType get(int idx){                     //返回下标为idx的元素的值
           if(idx<0 || idx>=size())
                 throw new ArrayIndexOutOfBoundsException();            //异常类的使用
           return theItem[idx];
      }

      public AnyType set(int idx,AnyType newVal){              //设置下标为idx的元素的值，不过不太明白为什么要返回原来元素的值
           if(idx<0 || idx>size())
                 throw new ArrayIndexOutOfBoundsException();
           AnyType old = theItem[idx];
           theItem[idx] = newVal;
           return old;
      }

      public void ensureCapacity(int newCapacity){             //扩大数组的容量
           if(newCapacity<theSize)
                return;

           AnyType [] old = theItem;
           theItem = (AnyType[]) new Object[newCapacity];
           for(int i=0;i<size();i++){
                theItem[i] = old[i];
           }
      }

      public boolean add(AnyType x){
           add(size(),x);
           return true;
      }

      public void add(int idx,AnyType x){
           if(theItem.length==size())
                ensureCapacity(size() * 2 +1);                       //空间加的好大，用一个加一个不行么？
           for(int i=size()-1;i>=idx;i--){
                theItem[i+1] = theItem[i];
           }
           theItem[idx] = x;
           theSize++;
      }

      public AnyType remove(int idx){                //把下标为idx的元素删除，同样不明白为什么要返回被删除元素的值
           AnyType removedItem = theItem[idx];
           for(int i=idx;i<size()-1;i++){
                theItem[i] = theItem[i+1];
           } 
           theSize--;
           return removedItem;         
      }

  /*    public java.util.Iterator<AnyType> iterator(){            //迭代器，用来访问链表中元素的值，并进行操作（其实就是接口回调啦，等会哥教你怎么用...）
           return new ArrayListIterator();
      }

      private class ArrayListIterator implements java.util.Iterator<AnyType>{        //一个内部类，用来保存数组元素的位置，给了三个函数来操作数组哦，OK，这就有话可说了
           private int current =0;                 //首先，这个类的对象用来实现迭代器 iterator()；其次，声明内部类时，编译器会添加对外部类对象的一个隐式引用，说人话就是
                                                   //java的编译器真tm厉害
           public boolean hasNext(){
                 return (current < size());
           }

           public AnyType next(){
                 if(!hasNext())
                       throw new java.util.NoSuchElementException();
                 return theItem[current++];
           }

           public void remove(){
                 MyArrayList.this.remove(--current);
           }
      }           */

      public java.util.Iterator<AnyType> iterator(){              
           return new ArrayListIterator<AnyType>(this);
      }

      private static class ArrayListIterator<AnyType> implements java.util.Iterator<AnyType>{         //为什么要再写一遍呢，看看有什么不同的地方？
          private int current = 0;                                //OK，声明类的时候多了static,这其实是java里的嵌套类(多了单词static性质就变了)
          private MyArrayList<AnyType> theList;                //一个连接到MyArrayList的链

          public ArrayListIterator(MyArrayList<AnyType> list){       //构造函数的形式变啦！其实上文的内部类里也是有构造函数的，编译器会自动会生成的（java强大的编译器）
                theList = list;
          }

          public boolean hasNext(){
                return current<theList.size();
          }

          public AnyType next(){
                if(!hasNext()){
                     throw new ArrayIndexOutOfBoundsException();
                }
                return theList.theItem[current++];
          }

          public void remove(){
                theList.remove(--current);
          }
      }
                              //内部类和嵌套类，你喜欢哪个就用哪个喽！（个人感觉是，都喜欢! 噫噫噫，好花心...）
}










