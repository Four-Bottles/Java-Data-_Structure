
//������еľ���ʵ�֡�



@SuppressWarnings("unchecked")

public class BinomialQueue<AnyType extends Comparable<? super AnyType>>{
    
    private Node<AnyType>[] theTree;
    private static final int TABLE_SIZE = 1;               //����ĳ�ʼ����
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

    public void insert(AnyType x){                      //�����ʵ��
        merge(new BinomialQueue<AnyType>(x));
    }

    public AnyType findMin(){                           //������СԪ��
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

    public void deleteMin(){                        //ɾ����СԪ�ء��뷨�ǣ��ҳ���СԪ�����ڵĶ��������ѳ����ڵ��ʣ�����нڵ㹹��һ���¶��С���ԭ���еĴ�λ���ÿգ�

        if(isEmpty())                               //�ٺ������ɵĶ��кϲ������ɵõ�����
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

    public void merge(BinomialQueue<AnyType> rt){                    //�ϲ������������
        if(this==rt)
           return;

        currentSize += rt.currentSize;

        if(currentSize>capacity()){                                 //ע�������Ŀ��ƣ���ҪԽ��
            int maxLength = Math.max(theTree.length,rt.theTree.length);
            expandCap(maxLength+1);
        }    

        Node<AnyType> carry = null;
        for(int i=0,j=1;j<=currentSize;i++,j *= 2){            //����ط�ע����޵�ѡȡ��������������ֺϲ����Ҫ�ǵ�
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

    public void print(){                             //�����ӡ�����δ�ӡÿ���ڵ㣩
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

    public void printSort(){                         //�����ӡ�����δ�ӡ��Сֵ��ɾ����Сֵ����ӡ�µ���Сֵ......��Ҫ���˰ѱ�ɾ����Ԫ���һ�����
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

    private void printSort(BinomialQueue<AnyType> rt){          //Ϊ�˲������������Ԫ�ض���ɾ��������������Ƿ��˺ô󹦷������䱻ɾ����Ԫ�ء���������ʵʵ����ᵽ��
        BinomialQueue<AnyType> queue = new BinomialQueue<>();   
        int i =1;                                               //java����ν���޴�ֵ������֮˵��������Ҿ͸Ҹģ���������ز�ȥ......
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

    private void printNode(Node<AnyType> node){                 //��ӡÿ�����������ڵ㣩���㷨
        if(node==null)
           return;
        System.out.print(node.element+" ");
        if(node.leftChild!=null)
           printNode(node.leftChild);
        if(node.nextSibling!=null)
           printNode(node.nextSibling);
    }

    private Node<AnyType> combineTrees(Node<AnyType> rt1,Node<AnyType> rt2){           //�ϲ���������ͬ�Ķ���������С�ڵ�Ϊ���ڵ㣩
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

    private int capacity(){                               //Ŀǰ������������
        return (1<<theTree.length)-1;
    }
}