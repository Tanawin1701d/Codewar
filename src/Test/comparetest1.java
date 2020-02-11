package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class comparetest1 {
    public static void main(String[] args) {
        String a = "abc" ;
        String b = new String("abc");
        String c = "abc";
        //System.out.println(a==b);
        //System.out.println(a==c);
        String a1 = "abc" ;
        String[] d = {b , "efg" , "hij"} ;
        //System.out.println(d[0] == b);
        //System.out.println(d[0] == b);
        List<String> e = new ArrayList<String>();
        e.add(b);
        System.out.println(e.get(0).equals("abc"));
        List<String> f = Arrays.asList(d) ;
        System.out.println(f.get(0).contains("abc"));
        //System.out.println(Arrays.asList(d).contains(b));
        System.out.println(Arrays.asList(d).indexOf("abc"));
    }
}
