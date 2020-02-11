package Codewar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DoubleLinear {
    public static int dblLinear (int n) {
        Deque<Integer> Ln = new  ArrayDeque<>();
        Deque<Integer> Rn = new ArrayDeque<>();
        List<Integer> l = new ArrayList<Integer>();
        List<Integer> R = new ArrayList<Integer>();
        int count = 0;
        int min = 1;
        while(count < n){
            //l.add(min*2+1) ; R.add(3*min+1);
            Ln.add(min*2+1);Rn.add(3*min+1);
            System.out.println(min);
            if (Ln.getFirst().compareTo(Rn.getFirst()) == -1){
                min = Ln.poll();
                try {
                    if (min == Ln.getFirst()) {
                        Ln.poll();
                    }
                }
                catch (Exception e){};
            }
            else if(Ln.getFirst().equals(Rn.getFirst())){
                min = Rn.poll();
                min = Ln.poll();
            }
            else{
                min = Rn.poll();
                try {
                    if (min == Rn.getFirst()) {
                        Rn.poll();

                    }
                }
                catch (Exception e){};
            }
            count += 1;
        }
        /*for (int a : l ) {
            System.out.println(a);
        }
        System.out.println("///////////////////////////");
        for (int a : R ) {
            System.out.println(a);
        }*/
        return min ;
    }

    public static void main(String[] args) {
        System.out.println(dblLinear(60));

    }
}
