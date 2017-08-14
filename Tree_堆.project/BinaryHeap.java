
//����ѵ�ʵ�֣�����C++���ݽṹѧ��С���ѡ�����ѣ�

@SuppressWarnings("unchecked")

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>{               //�����࣬����ֻҪǣ������compareTo���������Ķ�Ҫд�������ʽ
    
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

    public void insert(AnyType x){          //����ľ���ʵ�֡��뷨�ǣ���Ҫ�����ֵ�����������ɵ���Ҷ�У���Ŀǰ����currentSize����һλ��Ȼ�����κ͸��ڵ�Ƚ�
       if(currentSize == array.length-1){   //�����Ҫ�ƶ�λ�õĻ����Ͱ�Ҫ�����ֵ������array[0]��,���Ѹ��ڵ����ֵ����˿�λ�������¿�λ�����εݹ飬ֱ���ҵ�λ��......
           largeContains(array.length * 2 + 1);     //�����ǣ�����......
       }
       int hole = currentSize+1;
       for(array[0]=x;x.compareTo(array[hole/2])<0;hole = hole/2){
          array[hole] = array[hole/2];
       }
       array[hole] = x;
       currentSize++;
    }

    public void deleteMin(){            //ɾ����СԪ��ʵ�֡��뷨�Ͳ����࣬�ڸ��ڵ������λ�������ֵΪ��ǰ��������һ��Ԫ�ء����κ���С�ĺ��ӽڵ�Ƚϣ�ֱ������
       if(currentSize==1){              //�����ǣ�����......
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

    public void paixu_print(){              //����С���ѵ��ص���������������С���󣬴���ѣ��ɴ�С��������������ڵ㣬ɾ�����ڵ㣬ֱ��ȫ�����
       while(!isEmpty()){
           System.out.print(array[1] + " ");
           deleteMin();
       }
       System.out.println();
       System.out.println();
    }

    private void buildHeap(){           //��������������ֱ�ɶ���ѡ�������һ���λ�ÿ�ʼ�������ڵ��������������ÿ���ڵ�
        for(int i=currentSize/2;i>0;i--){
             putDown(i);
        }
    }

    private void putDown(int hole){              //���˵ľ���ʵ��
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

    private void allocateArray(int size){           //��ʼ������Ĵ�С
       array  = (AnyType[]) new Comparable[size+1];
    } 

   private void largeContains(int newSize){         //�������������
       AnyType[] oldArray = array; 
       allocateArray(newSize);
       currentSize = 0;
       for(int i=1;i<oldArray.length;i++){
           array[i] = oldArray[i];
           currentSize++;
       }
   }
}