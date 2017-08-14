import java.util.Queue;
import java.util.LinkedList;

@SuppressWarnings("unchecked")           //��Ը��һֱ������ô��������˵�ã��Ҳ�Ը�⡣���ҵ������Ķ����Ͳ������ˡ���

public class AVL_Tree<AnyType extends Comparable<? super AnyType>>{      //����÷�Ҫ���£�����<AnyType extends Comparable<? super AnyType>>

    private static class AvlNode<AnyType>{                 //���õ��ˣ�Ƕ����AvlNode<AnyType>
         AvlNode(AnyType theElement){
              this(theElement,null,null);
         }

         AvlNode(AnyType theElement,AvlNode<AnyType> lt,AvlNode<AnyType> rt){
              element = theElement;
              left = lt;
              right = rt;
              height = 0;
         }

         AvlNode<AnyType> left;
         AvlNode<AnyType> right;
         AnyType element;
         int height;                           //���ظ߶�ֵ����Ҫ�����жϡ���ƽ�������
    }

    private AvlNode<AnyType> root;             //���������ӣ���Ҫ���˸��ڵ�

    public AVL_Tree(){
         root = null;
    }

    public void makeEmpty(){
         root=null;
    }

    public void insert(AnyType x){
         root = insert(x,root);
    }   

    public void remove(AnyType x){
         if(contains(x))
               root = remove(x,root);
         else System.out.println("Ԫ�ز��ڣ�����ɾ��");
    }
 
    public boolean contains(AnyType x){
         return contains(x,root);
    }

    public void printTree(){
         if(root==null)   System.out.println("��Ϊ�գ����ܴ�ӡ");
         else    printTree(root);
    }

    public void cengXuPrint(){
         if(root==null)   System.out.println("Empty Tree.");
         else  cengXuPrint(root);
    }

    private void cengXuPrint(AvlNode<AnyType> t){       //�������������ȴ�ӡ����Ԫ�أ�����Ȼûʲô���ã��������ᵽ�ˣ����Ҿ���Ϊ���ѵ�дһ��
         if(root!=null){                                //�ö���ʵ�ֵģ��Ƚ��ȳ���FIFO��
             Queue<AvlNode> queue = new LinkedList<>();
             queue.add(root);
             while(!queue.isEmpty()){
                 AvlNode<AnyType> temp = queue.poll();
                 System.out.print(temp.element + "  ");
                 if(temp.left!=null)   queue.add(temp.left);
                 if(temp.right!=null)  queue.add(temp.right);
            }
         }
    }

    private boolean contains(AnyType x,AvlNode<AnyType> t){
         if(t==null) return false;
         else{
             int compareResult = x.compareTo(t.element);
              if(compareResult > 0)  return contains(x,t.right);
              else   if(compareResult < 0)    return contains(x,t.left);
                     else   return true;
         }
    } 

    private int height(AvlNode<AnyType> t){        //�����Ҫ����������ͷǿ����ģ�����߶�ֵ�ĸ�������ÿ�β��롢ƽ�����Ĺ�����ʵ�ֵ�
         return (t==null) ? -1 : t.height;
    }

    private AvlNode<AnyType> insert(AnyType x,AvlNode<AnyType> t){
         if(t==null)
              return new AvlNode<>(x,null,null);
         else{
             int compareResult = x.compareTo(t.element);
             if(compareResult >0)   
                  t.right = insert(x,t.right);
             else  if(compareResult <0)
                         t.left = insert(x,t.left);
                   else ;
         }
         return balance(t);
    }
 
    private static final int ALLOWED_IMBALANCE = 1;                //��̬����
 
    private AvlNode<AnyType> balance(AvlNode<AnyType> t){       //ƽ������ʵ�֣����ֵ���ת������˫��ת������˫��ת�������������ε���תʵ�ֵ�
         if(t==null)    
               return t;
         else{
             if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE){
                   if(height(t.left.left) >= height(t.left.right)){           
                        t = rotateLeft(t);
                   }
                   else    t = doubleLeft(t);
             }
             else if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE){
                        if(height(t.right.right) >= height(t.right.left)){
                             t = rotateRight(t);
                        }
                        else   t = doubleRight(t);
                  }
         }
         
         t.height = Math.max(height(t.left),height(t.right)) + 1;
         return t;
    }

    private AvlNode<AnyType> rotateLeft(AvlNode<AnyType> k2){
         AvlNode<AnyType> k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
         k1.height = Math.max(height(k1.left),k2.height) + 1;
         return k1;
    }

    private AvlNode<AnyType> rotateRight(AvlNode<AnyType> k2){
         AvlNode<AnyType> k1 = k2.right;
         k2.right = k1.left;
         k1.left = k2;
         k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
         k1.height = Math.max(height(k1.right),k2.height) + 1;
         return k1;
    }

    private AvlNode<AnyType> doubleLeft(AvlNode<AnyType> k3){
         k3.left = rotateRight(k3.left);
         return rotateLeft(k3);
    }

    private AvlNode<AnyType> doubleRight(AvlNode<AnyType> k3){
         k3.right = rotateLeft(k3.right);
         return rotateRight(k3);
    }
    
    private AvlNode<AnyType> remove(AnyType x,AvlNode<AnyType> t){
         if(t==null)
               return t;
         else{
              int compareResult = x.compareTo(t.element);
  
              if(compareResult > 0)
                    t.right = remove(x,t.right);
              else  if(compareResult < 0)
                          t.left = remove(x,t.left);
                    else{
                        if(t.left != null && t.right !=null){        //ɾ����ʵ�֡�����ɾ���Ľڵ�����������ʱ��ȡ������������Сֵ�Ľڵ���汻ɾ���ڵ�
                            t.element = findMin(t.right).element;
                            t.right = remove(t.element,t.right);   
                        }
                        else  t = (t.left!=null) ? t.left : t.right;
                    }
              return balance(t);
         }
    }                         //�����Ĳ����ɾ�������󣬶���Ҫ����ƽ����

    private AvlNode<AnyType> findMin(AvlNode<AnyType> t){
         if(t==null)    return null;
         if(t.left == null)    
             return t;
         else 
             return findMin(t.left);
    }
   
    private void printTree(AvlNode<AnyType> t){
         if(t!=null){
             printTree(t.left);
             System.out.print(t.element + "  ");
             printTree(t.right);
         }
    }
}




