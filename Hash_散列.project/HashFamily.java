
//һ���Զ���ӿڡ�Ϊ������ɢ���ṩ�����ϣ�����������Ը��¹�ϣ���������ع�ϣ�����ĸ���

public interface HashFamily<AnyType>{
    public int hash(AnyType x,int i);
    public void generateNewFunctions();
    public int getNumberOfFunctions();
}