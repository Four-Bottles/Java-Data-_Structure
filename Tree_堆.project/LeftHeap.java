
//先普及一个概念：零路径长，一个节点到其最近的不具有两个儿子的节点的路径长（所以叶子结点、只有一个儿子的节点，零路径长都为零）

//这个程序是左式堆（每个节点左儿子的零路径长不小于右儿子的零路径长，且根节点的值最小）的具体实现，除了加了一个零路径长npl外，节点Node类的声明和二叉树一样


@SuppressWarnings("unchecked")

public class LeftHeap<AnyType extends Comparable<? super AnyType>>{

   private static class Node<AnyType>{

        Node(AnyType x){
            this(x,null,null);
        }

        Node(AnyType x,Node<AnyType> lt,Node<AnyType> rt){
            element = x;
            left = lt;
            right = rt;
            npl = 0;
        }

        AnyType element;
        int npl;
        Node<AnyType> left;
        Node<AnyType> right;
         
    }

    private Node<AnyType> root;

    public LeftHeap(){
        root = null;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void makeEmpty(){
        root = null;
    }
 
    public void merge(LeftHeap<AnyType> heap){
        if(this==heap)
             return;
        root = merge(root,heap.root);
        heap.root = null;
    }

    public void insert(AnyType x){                   //插入的具体实现，想法是：把原堆和新加的节点进行合并，新加的节点看成只有一个根节点的左式堆
        root = merge(root,new Node<AnyType>(x));
    }

    public void deleteMin(){
        if(isEmpty())
            return;
        else
            root = deleteMin(root);
    }
  
    public void print(){                          //乱序打印
        if(isEmpty()){
             System.out.print("Empty.");
             return ;
        }
        print(root);
        System.out.println();
    }

    public void printSort(){                      //排序打印。因左式堆是小根堆，故排序的结果是由小到大
        if(isEmpty())
            System.out.println("Empty.");
        printSort(root);
        System.out.println();
    }

    private void printSort(Node<AnyType> rt){                //这样写的目的是：不要一次排序打印之后，把元素都删没了（初级想法是，在删除第一个
        Node<AnyType> rt1 = new Node<AnyType>(rt.element);   //元素的时候就把这个元素和剩下的元素进行合并，最后赋给根节点。但不知道为什么行不通）
        boolean flag = false;
        while(rt!=null){
            System.out.print(rt.element + " ");
            if(flag){
                rt1  = insert(rt.element,rt1);
            }
            rt = deleteMin(rt);
            flag = true;
        }
        root = rt1;
    }   

    private Node<AnyType> insert(AnyType x,Node<AnyType> rt){
        rt = merge(new Node<>(x),rt);
        return rt;
    }

    private Node<AnyType> merge(Node<AnyType> rt1,Node<AnyType> rt2){
        if(rt1==null && rt2==null)
             return null;
        if(rt1 == null)
             return rt2;
        if(rt2 == null)
             return rt1;
        if(rt1.element.compareTo(rt2.element) >0)
             return merge1(rt2,rt1);
        else
             return merge1(rt1,rt2);
    }

    private void print(Node<AnyType> rt){
        if(rt==null)
            return;
        print(rt.left);
        System.out.print(rt.element + " ");
        print(rt.right);
    }

    private Node<AnyType> deleteMin(Node<AnyType> rt){
        if(rt==null)
            throw new java.util.NoSuchElementException();
        else{
            rt = merge(rt.left,rt.right);
            return rt;
        }
    }

    private Node<AnyType> merge1(Node<AnyType> rt1,Node<AnyType> rt2){       //合并两个左式堆的具体实现，想法是：把根节点值较小堆的右儿子和另一个堆合并，用递归实现
        if(rt1.left == null){                                                //并递归实现左式堆的调整。
             rt1.left = rt2;
             return rt1;
        }
        else{
             rt1.right = merge(rt1.right,rt2);
             if(rt1.left.npl < rt1.right.npl){
                 rt1 = swapChild(rt1);
             }
             rt1.npl = rt1.right.npl + 1;
        }
        return rt1;
         
    }

    private Node<AnyType> swapChild(Node<AnyType> rt){                        //交换两个孩子节点
        if(rt == null)
            return rt;
        if(rt.left == null && rt.left == null)
            return rt;
        if(rt.left == null){
            rt.left = rt.right;
            return rt;
        }
        if(rt.right == null) {
            rt.right = rt.left;
            return rt;
        }   
        else{
            Node<AnyType> tmp = rt.left;
            rt.left = rt.right;
            rt.right = tmp;
            return rt;
        }
    }

    

}