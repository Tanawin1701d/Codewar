/*package Codewar;

import java.util.*;

public class number_manager {
    //                 n.card       v.seq       value    expression
    private static Object[] save ;

    private static void init(int n){
        save = new Object[n];
        for (int i = 0; i <n ; i++) {
            Map<String, Map<Integer, String>> seq     = new HashMap<>();
            save[i] = seq;
        }
    }

    private static int[] permutation(Integer[] a){

        Stack<Integer[][]> s = new Stack<>();
        Set<Integer>       r = new HashSet<>();

        s.push(new Integer[][] {{} ,a});

        while (!s.isEmpty()){
            Integer[][] buffer = s.pop();
            for(int i = 0; i<buffer[1].length; i++){
                Integer[][] putt = new Integer[buffer[0].length+1][buffer[1].length-1];
                putt[0] = Arrays.a
            }
        }




        return
    }


    public static String solver(int[] number){





        return "";
    }

}*/