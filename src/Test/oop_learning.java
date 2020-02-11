package Test;

import org.w3c.dom.ls.LSOutput;

class xy{
    public int a ;
    static int b = 13;

    public String toString(){
        return "a is : " + String.valueOf(a) + "b is : " + String.valueOf(b);
    }



}

public class oop_learning {

    public static void main(String[] args) {
        xy f = new xy();
        f.a = 12;
        System.out.println(f);
        f.b = 2434324;
        xy l =  new xy();
        System.out.println(l);

    }
}
