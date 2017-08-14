
/*�����ǡ���׼���еļ�����ӳ�䡱������������۵�List�����е�ArrayList��LinkedList���������������ڲ���Ч�ʺܵͣ����ƣ�Collection API Ҳ�ṩ��������������Set��Map

���ԣ����С�����������ϰʹ�������������ġ��ܵ���˵���÷���ͦ���ӵģ����϶��ھ��庯����ʵ��û��׸��������ֱ���ڴ�����ʹ�á���Ȼ���������ִ󱿵���һ�㶼��������

ʲô�ط�����ʲô��������ˣ�ǰǰ����ҰѴ�������ϵ��⼸ҳ�����������ֿ�����������ң���������ÿ�������ľ����÷������̺�ʹ�࣬������ѧ���˶���������! ����......*/


import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Map_DanCi{
    
    public static void print(Map<String,List<String>> adjWords,int minWords){         //����Map������������Ҫ��ס
         for(Map.Entry<String,List<String>> entry : adjWords.entrySet()){             //�ӿ�Map.Entry��ĺ�����Ҫ�������ʹؼ��֡��ؼ��ֵ�ֵ
              List<String> words = entry.getValue();                                  //entrySet()������Map.Entry��Ӧ��Ϊ�������ã�
              if(words.size() >= minWords){ 
                   System.out.print(entry.getKey() + "(" + words.size() + "):");
                   for(String w : words){
                        System.out.print(w + " ");
                   }
                   System.out.println();
              }
         }
    }

    private static boolean OneCharOff(String words1,String words2){    //�ж�����������ֻ��һ����ĸ��ͬ
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

    private static <KeyType> void update(Map<KeyType,List<String>> m ,KeyType key,String value){     //����Map���󣬼ǵùؼ����÷���
         List<String> lst = m.get(key);
         if(lst==null){
             lst = new ArrayList<>();
             m.put(key,lst);
         }
         lst.add(value);
    }

    public static Map<String,List<String>> AdjWords_1(List<String> theWords){        //��һ�ַ��෽����������ķ��������η��ʵ��ʣ����αȽϺ͸���
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

    public static Map<String,List<String>> AdjWords_2(List<String> theWords){      //�ڶ��ַ��෽�����Ȱѳ�����ͬ�ĵ��ʷ���һ��Map������������αȽϺ͸���
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

    public static Map<String,List<String>> AdjWords_3(List<String> words){   //�����ַ��෽����ǰ���ַ�����ô��û��ʵ������������ϵĴ�����˺ü��鶼û����ʲô���⣩
         Map<String,List<String>> adjWords = new TreeMap<>();                //ͬ���Ȱѳ�����ͬ�ĵ��ʹ��࣬���Űѵ��ʴ�ǰ��������ȥ��һ����ĸ��Ϊ�ؼ��֣���ȡ�ؼ��ֵ�ֵ
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