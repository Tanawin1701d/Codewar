package Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class matcher {
    public static void main(String args[]){

        Pattern test1 = Pattern.compile("((a)(b(c)))");
        Matcher m = test1.matcher("I adoc love dog godabcabc ");
        while (m.find()){
            System.out.println(m.group());
        }
        System.out.println("finish");

    }
}
