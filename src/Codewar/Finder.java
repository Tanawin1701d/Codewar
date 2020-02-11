package Codewar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Finder {
    private static int row;
    public static boolean contin(String[][] data , int i ,int j){
        Boolean n=false,s=false,e=false,w=false,ne=false,nw=false,sw=false,se = false;
        if (j==row || i == 0)
            return true ;
        if (data[i-1][j] == "W")
            n = contin(data, i-1, j);
        if (data[i+1][j] == "W")
            s = contin(data, i+1, j);
        if (data[i][j+1] == "W")
            e = contin(data, i, j+1);
        if (data[i][j-1] == "W")
            w = contin(data, i, j-1);
        if (data[i-1][j+1] == "W")
            ne = contin(data, i-1, j+1);
        if (data[i-1][j-1] == "W")
            nw = contin(data, i-1, j-1);
        if (data[i+1][j+1] == "W")
            se = contin(data, i-1, j+1);
        if (data[i-1][j-1] == "W")
            sw = contin(data, i-1, j-1);
        return n||s||e||w||ne||nw||sw||se ;
    }
    public static boolean pathFinder(String maze) {
        String[] sub = maze.split("\n");
        row = (int)Math.sqrt(maze.length())-1;
        String[][] data = new String[row+1][row+1];
        for (int i = 0; i < row+1 ; i++) {
            data[i] = sub[i].split("");
        }

//        for (int i = 0; i < row ; i++) {
//            for (int j = 0; j < row ; j++) {
//                System.out.print(data[i][j]);
//            }
//            System.out.println();
//        }
        Boolean save ;
        for (int j = row; j >= 0 ; j--) {
            if (data[row][j] == "W"){
                save = contin(data,row,j);
            }
        }
        return false ;
    }
}
