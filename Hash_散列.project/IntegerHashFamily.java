
//一个实现 HashFamily 接口的类。传递Integer类型的数，提供哈希函数

import java.util.Random;

@SuppressWarnings("unchecked")

public class IntegerHashFamily implements HashFamily<Integer>{

    public IntegerHashFamily(int num){      //构造函数，传递的参数值为哈希函数的个数
        MULTIPLIER = new int[num];
        generateNewFunctions();
    }

    public int hash(Integer x,int which){
        final int multiplier = MULTIPLIER[which];
        int hashVal = 0;
        for(int i=0;i<Integer.toString(x).length();i++){
            hashVal = hashVal * multiplier + Integer.toString(x).charAt(i); 
        }
        return hashVal;
    }

    public int getNumberOfFunctions(){
        return MULTIPLIER.length;
    }
   
    public void generateNewFunctions(){
        for(int i=0;i<MULTIPLIER.length;i++){
            MULTIPLIER[i] = rand.nextInt();
        }
    }

    private int[] MULTIPLIER;
    private Random rand = new Random();
}