
//���ռ�һ�������·������һ���ڵ㵽������Ĳ������������ӵĽڵ��·����������Ҷ�ӽ�㡢ֻ��һ�����ӵĽڵ㣬��·������Ϊ�㣩

//�����������ʽ�ѣ�ÿ���ڵ�����ӵ���·������С���Ҷ��ӵ���·�������Ҹ��ڵ��ֵ��С���ľ���ʵ�֣����˼���һ����·����npl�⣬�ڵ�Node��������Ͷ�����һ��


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

    public void insert(AnyType x){                   //����ľ���ʵ�֣��뷨�ǣ���ԭ�Ѻ��¼ӵĽڵ���кϲ����¼ӵĽڵ㿴��ֻ��һ�����ڵ����ʽ��
        root = merge(root,new Node<AnyType>(x));
    }

    public void deleteMin(){
        if(isEmpty())
            return;
        else
            root = deleteMin(root);
    }
  
    public void print(){                          //�����ӡ
        if(isEmpty()){
             System.out.print("Empty.");
             return ;
        }
        print(root);
        System.out.println();
    }

    public void printSort(){                      //�����ӡ������ʽ����С���ѣ�������Ľ������С����
        if(isEmpty())
            System.out.println("Empty.");
        printSort(root);
        System.out.println();
    }

    private void printSort(Node<AnyType> rt){                //����д��Ŀ���ǣ���Ҫһ�������ӡ֮�󣬰�Ԫ�ض�ɾû�ˣ������뷨�ǣ���ɾ����һ��
        Node<AnyType> rt1 = new Node<AnyType>(rt.element);   //Ԫ�ص�ʱ��Ͱ����Ԫ�غ�ʣ�µ�Ԫ�ؽ��кϲ�����󸳸����ڵ㡣����֪��Ϊʲô�в�ͨ��
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

    private Node<AnyType> merge1(Node<AnyType> rt1,Node<AnyType> rt2){       //�ϲ�������ʽ�ѵľ���ʵ�֣��뷨�ǣ��Ѹ��ڵ�ֵ��С�ѵ��Ҷ��Ӻ���һ���Ѻϲ����õݹ�ʵ��
        if(rt1.left == null){                                                //���ݹ�ʵ����ʽ�ѵĵ�����
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

    private Node<AnyType> swapChild(Node<AnyType> rt){                        //�����������ӽڵ�
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