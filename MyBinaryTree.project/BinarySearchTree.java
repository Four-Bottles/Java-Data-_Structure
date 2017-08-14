import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{   //������Ǹ���ν��ӵķ��;�ʽඣ�������
                                                                              //AnyType extends Copmarable<? super AnyType>    
     private static class BinaryNode<AnyType>{            //���ɽڵ��Ƕ���࣬�������캯���������ββ�ͬ
          BinaryNode(AnyType theElement){
               this(theElement,null,null);            //���棬��仰���Ǻܶ�������ȫƪ��û���õ�������캯�����տ��˴�ţ�ǵĲ��ͣ����������ˣ�
          }

          BinaryNode(AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt){
               element = theElement;
               left = lt;
               right = rt;
          }
          AnyType element;
          BinaryNode<AnyType> left;
          BinaryNode<AnyType> right;
     }

     private BinaryNode<AnyType> root;                 //������root���ڵ�

     public BinarySearchTree(){
          root = null;
     }

     public void makeEmpty(){
          root = null;
     }
     public boolean isEmpty(){
          return root==null;
     }

     public boolean contains(AnyType x){
          return contains(x,root);
     }

     public AnyType findMin(){
          if(isEmpty()){       
                 throw new IllegalStateException();        //����׳����쳣��ʵ��UnderflowException(),���ǣ���ô���붼��ͨ�������Ըĳ���һ���쳣
          }                                                //������Ҷ���������ʲô�쳣������Ŀ�ľ���Ҫ�ó����жϣ��Ǿ�������ඣ�
          return findMin(root).element;
     }

     public AnyType findMax(){
          if(isEmpty()){
                 throw new IllegalStateException();
          }
          return findMax(root).element;
    }

    public void insert(AnyType x){
          root = insert(x,root);
    }

    public void remove(AnyType x){
          root = remove(x,root);
    }

    public void printTree(){
          if(isEmpty())
              System.out.println("Empty tree.");
          else   
               printTree(root);
    }

    public void cengXuPrint(){
          if(root == null)    System.out.println("Empty Tree.");
          else      cengXuPrint(root);
    }

    private boolean contains(AnyType x,BinaryNode<AnyType> t){
          if(t==null)
               return false;
          
          int compareResult = x.compareTo(t.element);
          if(compareResult<0)     return contains(x,t.left);
              else if(compareResult>0)     return contains(x,t.right);
                       else    return true;
          
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
           if(t==null)    return null;
           if(t.left == null)   
                 return t;
           else
                 return findMin(t.left);
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
           if(t!=null){
                 while(t.right!=null)
                      t = t.right;
           }
           return t;
    }

    private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){           //����Ԫ�أ�������������
           if(t==null)
              return new BinaryNode(x,null,null);
           else{
              int compareResult = x.compareTo(t.element);
              if(compareResult<0)      t.left = insert(x,t.left);
              else     if(compareResult>0)      t.right = insert(x,t.right);
                       else   ;
              return t;   
           }
    }

    private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
           if(t==null)    return t;
           int compareResult = x.compareTo(t.element);
           if(compareResult<0)
                t.left = remove(x,t.left);
           else if(compareResult>0)
                t.right = remove(x,t.right);
                else  if(t.left!=null && t.right!=null){
                            t.element = findMin(t.right).element;
                            t.right = remove(t.element,t.right);
                      } 
                      else   t = (t.left!=null) ? t.left : t.right;
           return t; 
    }

    private void printTree(BinaryNode<AnyType> t){          //���������Ŀ���ǰ�Ԫ�ذ�˳���ӡ������
           if(t!=null){
                printTree(t.left);
                System.out.print(t.element + " ");
                printTree(t.right);
           }
    }

    private void cengXuPrint(BinaryNode<AnyType> t){     //����������õ��˷��ͣ��ⲻ�ϻ�ô��java������ô����������
           if(t!=null){
                 Queue<BinaryNode> queue = new LinkedList<>();          //�㷨����ĳλ����Ĳ����￴���ģ�����������ѧ����
                 queue.add(t);
                 while(!queue.isEmpty()){         
                      BinaryNode<AnyType> temp = queue.poll();
                      System.out.print(temp.element + "  ");
                      if(temp.left!=null)   queue.add(temp.left);
                      if(temp.right!=null)  queue.add(temp.right);
                 }
                 System.out.println();
           }
    }
}






