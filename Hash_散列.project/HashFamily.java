
//一个自定义接口。为布谷鸟散列提供多个哈希函数，还可以更新哈希函数，返回哈希函数的个数

public interface HashFamily<AnyType>{
    public int hash(AnyType x,int i);
    public void generateNewFunctions();
    public int getNumberOfFunctions();
}