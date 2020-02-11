package Codewar;

import java.util.*;

public class Maze {
    private static List<Character> res ;
    private static int[][] headdir = {{0,-1},{-1,0},{0,1},{1,0}};
    private static Boolean recurs(char[][] maze , int i , int j , int hi , int hj ){
        if(maze[i][j] == '#') return false ;
        if(i == maze.length-1 || j == maze.length-1 || i == 0 || j == 0 ) return true ;
        maze[i][j] = '#';
        Boolean stop = false;
        for(int k = -1 ; k<2  ; k++){
            for (int m = -1; m < 2 ; m++) {
                Boolean r = recurs(maze,i+k , j+m ,k , m);
                if(r == true) {
                    if (hi == k && hj == m) {
                        res.add('F');
                    } else {
                        int temp = Arrays.asList(headdir).indexOf(new int[]{hi, hj});
                        if (headdir[(temp - 1) % 4].equals(new int[]{k, m})) {
                            res.add('F');
                            res.add('L');
                        } else if (headdir[(temp + 1) % 4].equals(new int[]{k, m})) {
                            res.add('F');
                            res.add('R');
                        } else {
                            res.add('F');
                            res.add('B');
                        }
                    }
                    stop  = true ;
                }

            }
            if (stop == true) break;
        }
        maze[i][j] = ' ';
    return false;
    }
    public static List<Character> escape(char[][] maze) {
        char[] heading = {'<','^','>','v'} ;
        for (int i = 0; i < maze.length ; i++) {
            for (int j = 0; j < maze[0].length ; j++) {
                if (Arrays.asList(heading).equals(maze[i][j])) {
                    recurs(maze , i , j , headdir[Arrays.asList(heading).indexOf(maze[i][j])][0] , headdir[Arrays.asList(heading).indexOf(maze[i][j])][1]);
                }
            }
        }
        //Collections.reverse(res);
//        for (int i = 0; i < 2 ; i++) {
//            System.out.println(res.get(i));
//        }
        System.out.println(res.size());
        return res  ;
    }
}
