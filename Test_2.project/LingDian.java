
public class LingDian{
    public static <AnyType> AnyType jie(AnyType low,AnyType high,Function<AnyType> cmp){
         AnyType z =cmp.new_();
         while( cmp.f(low) * cmp.f(high) <=0 ){
              if(cmp.f(low)==0)   return low;  
              if(cmp.f(high)==0)  return high;
              else{
                  AnyType mid = cmp.mid_(low,high);
                  if(cmp.f(mid) ==0)  return mid;
                  else{
                     if(cmp.f(mid) * cmp.f(high) <=0)
                         low = mid;
                     else{  
                         if(cmp.f(mid) * cmp.f(low) <=0)   high = mid; 
                     }
                  }
               }
         }
         return z;
    }
}
              
                  