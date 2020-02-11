package Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class acm_test {
    public static boolean func( int q) {
        return q%2==0;
    }
    public static void main(String[] args) {
        String a = "abcdefg" ;
        Scanner b = new Scanner(System.in);
        System.out.println("Let scaner do : ");
        //a = b.nextLine();
        System.out.print(a);
        System.out.println(12);
        String[] wer = {"a" , "b" , "c" , "d"};
        int[] fortest = {1,2,3,4,5,6,7,8,9} ;
        List<Integer> tester = Arrays.stream(fortest).filter(x -> x%2==0).boxed().collect(Collectors.toList());
        //for (int i = 0; i < tester.size() ; i++) {
        //    System.out.println(tester.get(i));
        //}
        //IntStream.range(1,23234).filter(x -> func(x)).boxed().forEach(System.out::println);








    }
}
