
//�ṩArrayList�������ʵ�֣�Ϊ���������е�ArrayList��������Ѵ����MyArrayList

@SuppressWarnings("unchecked")                //���棬�Ҳ�֪����仰�����ҵ�ʲôʱ��

public class MyArrayList<AnyType> implements Iterable<AnyType>{
      private static final int DEFAULT_CAPACITY = 10;                    
      
      private int theSize;
      private AnyType[] theItem;

      public MyArrayList(){                   //���캯������������ĳ�ʼ����Ϊ10
          doClear();
      }

      public void clear(){
          doClear();
      }

      public void doClear(){
           theSize = 0;
           ensureCapacity(DEFAULT_CAPACITY);
      }

      public int size(){
           return theSize;
      }
      public boolean isEmpty(){
           return size()==0 ;
      }
      public void trimToSize(){             //��ʵ���������Ϊ�˽�ʡ�ռ�ģ��ѿյ�λ��ȫ��ɾ����ֻ����Ԫ�ص�λ�ã����˾���Ϊ��ʡ�ռ���add(int��AnyType)�������������¾Ϳ����ˣ�û��Ҫд���������
           ensureCapacity(size());
      }
        
      public AnyType get(int idx){                     //�����±�Ϊidx��Ԫ�ص�ֵ
           if(idx<0 || idx>=size())
                 throw new ArrayIndexOutOfBoundsException();            //�쳣���ʹ��
           return theItem[idx];
      }

      public AnyType set(int idx,AnyType newVal){              //�����±�Ϊidx��Ԫ�ص�ֵ��������̫����ΪʲôҪ����ԭ��Ԫ�ص�ֵ
           if(idx<0 || idx>size())
                 throw new ArrayIndexOutOfBoundsException();
           AnyType old = theItem[idx];
           theItem[idx] = newVal;
           return old;
      }

      public void ensureCapacity(int newCapacity){             //�������������
           if(newCapacity<theSize)
                return;

           AnyType [] old = theItem;
           theItem = (AnyType[]) new Object[newCapacity];
           for(int i=0;i<size();i++){
                theItem[i] = old[i];
           }
      }

      public boolean add(AnyType x){
           add(size(),x);
           return true;
      }

      public void add(int idx,AnyType x){
           if(theItem.length==size())
                ensureCapacity(size() * 2 +1);                       //�ռ�ӵĺô���һ����һ������ô��
           for(int i=size()-1;i>=idx;i--){
                theItem[i+1] = theItem[i];
           }
           theItem[idx] = x;
           theSize++;
      }

      public AnyType remove(int idx){                //���±�Ϊidx��Ԫ��ɾ����ͬ��������ΪʲôҪ���ر�ɾ��Ԫ�ص�ֵ
           AnyType removedItem = theItem[idx];
           for(int i=idx;i<size()-1;i++){
                theItem[i] = theItem[i+1];
           } 
           theSize--;
           return removedItem;         
      }

  /*    public java.util.Iterator<AnyType> iterator(){            //����������������������Ԫ�ص�ֵ�������в�������ʵ���ǽӿڻص������Ȼ�������ô��...��
           return new ArrayListIterator();
      }

      private class ArrayListIterator implements java.util.Iterator<AnyType>{        //һ���ڲ��࣬������������Ԫ�ص�λ�ã�����������������������Ŷ��OK������л���˵��
           private int current =0;                 //���ȣ������Ķ�������ʵ�ֵ����� iterator()����Σ������ڲ���ʱ������������Ӷ��ⲿ������һ����ʽ���ã�˵�˻�����
                                                   //java�ı�������tm����
           public boolean hasNext(){
                 return (current < size());
           }

           public AnyType next(){
                 if(!hasNext())
                       throw new java.util.NoSuchElementException();
                 return theItem[current++];
           }

           public void remove(){
                 MyArrayList.this.remove(--current);
           }
      }           */

      public java.util.Iterator<AnyType> iterator(){              
           return new ArrayListIterator<AnyType>(this);
      }

      private static class ArrayListIterator<AnyType> implements java.util.Iterator<AnyType>{         //ΪʲôҪ��дһ���أ�������ʲô��ͬ�ĵط���
          private int current = 0;                                //OK���������ʱ�����static,����ʵ��java���Ƕ����(���˵���static���ʾͱ���)
          private MyArrayList<AnyType> theList;                //һ�����ӵ�MyArrayList����

          public ArrayListIterator(MyArrayList<AnyType> list){       //���캯������ʽ��������ʵ���ĵ��ڲ�����Ҳ���й��캯���ģ����������Զ������ɵģ�javaǿ��ı�������
                theList = list;
          }

          public boolean hasNext(){
                return current<theList.size();
          }

          public AnyType next(){
                if(!hasNext()){
                     throw new ArrayIndexOutOfBoundsException();
                }
                return theList.theItem[current++];
          }

          public void remove(){
                theList.remove(--current);
          }
      }
                              //�ڲ����Ƕ���࣬��ϲ���ĸ������ĸ�ඣ������˸о��ǣ���ϲ��! �����棬�û���...��
}










