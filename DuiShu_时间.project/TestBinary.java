
public class TestBinary{
    public static void main(String [] args){
        Integer A[] = {23,45,56,78,90,133,234,245,567};   /*  �ڴ˴���NTMD......��һ��Ҫ��ס�
                                                              ......8��......��������......
                                                              ���Բ����ڷ�����ʹ�ã����Բ���
                                                              ......��Debug�����ڣ��Ѿ�12��
                                                              ���ˣ��͵���ʱ�������ѵ��......��
                                                           */  
        System.out.println("90��λ���ǵ� " + BinarySearch.binarySearch(A,90) +" ��");
        System.out.println("133��λ���ǵ� " + BinarySearch.binarySearch(A,133)+" ��");
        System.out.println("456��λ���ǵ� " + BinarySearch.binarySearch(A,456)+" ��");
    }
}