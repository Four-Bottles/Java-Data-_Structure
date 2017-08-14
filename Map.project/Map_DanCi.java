
/*题名是“标准库中的集合与映射”，与第三章讨论的List容器中的ArrayList和LinkedList（这两种链表用于查找效率很低）类似，Collection API 也提供了两个附加容器Set和Map

所以，这个小程序就是来练习使用这两个容器的。总的来说，用法是挺复杂的，书上对于具体函数的实现没有赘述，而是直接在代码中使用。当然对于我这种大笨蛋，一点都不明白在

什么地方该用什么函数。因此，前前后后我把代码和书上的这几页文字描述部分看了有五遍左右，才算明白每个函数的具体用法。过程很痛苦，但起码学到了东西。加油! 共勉......*/


import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Map_DanCi{
    
    public static void print(Map<String,List<String>> adjWords,int minWords){         //容器Map的声明方法，要记住
         for(Map.Entry<String,List<String>> entry : adjWords.entrySet()){             //接口Map.Entry里的函数主要用来访问关键字、关键字的值
              List<String> words = entry.getValue();                                  //entrySet()方法和Map.Entry对应（为迭代而用）
              if(words.size() >= minWords){ 
                   System.out.print(entry.getKey() + "(" + words.size() + "):");
                   for(String w : words){
                        System.out.print(w + " ");
                   }
                   System.out.println();
              }
         }
    }

    private static boolean OneCharOff(String words1,String words2){    //判断两个单词是只有一个字母不同
         if(words1.length() != words2.length())
               return false;
        
         int diff = 0;

         for(int i=0;i<words1.length();i++){
              if(words1.charAt(i) != words2.charAt(i));
                  if(++diff > 1)
                     return false;
         }
         return diff == 1;
    }

    private static <KeyType> void update(Map<KeyType,List<String>> m ,KeyType key,String value){     //更新Map对象，记得关键字用泛型
         List<String> lst = m.get(key);
         if(lst==null){
             lst = new ArrayList<>();
             m.put(key,lst);
         }
         lst.add(value);
    }

    public static Map<String,List<String>> AdjWords_1(List<String> theWords){        //第一种分类方法，最基础的方法。依次访问单词，依次比较和更新
         Map<String,List<String>> adjWords = new TreeMap<>();
         String[] words = new String[theWords.size()];
         theWords.toArray(words);
         for(int i=0;i<words.length;i++){
             for(int j=i+1;j<words.length;j++){
                  if(OneCharOff(words[i],words[j])){
                       update(adjWords,words[i],words[j]);
                       update(adjWords,words[j],words[i]);
                  }
             }
         }
         return adjWords;
    }

    public static Map<String,List<String>> AdjWords_2(List<String> theWords){      //第二种分类方法。先把长度相同的单词放在一个Map对象里，接着依次比较和更新
         Map<String,List<String>> adjWords = new TreeMap<>();
         Map<Integer,List<String>> wordsByLength = new TreeMap<>();
         for(String w : theWords){
             update(wordsByLength,w.length(),w);
         }
         for(List<String> groupWords : wordsByLength.values()){
             String[] words = new String[groupWords.size()];
             groupWords.toArray(words);
             for(int i=0;i<words.length;i++){
                 for(int j=i+1;j<words.length;j++){
                   if(OneCharOff(words[i],words[j])){
                      update(adjWords,words[i],words[j]);
                      update(adjWords,words[j],words[i]);
                   }
                 }
             } 
         }
         return adjWords;
    }

    public static Map<String,List<String>> AdjWords_3(List<String> words){   //第三种分类方法（前两种方法怎么都没法实现输出，和书上的代码对了好几遍都没发现什么问题）
         Map<String,List<String>> adjWords = new TreeMap<>();                //同样先把长度相同的单词归类，接着把单词从前往后依次去掉一个字母作为关键字，再取关键字的值
         Map<Integer,List<String>> wordsByLength = new TreeMap<>();

         for(String w : words){
             update(wordsByLength,w.length(),w);
         }

         for(Map.Entry<Integer,List<String>> entry : wordsByLength.entrySet()){
               List<String> groupValue = entry.getValue();
               int groupKey = entry.getKey();

               for(int i=0;i<groupKey;i++){
                    Map<String,List<String>> wordsSmall = new TreeMap<>();
                    for(String str : groupValue){
                         String s = str.substring(0,i) + str.substring(i+1);
                         update(wordsSmall,s,str);
                    }
                    for(List<String> str : wordsSmall.values()){
                         if(str.size() >= 2)
                             for(String words1 : str)
                                  for(String words2 : str)
                                       if(words1 != words2)
                                           update(adjWords,words1,words2);
                    }             
               }
         }
         return adjWords;
    }
}