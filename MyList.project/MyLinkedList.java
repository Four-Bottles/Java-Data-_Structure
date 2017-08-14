
//LinkedList��ľ���ʵ�֣�ʹ����˫������Ϊ��������еģ��ڴ���MyLinkedListΪ�������ף�����......�� 

@SuppressWarnings("unchecked")             //��������

public class MyLinkedList<AnyType> implements Iterable<AnyType>{

     private static class Node<AnyType>{                             //Ƕ����Node<AnyType>,���ɽڵ�  
          public Node(AnyType d,Node<AnyType> p,Node<AnyType> n){
               data = d;
               prev = p;
               next = n;
          }
          
          public AnyType data;                      //�ڵ���ڲ����ݳ�Ա�������ݣ�ǰ�ڵ㣬��ڵ�
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
          beginMaker = new Node<AnyType>(null,null,null);              //ͷ����β�ڵ�ĳ�ʼ��������������һ��Ҫ��ס����ҪŪ������debug��
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
 
     public void change(int a,int b){               //��������Ԫ�صĺ���
          if(a>b){
               int temp = a;
               a = b;
               b = temp;
          }
          changeNode(getNode(a),getNode(b));
     }

     public void contains(AnyType x){
          if(containsNode(x) == true )    System.out.println("Ԫ����������");
          else    System.out.println("Ԫ�ز���������");
     }

     private boolean containsNode(AnyType x){      //�Ҳ���˵ʲô�ˣ�Ϊʲô���ִ�һ��Ͳ��У����жϲ��ˡ���Լ����ȫ����ʾΪ���������ڣ�����
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

     private void changeNode(Node<AnyType> p1,Node<AnyType> p2){              //����Ԫ�صľ���ʵ��
          Node<AnyType> p2_next = p2.next;                                    //��������Ҫ��ͨ�������������Ǽ򵥵�ֱ�ӽ���Ԫ�أ���ʵ��
          Node<AnyType> p1_prev = p1.prev;                                    //��ʼ��ıȽϼ򵥣�ֻ�ܽ�������Ԫ��,���ڿ�����
          Node<AnyType> p2_prev = p2.prev;                                    //��д�ĵ�����ҲҪ����һ���
          p2.next.prev = p1;
          p2.prev.next = p1;
          p2.prev = p1.prev;
          p1.next.prev = p2;
          p2.next = p1.next;
          p1.next = p2_next;
          p1.prev = p2_prev;
          p1_prev.next = p2;
          
          
     }

     private void addBefore(Node<AnyType> p,AnyType x){              //˽�к������ڲ����ã��ⲿ���ɼ����ڽڵ�ǰ��ӽڵ�
          Node<AnyType> newNode = new Node<AnyType>(x,p.prev,p);
          newNode.prev.next = newNode;
          p.prev = newNode;
          theSize ++;
          modCount ++;
     }

     private AnyType remove(Node<AnyType> p){                      //˽�У����ڲ��������á��Ƴ��ڵ�
          p.prev.next = p.next;
          p.next.prev = p.prev;
          theSize --;
          modCount ++;
 
          return p.data;
     }

     private Node<AnyType> getNode(int idx){
          return getNode(idx,0,size()-1);
     }

     private Node<AnyType> getNode(int idx,int low,int upper){          //˽�С�ȡ�ڵ㣬����ȡ�ڵ���ǰ�벿�֣��ӿ�ͷ��������֮�ӽ�β����
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

     public java.util.Iterator<AnyType> iterator(){             //�����������ɣ���ǰ�����Ʒ���һ�������ɵ����ã�żȻ�뵽������ʵ���Ǹ���ѧϰjavaʱ�Ӵ����Ľӿڻص���
          return new LinkedListIterator();
     }
 
     private class LinkedListIterator implements java.util.Iterator<AnyType>{
          private int expectedModCount = modCount;              //��ʹ�õ�����ʱ�ͼ���Ƿ�ͬ���������ݣ��൱�ڼ��˸�����ͬ���ᱨ��java.util.ConcurrentModificationException()
          private Node<AnyType> current = beginMaker.next;      //���浱ǰλ��
          private boolean okToRemove = false;                   //��������Ƿ�ɵ��ô����е�remove()���������㣺�������ٵ���һ��next()�������ܲ���ʹ��remove()����
 
          public boolean hasNext(){
               return current != endMaker;
          }

          public AnyType next(){
               if(!hasNext())
                     throw new java.util.NoSuchElementException();                  //�쳣������һ�£�java.util.NoSuchElementException()
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
                      throw new IllegalStateException();                        //�׳�IllegalStateException()�쳣������remove()
            
               MyLinkedList.this.remove(current.prev);
               expectedModCount ++;
               okToRemove = false;
          }
     }

     private int theSize;                             //MyLinkedList������ݳ�Ա����������С������������ͷ��㣬β�ڵ�
     private int modCount = 0;
     private Node<AnyType> beginMaker;
     private Node<AnyType> endMaker;
}                                          

         //�����˽⵽����ı�д��ʹ�÷����⣬����ʶ����ķ�װ�ԡ���ǰ������Ҫ��ô�������ݳ�Ա����Ա�����Ŀɼ��ԣ�
        //������΢�����һ�㡣���У�����Ҫ��һ�㣬һ��Ҫ�����˼��,��Ŀ�ܺ�˼·���˵Ļ���ϸ�ھ;����ɰ��ˣ�������











