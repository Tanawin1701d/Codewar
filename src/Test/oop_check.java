package Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class oop_check {
    public int a = 0;
    int b = 1;
    public void t(){
        System.out.println( a );
    }

}

class test extends oop_check{
    public int a = 9;


}



class main {
    public static void main(String[] args) {
        oop_check x = new test();
        x.t();
        System.out.println(isnumberic("12."));
        test();
    }

    public static boolean isnumberic(String a) {
        Pattern number = Pattern.compile("[+-]?[0-9]+(\\.[0-9]+)?");
        Matcher test = number.matcher(a);
        return test.matches();
    }

    public static void test(){
        Stack<String> a  = new Stack<>();
        Stack<String> b  = new Stack<>();
        a.add("1");
        a.add("2");
        b.add("3");
        b.add("4");
        b.addAll(a);
        while (!b.isEmpty()){
            System.out.println(b.pop());
        }

    }
}
