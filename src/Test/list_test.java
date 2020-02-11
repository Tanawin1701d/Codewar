package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class list_test {
    public static void main(String[] args) {
        List<Character> we = new ArrayList<>();
        System.out.println(we);
        System.out.println(we == null);
        we.add('e');
        we.add('w');
        we.add('q');
        Stream.of(we).forEach(System.out::println);
        Integer[] x = {1,2,3,4,5,6,7,8,9};
        int[] y = new int[25];
        y = new int[] {1,2,3};
        List<Integer> a = Arrays.asList(1,2,3,4) ;
        System.out.println(a);
        System.out.println(a.contains(9));
        String b = new String("new");
        String c = new String("new");
        String d = new String("new");
        System.out.println(b.equals(c));
        List<String> com = new ArrayList<String>();   // build inside array
        com.addAll(Arrays.asList("a","b","c"));
        System.out.println(com.getClass().getName());
        System.out.println(com);
        System.out.println(com.contains(d));
        b = "View";
        System.out.println(com);

        List<String> com1 = new ArrayList<>();
        com1 = Arrays.asList("a","b","c");
        System.out.println(com1);
        com1.add(0,"r");

    }
}
