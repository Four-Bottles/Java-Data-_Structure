//��װ��ķ�ʽ���� �� int �İ�װ����Integer

public class WrapperDemo{
    public static void main(String args[]){
        MemoryCell memory = new MemoryCell();
        memory.write(new Integer(40));
        Integer x = (Integer)memory.read();
        int val = x.intValue();
        System.out.println("Contents are: " + val);
    }
}