//ָ��һ�������࣬��������ɰ���һ���������ͣ��˴�ΪInteger

public class GenericMemoryCell<Integer>{
    public void write(Integer x)   { storedValue = x;}
    public Integer read()     { return storedValue;}
    private Integer storedValue;
}