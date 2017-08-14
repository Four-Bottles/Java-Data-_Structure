/*  考虑两个细节   */
   
public class TestMemoryCell{
    public static void main(String args[]){
        MemoryCell memory = new MemoryCell();
        memory.write("37");
        String val = (String)memory.read();    /*  1.使用了强制转换，也可使用toString（）方法  */
        System.out.println(" Contents are: " + val);
    }
}                             /*   2.不能使用基本类型，只有引用类型能与Object相容    */