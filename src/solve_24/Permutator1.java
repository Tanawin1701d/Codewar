package solve_24;

import java.util.*;
import java.util.stream.Stream;

public class Permutator1 {


    private static int frac(int a){
        int re = 1;
        for (int i = 1; i <= a ; i++) {
            re*=i;
        }
        return re;
    }



    static void permutation(int[] number){
        Queue<int[]>    s = new ArrayDeque<>();
        int             k = 0;
        Set<String>  save = new HashSet<>();
        s.add(number);

        while (!s.isEmpty()){
            int[] buffer = s.poll();
            String test = Arrays.toString(buffer);
            if (save.contains(Arrays.toString(buffer)))
                continue;
            else{
                save.add(test);
            }
            int start = buffer[buffer.length-1];
            for (int i = start; i < buffer.length-1; i++) {
                int[] buffer1 = buffer.clone();
                int temp = buffer[start];
                // swaper
                buffer1[start] = buffer1[i];
                buffer1[i] = temp;
                // pos++
                buffer1[buffer.length - 1]++;
                s.add(buffer1);
                solve24.operation(Arrays.copyOfRange(buffer1,0,buffer1[buffer1.length-1]), buffer1[buffer1.length-1] == buffer1.length-1 );
            }

        }
    }

    public static void main(String[] args) {
        //long starrt = System.currentTimeMillis();
        //int[][] re = permutation(new int[]{1,2,3,4,5,6,7,8,9,0},9);
//        for (int i = 0; i < re.length ; i++) {
//            System.out.println(Arrays.toString(re[i]));
//        }
//        System.out.println(re.length);
//        System.out.println(System.currentTimeMillis() - starrt);
        permutation(new int[] {1,2,3,4,0});


    }
}

