
public class DisjSets{

    public DisjSets(int size){
        s = new int[size];
        for(int i=0;i<size;i++){
            s[i] = -1;
        }
    }
   
    public void union(int root1,int root2){

        if(s[root1] < s[root2])
            s[root2] = root1;
        else{
            if(s[root1]==s[root2])
                s[root2]--;
            s[root1] = root2;
        }
    }

    public int find(int x){
        if(s[x]<0)
            return x;
        else
            return s[x] = find(s[x]);
    }

    public int[] getElement(){
        int element[] = new int[s.length];
        for(int i=0;i<s.length;i++){
           element[i] = s[i];
        }
        return element;
    }

    

    private int[] s;
}