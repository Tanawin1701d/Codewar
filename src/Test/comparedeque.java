package Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class comparedeque {
    public static void main(String[] args) {
        Deque<Integer> a = new ArrayDeque<>();
        int min = 5;
        a.add(4*1);

        a.add(a.poll()*5+8);
        a.poll();
        Deque<Integer> b = new ArrayDeque<>();
        b.add(min*4+8);
        System.out.println(a.getFirst() == b.getFirst());
    }
}
