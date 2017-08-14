import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{   //这就是那个所谓最复杂的泛型句式喽！看下面
                                                                              //AnyType extends Copmarable<? super AnyType>    
     private static class BinaryNode<AnyType>{            //生成节点的嵌套类，两个构造函数，但是形参不同
          BinaryNode(AnyType theElement){
               this(theElement,null,null);            //讲真，这句话不是很懂。而且全篇都没有用到这个构造函数（刚看了大牛们的博客，现在明白了）
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

     private BinaryNode<AnyType> root;                 //别忘了root根节点

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
                 throw new IllegalStateException();        //这个抛出的异常其实是UnderflowException(),但是，怎么编译都不通过，所以改成另一个异常
          }                                                //反正大家都看不懂是什么异常，反正目的就是要让程序中断，那就这样改喽！
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

    private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){           //插入元素，构造二叉查找树
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

    private void printTree(BinaryNode<AnyType> t){          //中序遍历（目的是把元素按顺序打印出来）
           if(t!=null){
                printTree(t.left);
                System.out.print(t.element + " ");
                printTree(t.right);
           }
    }

    private void cengXuPrint(BinaryNode<AnyType> t){     //层序遍历，用到了泛型（这不废话么，java泛型那么厉害）队列
           if(t!=null){
                 Queue<BinaryNode> queue = new LinkedList<>();          //算法是在某位大神的博客里看到的，真是厉害，学到了
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






