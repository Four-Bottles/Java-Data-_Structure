/*  ��������ϸ��   */
   
public class TestMemoryCell{
    public static void main(String args[]){
        MemoryCell memory = new MemoryCell();
        memory.write("37");
        String val = (String)memory.read();    /*  1.ʹ����ǿ��ת����Ҳ��ʹ��toString��������  */
        System.out.println(" Contents are: " + val);
    }
}                             /*   2.����ʹ�û������ͣ�ֻ��������������Object����    */