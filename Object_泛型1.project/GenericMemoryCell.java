//指定一个泛型类，类名后面可包含一个或多个类型，此处为Integer

public class GenericMemoryCell<Integer>{
    public void write(Integer x)   { storedValue = x;}
    public Integer read()     { return storedValue;}
    private Integer storedValue;
}