package Codewar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Maze1 {
    private static List<Character> save = new ArrayList<>();
    private static char[] dir = {'R','B','L','#' , 'R' , 'B' , 'L' };
    private static Boolean m( char[][] maze , int heading , int posx ,int posy ) {
        System.out.println(posx+"   lklk  "+posy);
        if (!(posx == 0 || posy == 0 ||posx == maze[1].length-1 || posy == maze.length-1)) {
             maze[posy][posx] = '#' ;
             for (int i = -1; i < 2; i++) {
                 if (i != 0 & maze[posy + i][posx] == ' ') {
                     int heading1 =  (i == -1 ? 0 :2);
                     if( m(maze, heading1 , posx, i + posy) ) {
                         save.add(0, 'F');
                         if (heading1 - heading != 0) {
                             save.add(0, dir[(heading1 - heading)+3]);
                         }
                         return true;
                     }
                 }
             }
             for (int j = -1; j < 2; j++) {
                 if (j != 0 & maze[posy][posx+j] == ' ') {
                     int heading1 = (j == -1 ? 3 : 1);
                     if(m(maze, heading1, posx + j, posy)) {
                         save.add(0, 'F');

                         if (heading1 - heading != 0) {
                             save.add(0, dir[(heading1 - heading) + 3]);
                         }
                         return true;
                     }
                     }
             }
             maze[posy][posx] = ' ';
             return false;
         }
         else if (maze[posy][posx] == '#')return false ;
         else return true;
    }

    public static List<Character> escape(char[][] maze) {
        save.clear();
        int heading = -1;
        int i = 0; int j = 0 ;
        outer :
        for( i = 0 ; i<maze.length ; i++) {
            for ( j = 0; j < maze[i].length ; j++) {
                if (maze[i][j] == '^') {heading = 0 ; break outer;}
                else if (maze[i][j] == '>') {heading = 1 ; break outer;}
                else if (maze[i][j] == 'v') {heading = 2 ; break outer;}
                else if (maze[i][j] == '<') {heading = 3 ; break outer;}
            }
        }
        m(maze , heading , j ,i ) ;
        return save;
    }

    public static void main(String[] args) {
        escape(new char[][] {
                "###########".toCharArray(),
                "#>        #".toCharArray(),
                "######### #".toCharArray(),
        });
        Stream.of(save).forEach(System.out::println);
    }


}
