
//二叉堆的实现（就是C++数据结构学的小根堆、大根堆）

@SuppressWarnings("unchecked")

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>{               //泛型类，好像只要牵扯到用compareTo（）函数的都要写成这个形式
    
    private AnyType[] array;
    private int currentSize;
    private static final int FIRST_SIZE = 15;
    
    public BinaryHeap(){
       this(FIRST_SIZE);
    }

    public BinaryHeap(int Size){
       allocateArray(Size);
       makeEmpty();
    }

    public BinaryHeap(AnyType[] item){
       this(item.length + 1);
       currentSize = 0;
       for(int i=0;i<item.length;i++){
          if(item[i] != null){
              array[++currentSize] = item[i];
          }
       }
       buildHeap();
    }

    public void makeEmpty(){
       currentSize = 0;
       for(int i=0;i<array.length;i++){
          array[i] = null;
       }
    }

    public boolean isEmpty(){
       return currentSize == 0;
    }

    public void insert(AnyType x){          //插入的具体实现。想法是：把要插入的值保存在新生成的树叶中，即目前数组currentSize的下一位，然后依次和父节点比较
       if(currentSize == array.length-1){   //如果需要移动位置的话，就把要插入的值保存在array[0]中,并把父节点的数值移入此空位，生成新空位，依次递归，直到找到位置......
           largeContains(array.length * 2 + 1);     //术语是，上滤......
       }
       int hole = currentSize+1;
       for(array[0]=x;x.compareTo(array[hole/2])<0;hole = hole/2){
          array[hole] = array[hole/2];
       }
       array[hole] = x;
       currentSize++;
    }

    public void deleteMin(){            //删除最小元的实现。想法和插入差不多，在根节点产生空位，补充的值为当前数组的最后一个元素。依次和最小的孩子节点比较，直到符合
       if(currentSize==1){              //术语是，下滤......
           array[currentSize] = null;
           currentSize--;
           return;
       }
       if(isEmpty()){
           throw new java.util.NoSuchElementException();
       }
       array[1] = array[currentSize];
       array[currentSize--] = null;
       putDown(1);
    }

    public void print(){                
       if(isEmpty())
           System.out.println("Empty.");
       else{
           for(int i=1;i<array.length;i++){
               if(array[i] != null)
                  System.out.print(array[i] + " ");
           }
       }
       System.out.println();
       System.out.println();
    }

    public void paixu_print(){              //利用小根堆的特点进行排序输出（由小到大，大根堆：由大到小）。依次输出根节点，删除根节点，直到全部输出
       while(!isEmpty()){
           System.out.print(array[1] + " ");
           deleteMin();
       }
       System.out.println();
       System.out.println();
    }

    private void buildHeap(){           //把乱序输入的数字变成二叉堆。从数组一半的位置开始、到根节点结束，依次下滤每个节点
        for(int i=currentSize/2;i>0;i--){
             putDown(i);
        }
    }

    private void putDown(int hole){              //下滤的具体实现
       array[0] = array[hole];
       int child;
       for(;hole*2 <= currentSize;hole = child){
          child = hole * 2;
          if(child<currentSize && array[child].compareTo(array[child+1])>0){
              child++;
          }
          if(array[0].compareTo(array[child])>0){
              array[hole] = array[child];
          }
          else{
             break;
             
          }
       }
       array[hole] = array[0];
    }

    private void allocateArray(int size){           //初始化数组的大小
       array  = (AnyType[]) new Comparable[size+1];
    } 

   private void largeContains(int newSize){         //扩大数组的容量
       AnyType[] oldArray = array; 
       allocateArray(newSize);
       currentSize = 0;
       for(int i=1;i<oldArray.length;i++){
           array[i] = oldArray[i];
           currentSize++;
       }
   }
}