
public class TestBinary{
    public static void main(String [] args){
        Integer A[] = {23,45,56,78,90,133,234,245,567};   /*  在此处，NTMD......你一定要记住喽
                                                              ......8种......基本类型......
                                                              绝对不能在泛型中使用，绝对不能
                                                              ......（Debug到现在，已经12点
                                                              多了，就当花时间买个教训了......）
                                                           */  
        System.out.println("90的位置是第 " + BinarySearch.binarySearch(A,90) +" 个");
        System.out.println("133的位置是第 " + BinarySearch.binarySearch(A,133)+" 个");
        System.out.println("456的位置是第 " + BinarySearch.binarySearch(A,456)+" 个");
    }
}