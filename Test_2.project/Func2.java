
public class Func2 implements Function<String>{
    public double f(String x){
        double y = Double.valueOf(x);
        return (y*y - 3*y);
    }
    public String mid_(String a,String b){
        double A = Double.valueOf(a);
        double B = Double.valueOf(b);
        return Double.toString((A + B) /2);
    }
    public String new_(){
        return (new String());
    }
}