package Codewar;

import java.util.Arrays;

class Maze4 {
    private static boolean solver(char[][] view , int posx , int posy){
        try {
            if ((posx == view[0].length - 1 || posy == view.length - 1 || posx == 0 || posy == 0) & view[posy][posx] == ' ')
                return true;
            else if (view[posy][posx] == '#') return false;
            else {
                view[posy][posx] = '#';
                boolean temp = false;
                for (int i = -1; i < 2; i += 2) {
                    temp = temp || solver(view, posx + i, posy);
                }
                for (int i = -1; i < 2; i += 2) {
                    temp = temp || solver(view, posx, posy + i);
                }
                view[posy][posx] = ' ';
                return temp;
            }
        }
        catch (Exception e ){
            return true ;
        }
    }

    public static boolean hasExit(String[] maze) {
        char[][] view =  Arrays.stream(maze).map(String::toCharArray).toArray(char[][]::new);
        int i; int j = 0 ;boolean f = false ; int li = 0 ; int  lj = 0;
        mook :
        for ( i = 0; i < view.length ; i++) {
            for ( j = 0; j < view[i].length ; j++) {
                if (view[i][j] == 'k') {
                    if (f == false){
                        f = true;
                        lj = j ; li = i;
                    }
                    else throw new RuntimeException();
                }
            }
        }
        return solver(view,lj,li) ;
    }

    public static void main(String[] args) {
        System.out.println(hasExit(new String[]{"k ","k"}));
    }
}