//使用像Object这样的类来表示泛型


public class MemoryCell{
    public Object read()     { return storedValue;}
    public void write(Object x)     {storedValue = x;}
    private Object storedValue; 
}