package Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class comparator {

    public static Comparator<Integer> t(){
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer a1, Integer a2) {
                return (a1 < a2 ? 1 : 0) ;
            }

        };
        return cmp;
    }
    public static void main(String[] args) {
        Integer[] a = {1,2,3,4,5,6,7};
        Arrays.sort(a ,t() );
    }
}
