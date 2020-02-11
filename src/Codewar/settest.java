package Codewar;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class settest {
    public static void main(String[] args) {
        int[][] headdir = {{0,-1},{-1,0},{0,1},{1,0}};
        System.out.println(Set.of(headdir).contains(new int[]{0,-1
        }));
        System.out.println(Arrays.equals(headdir[0], new int[]{0,-1}));
    }
}
