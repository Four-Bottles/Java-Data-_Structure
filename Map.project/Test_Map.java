import java.util.List;
import java.util.ArrayList;

public class Test_Map{
    public static void main(String[] args){
         List<String> list = new ArrayList<>();
         list.add("qwea");
         list.add("qweb");
         list.add("qwec");
         list.add("qwed");
         list.add("qwer");
         list.add("gwer");
         list.add("mwer");
         list.add("nwer");
         list.add("hwer");
         list.add("qwej");
         list.add("qwbr");
         list.add("qwcr");
         list.add("qwmr");
         list.add("qner");
         list.add("qaer");
         list.add("qqer");
         list.add("qrer");
         Map_DanCi.print(Map_DanCi.AdjWords_1(list),2);
         System.out.println();   

         Map_DanCi.print(Map_DanCi.AdjWords_2(list),2);
         System.out.println(); 

         Map_DanCi.print(Map_DanCi.AdjWords_3(list),2);
         System.out.println(); 
    }
}