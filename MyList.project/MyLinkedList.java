
//LinkedList类的具体实现，使用了双向链表，为区别类库中的，在此用MyLinkedList为名（俗套，无聊......） 

@SuppressWarnings("unchecked")             //不出所料

public class MyLinkedList<AnyType> implements Iterable<AnyType>{

     private static class Node<AnyType>{                             //嵌套类Node<AnyType>,生成节点  
          public Node(AnyType d,Node<AnyType> p,Node<AnyType> n){
               data = d;
               prev = p;
               next = n;
          }
          
          public AnyType data;                      //节点的内部数据成员。含数据，前节点，后节点
          public Node<AnyType> prev;
          public Node<AnyType> next;
     }

     public MyLinkedList(){
          doClear();
     }
    
     public void clear(){
          doClear();
     }

     private void doClear(){
          beginMaker = new Node<AnyType>(null,null,null);              //头结点和尾节点的初始化方法，基础的一定要记住，不要弄错，很难debug的
          endMaker = new Node<AnyType>(null,beginMaker,null);
          beginMaker.next = endMaker;

          theSize = 0;
          modCount ++;
     }

     public int size(){
          return theSize;
     }

     public boolean isEmpty(){
          return size() == 0;
     }

     public boolean add(AnyType x){
          add(size(),x);
          return true;
     }

     public void add(int idx,AnyType x){
          addBefore(getNode(idx,0,size()),x);
     }

     public AnyType get(int idx){
          return getNode(idx).data;
     }

     public AnyType set(int idx,AnyType newVal){
          Node<AnyType> p = getNode(idx);
          AnyType oldVal = p.data;
          p.data = newVal;
          return oldVal;
     }

     public AnyType remove(int idx){
          return remove(getNode(idx));
     }
 
     public void change(int a,int b){               //交换两个元素的函数
          if(a>b){
               int temp = a;
               a = b;
               b = temp;
          }
          changeNode(getNode(a),getNode(b));
     }

     public void contains(AnyType x){
          if(containsNode(x) == true )    System.out.println("元素在数组内");
          else    System.out.println("元素不在数组内");
     }

     private boolean containsNode(AnyType x){      //我不想说什么了，为什么数字大一点就不行，就判断不了。还约好了全部显示为不在数组内，好气
          Node<AnyType> q = beginMaker.next;
          if(q != endMaker){
              while(q.data != x){
                 q = q.next;
                 if(q==endMaker)   return false;
              }
              return true;
          }
          return false;
     }

     private void changeNode(Node<AnyType> p1,Node<AnyType> p2){              //交换元素的具体实现
          Node<AnyType> p2_next = p2.next;                                    //按照书上要求，通过调整链表（不是简单的直接交换元素）来实现
          Node<AnyType> p1_prev = p1.prev;                                    //开始想的比较简单，只能交换相邻元素,现在可以啦
          Node<AnyType> p2_prev = p2.prev;                                    //刚写的单链表也要调整一下喽
          p2.next.prev = p1;
          p2.prev.next = p1;
          p2.prev = p1.prev;
          p1.next.prev = p2;
          p2.next = p1.next;
          p1.next = p2_next;
          p1.prev = p2_prev;
          p1_prev.next = p2;
          
          
     }

     private void addBefore(Node<AnyType> p,AnyType x){              //私有函数，内部调用，外部不可见。在节点前添加节点
          Node<AnyType> newNode = new Node<AnyType>(x,p.prev,p);
          newNode.prev.next = newNode;
          p.prev = newNode;
          theSize ++;
          modCount ++;
     }

     private AnyType remove(Node<AnyType> p){                      //私有，供内部函数调用。移除节点
          p.prev.next = p.next;
          p.next.prev = p.prev;
          theSize --;
          modCount ++;
 
          return p.data;
     }

     private Node<AnyType> getNode(int idx){
          return getNode(idx,0,size()-1);
     }

     private Node<AnyType> getNode(int idx,int low,int upper){          //私有。取节点，若所取节点在前半部分，从开头遍历，反之从结尾遍历
          Node<AnyType> p;
         
          if(idx<low || idx >upper){
                throw new IndexOutOfBoundsException();
          }

          if(idx< size()/2){
                 p = beginMaker.next;
                 for(int i =0;i<idx;i++){
                      p= p.next;
                 }
          }
          else{
                 p= endMaker;
                 for(int i=size();i>idx;i--){
                      p= p.prev;
                 }
          }
          return p;
     }

     public java.util.Iterator<AnyType> iterator(){             //迭代器的生成，与前例类似返回一个新生成的引用（偶然想到，这其实就是刚在学习java时接触到的接口回调）
          return new LinkedListIterator();
     }
 
     private class LinkedListIterator implements java.util.Iterator<AnyType>{
          private int expectedModCount = modCount;              //在使用迭代器时就检测是否同步处理数据，相当于加了个锁，同步会报错java.util.ConcurrentModificationException()
          private Node<AnyType> current = beginMaker.next;      //保存当前位置
          private boolean okToRemove = false;                   //用来检测是否可调用此类中的remove()函数。满足：必须至少调用一次next()函数才能才能使用remove()函数
 
          public boolean hasNext(){
               return current != endMaker;
          }

          public AnyType next(){
               if(!hasNext())
                     throw new java.util.NoSuchElementException();                  //异常类名记一下，java.util.NoSuchElementException()
               if(expectedModCount != modCount)
                      throw new java.util.ConcurrentModificationException();        //java.util.ConcurrentModificationException()

               AnyType nextItem = current.data;
               okToRemove = true; 
               current = current.next;
               return nextItem;
          }

          public void remove(){
               if(expectedModCount != modCount)
                      throw new java.util.ConcurrentModificationException();
               if(!okToRemove)
                      throw new IllegalStateException();                        //抛出IllegalStateException()异常，不可remove()
            
               MyLinkedList.this.remove(current.prev);
               expectedModCount ++;
               okToRemove = false;
          }
     }

     private int theSize;                             //MyLinkedList类的数据成员。包含：大小，操作次数，头结点，尾节点
     private int modCount = 0;
     private Node<AnyType> beginMaker;
     private Node<AnyType> endMaker;
}                                          

         //除了了解到链表的编写和使用方法外，还认识到类的封装性。以前并不懂要怎么设置数据成员及成员函数的可见性，
        //现在稍微理解了一点。还有，最重要的一点，一定要多想多思考,大的框架和思路有了的话，细节就决定成败了，望共勉











