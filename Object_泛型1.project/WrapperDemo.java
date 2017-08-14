//包装类的范式方法 如 int 的包装类是Integer

public class WrapperDemo{
    public static void main(String args[]){
        MemoryCell memory = new MemoryCell();
        memory.write(new Integer(40));
        Integer x = (Integer)memory.read();
        int val = x.intValue();
        System.out.println("Contents are: " + val);
    }
}