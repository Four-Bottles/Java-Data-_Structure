
//二项队列的具体实现。



@SuppressWarnings("unchecked")

public class BinomialQueue<AnyType extends Comparable<? super AnyType>>{
    
    private Node<AnyType>[] theTree;
    private static final int TABLE_SIZE = 1;               //数组的初始长度
    private int currentSize;

    public BinomialQueue(){
        theTree = new Node[TABLE_SIZE];
        makeEmpty();
    }

    public BinomialQueue(AnyType x){
         this();
         theTree[0] = new Node<AnyType>(x);
         currentSize++;
    }

    public void insert(AnyType x){                      //插入的实现
        merge(new BinomialQueue<AnyType>(x));
    }

    public AnyType findMin(){                           //查找最小元素
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        int minIndex = findMind();
        return theTree[minIndex].element;
    }
 
    public void makeEmpty(){
        for(int i=0;i<theTree.length;i++){
             theTree[i] = null;
        }
        currentSize=0;
    }

    public boolean isEmpty(){
        return currentSize==0;
    }

    public int size(){
        return currentSize;
    }

    public void deleteMin(){                        //删除最小元素。想法是，找出最小元素所在的二项树，把除根节点的剩下所有节点构成一颗新队列。把原队列的此位置置空，

        if(isEmpty())                               //再和新生成的队列合并，即可得到所求
             throw new java.util.NoSuchElementException();

        int minIndex = findMind();
        Node<AnyType> deleteTree = theTree[minIndex].leftChild; 

        BinomialQueue<AnyType> deleteQueue = new BinomialQueue<>();
        deleteQueue.expandCap(minIndex+1);

        deleteQueue.currentSize = (1<<minIndex) - 1;

        for(int i=minIndex-1;i>=0;i--){
            deleteQueue.theTree[i] = deleteTree;
            deleteTree = deleteTree.nextSibling;
            deleteQueue.theTree[i].nextSibling = null;
        }

        theTree[minIndex] = null;
        currentSize -= (deleteQueue.currentSize + 1);

        merge(deleteQueue);

    }

    public void merge(BinomialQueue<AnyType> rt){                    //合并两个二项队列
        if(this==rt)
           return;

        currentSize += rt.currentSize;

        if(currentSize>capacity()){                                 //注意容量的控制，不要越界
            int maxLength = Math.max(theTree.length,rt.theTree.length);
            expandCap(maxLength+1);
        }    

        Node<AnyType> carry = null;
        for(int i=0,j=1;j<=currentSize;i++,j *= 2){            //这个地方注意界限的选取。还有下面这八种合并情况要记得
            Node<AnyType> t1 = theTree[i];
            Node<AnyType> t2 = i<rt.theTree.length ? rt.theTree[i] : null;

            int whichCase = t1==null ? 0 : 1;
            whichCase += t2==null ? 0 : 2;
            whichCase += carry==null ? 0 : 4;

            switch(whichCase){
                case 0:  
                case 1:  break;
                case 2:   
                    theTree[i] = t2;
                    rt.theTree[i] = null;
                    break;
                case 4:
                    theTree[i] = carry;
                    carry = null;
                    break;
                case 3:
                    carry = combineTrees(t1,t2);
                    theTree[i] = rt.theTree[i] = null;
                    break;
                case 5:
                    carry = combineTrees(t1,carry);
                    theTree[i] = null;
                    break;
                case 6: 
                    carry = combineTrees(t2,carry);
                    rt.theTree[i] = null;
                    break;
                case 7:
                    theTree[i] = carry;
                    carry = combineTrees(t1,t2);
                    rt.theTree[i] = null;
                    break;
            }
        }

        for(int i=0;i<rt.theTree.length;i++){
            rt.theTree[i] = null;
        }
        rt.currentSize = 0;
    }

    public void print(){                             //乱序打印（依次打印每个节点）
        if(isEmpty()){
             System.out.println("Empty.");
             return;
        }
        for(int i=0;i<theTree.length;i++){
             if(theTree[i]!=null){
                  printNode(theTree[i]);
             }
        }
        System.out.println();
    }

    public void printSort(){                         //排序打印（依次打印最小值、删除最小值、打印新的最小值......不要忘了把被删除的元素找回来）
        if(isEmpty()){
             System.out.println("Empty.");
             return;
        } 
        printSort(this);
        System.out.println();
    }

    private static class Node<AnyType>{

        AnyType element;
        Node<AnyType> nextSibling;
        Node<AnyType> leftChild;

        Node(AnyType x){
            this(x,null,null);
        }

        Node(AnyType x,Node<AnyType> left,Node<AnyType> next){
            element = x;
            leftChild = left; 
            nextSibling = next;
        }
    }

    private void printSort(BinomialQueue<AnyType> rt){          //为了不让依次排序后元素都被删除，这里我真的是费了好大功夫来补充被删除的元素。真是切切实实的体会到了
        BinomialQueue<AnyType> queue = new BinomialQueue<>();   
        int i =1;                                               //java中所谓的无传值无引用之说。你敢来我就敢改，而且让你回不去......
        while(!rt.isEmpty()){
             System.out.print(rt.findMin() + " ");
             if(i++ % 30==0)
                 System.out.println();
             queue.insert(rt.findMin());
             rt.deleteMin();
        }
        currentSize = queue.currentSize;
        for(int j=0;j<queue.theTree.length;j++){
             theTree[j] = queue.theTree[j];
        }
    }

    private void printNode(Node<AnyType> node){                 //打印每个二项树（节点）的算法
        if(node==null)
           return;
        System.out.print(node.element+" ");
        if(node.leftChild!=null)
           printNode(node.leftChild);
        if(node.nextSibling!=null)
           printNode(node.nextSibling);
    }

    private Node<AnyType> combineTrees(Node<AnyType> rt1,Node<AnyType> rt2){           //合并两个秩相同的二项树（最小节点为根节点）
        if(rt1.element.compareTo(rt2.element)>0)
            return combineTrees(rt2,rt1);
        rt2.nextSibling = rt1.leftChild;
        rt1.leftChild = rt2;
        return rt1;
    }

    private void expandCap(int size){
        if(size<=theTree.length){
             size++;
        }
        Node<AnyType>[] oldTree = theTree;
        theTree = new Node[size];
        
        for(int i=0;i<theTree.length;i++){
             theTree[i] = null;
        }
        for(int i=0;i<oldTree.length;i++){
             if(oldTree[i]!=null){
                 theTree[i] = oldTree[i];
             }
        }  
    }

    private int findMind(){
        int i=0;
        for(;theTree[i]==null;i++){
           ;
        }
        int minIndex;
        for(minIndex=i;i<theTree.length;i++){
           if(theTree[i]!=null && 
              theTree[minIndex].element.compareTo(theTree[i].element)>0)
                   minIndex = i;
        }
        return minIndex;
    }

    private int capacity(){                               //目前允许的最大容量
        return (1<<theTree.length)-1;
    }
}