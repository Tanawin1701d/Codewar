package solve_24;

import java.util.*;
import java.util.stream.Stream;

public class Permutator {


    private static int frac(int a){
        int re = 1;
        for (int i = 1; i <= a ; i++) {
            re*=i;
        }
        return re;
    }



    public static int[][] permutation(int[] number, int lim){
        Stack<int[]>    s = new Stack<>();
        int[][]        re = new int[frac(number.length)][number.length-1];
        int             k = 0;
        Set<String>  save = new HashSet<>();
        s.push(number);

        while (!s.isEmpty()){
            int[] buffer = s.pop();
            String test = Arrays.toString(buffer);
            if (save.contains(Arrays.toString(buffer)))
                continue;
            else{
                save.add(test);
            }
            int start = buffer[buffer.length-1];
            if (start < lim) {
                for (int i = start; i < buffer.length-1; i++) {
                    int[] buffer1 = buffer.clone();
                    int temp = buffer[start];
                    buffer1[start] = buffer1[i];
                    buffer1[i] = temp;
                    buffer1[buffer.length - 1]++;
                    s.push(buffer1);
                }
            }else{
                    re[k++] = Arrays.copyOfRange(buffer,0,lim);

            }
        }
        return Arrays.copyOfRange(re,0,k);


    }

    public static void main(String[] args) {
        long starrt = System.currentTimeMillis();
        int[][] re = permutation(new int[]{1,2,3,4,5,6,7,8,9,0},9);
        for (int i = 0; i < re.length ; i++) {
            System.out.println(Arrays.toString(re[i]));
        }
        System.out.println(re.length);
        System.out.println(System.currentTimeMillis() - starrt);
    }
}
