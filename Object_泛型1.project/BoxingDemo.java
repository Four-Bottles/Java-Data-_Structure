//ʹ���˷�����GenericMemoryCell<Integer>,��������Զ�װ�䡢�Զ�����

public class BoxingDemo{
   public static void main(String []args){
       GenericMemoryCell<Integer> m = new GenericMemoryCell<>();
       m.write(50);              /*�Զ�װ�䣬����һ��int �ͱ�������һ��Integer����
                                             ��������Ļ�����һ����Integer���췽���ĵ���
       int val = m.read();       //�Զ����䣬����һ��Integer����ŵ���Ҫint �ͱ����ĵط�
                                             ��������Ļ�����һ����intValue�����ĵ���
       System.out.println("Contents are: " + val);
   }
}