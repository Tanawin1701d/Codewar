package Codewar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loopover {

    static int col;
    static int row;
    static int r_at;       // row active
    static int c_at;       // col active
    static int r_cu;       // row curr
    static int c_cu;       // col curr
    static int FixRow0;    // Fix Row
    static List<String> reccord ;
    static char[][] mixedUpBoard1;

    public static List<String> solve(char[][] mixedUpBoard, char[][] solvedBoard) {
        col = mixedUpBoard[0].length;
        row = mixedUpBoard.length;
        r_at = c_at = r_cu = c_cu = FixRow0 = 0;
        reccord = new ArrayList<>();
        mixedUpBoard1 = mixedUpBoard;

        while (r_at < row){
            // it's declar th c_at,r_at
            System.out.println(r_at + "   " + c_at  );
            find(mixedUpBoard, solvedBoard);

            // same position continue
            if (r_cu == r_at && c_cu == c_at){
                IncreaseActive();
                continue;
            }
            // if same row but it @ first
            if (r_cu == r_at && c_at == 0){
                while (c_cu != 0){
                    MoveLeft(r_cu);
                    c_cu--;
                }
                continue;
            }
            // if same row
            if ( r_cu == r_at && r_cu != row-1 ){
               MoveDown( c_cu );
               r_cu++;
               int ret = c_cu;
               MoveLeft( r_cu );
               c_cu = (c_cu == 0) ? col - 1 : c_cu-1;
               MoveUp( ret );

            }

            if (c_cu == c_at){
                    MoveRight(r_cu);
                    c_cu = (c_cu+1)%col;

            }else if (c_cu - c_at > 1){
                while (c_cu - c_at != 1){
                    MoveLeft(r_cu);
                    c_cu--;

                }
            }else if(c_cu - c_at <= -1){
                while (c_cu - c_at != 1) {
                    MoveRight(r_cu);
                    c_cu+=1;

                }
                c_cu = c_cu%col;
            }

            int getback = r_cu - r_at;

            for (int i = 0; i < getback  ; i++) {
                MoveDown(c_at);
            }
            MoveLeft(r_cu);
            for (int i = 0; i <  getback ; i++) {
                MoveUp(c_at);
            }

            IncreaseActive();


        }
        System.out.println("------------------ This is init -----------------------");
        MoveLastFront(solvedBoard);
        System.out.println("------------------ This is init  -----------------------");
        if (FixLast(solvedBoard)){
            return reccord;
        }
        System.out.println("----------------------------------------------extent");
        return null;
    }

    private static void find(char[][] mix, char[][] solve){
        for (int i = 0; i< row; i++){
            for (int j = 0; j<col;j++){
                if (mix[i][j] == solve[r_at][c_at]){
                    System.out.println(i + "   "+j);
                    r_cu = i;
                    c_cu = j;
                }
            }
        }
    }

    private static void IncreaseActive(){
        if (c_at + 1 == col ){
            r_at++;
            c_at = 0;
        }else{
            c_at ++;
        }
    }

    public static void MoveUp(int i){
        reccord.add("U" + i);
        if (row > 1) {
            char buffer1 = mixedUpBoard1[0][i];
            for (int j = 0; j < row - 1; j++) {
                mixedUpBoard1[j][i] = mixedUpBoard1[j + 1][i];
            }
            mixedUpBoard1[row - 1][i] = buffer1;
        }
        System.out.println("Move up");
        System.out.println(Arrays.deepToString(mixedUpBoard1));
    }

    public static void MoveDown(int i){
        reccord.add("D" + i);
        if (row > 1) {
            char buffer1 = mixedUpBoard1[row-1][i];
            for (int j = row-1; j >= 1; j--) {
                mixedUpBoard1[j][i] = mixedUpBoard1[j - 1][i];
            }
            mixedUpBoard1[0][i] = buffer1;
        }
        System.out.println("Move Down");
        System.out.println(Arrays.deepToString(mixedUpBoard1));
    }

    public static void MoveLeft(int i){
        reccord.add("L" + i);
        if ( col > 1 ){
            char buffer1 = mixedUpBoard1[i][0];
            for (int j = 0; j < col - 1 ; j++) {
                mixedUpBoard1[i][j] = mixedUpBoard1[i][j+1];
            }
            mixedUpBoard1[i][col-1] = buffer1;
        }
        System.out.println("Move Left");
        System.out.println(Arrays.deepToString(mixedUpBoard1));
    }

    public static void MoveRight(int i){

        reccord.add("R" + i);
        if ( col > 1 ){
            char buffer1 = mixedUpBoard1[i][col-1];
            for (int j = col-1; j >= 1 ; j--) {
                mixedUpBoard1[i][j] = mixedUpBoard1[i][j-1];
            }
            mixedUpBoard1[i][0] = buffer1;
        }
        System.out.println("Right");
        System.out.println(Arrays.deepToString(mixedUpBoard1));
    }

    public static void MoveLastFront(char[][] solveboard){
        while(mixedUpBoard1[row-1][0] != solveboard[row-1][0]){
            MoveLeft(row-1);
        }
    }

    public static void MoveFindatlast(char a){
        int i;
        for ( i = 0; i < col-1 ; i++) {
            if ( mixedUpBoard1[row-1][i] == a ){
                break;
            }
        }
        for (int j = 0; j < col-1-i ; j++) {
            MoveRight(row-1);
        }
    }

    public static void MoveFindatlast1(char a){

        System.out.println("find" + "   "+ a);

        int i;
        for( i = 0 ; i < row ; i++ ){
            System.out.println(mixedUpBoard1[i][col-1]);
            if (mixedUpBoard1[i][col-1] == a){

                break;
            }
        }
        System.out.println(i);
        for( int j = 0 ; j < row-1-i ; j++ ){
            System.out.println(i);
            MoveDown(col-1);
        }
        System.out.println("fin");
    }

    public static boolean FixLast(char[][] solvedBoard){
        System.out.println("-------------------Initial last row fixer---------------------------------");
        MoveUp(col-1);
        boolean status_up = false;

        for (int i = 1; i < col; i++){
            if ( status_up ){
                char buffer = mixedUpBoard1[0][col-1];
                if (solvedBoard[row-1][i] == buffer){
                    MoveFindatlast(solvedBoard[row-1][i-1]);
                    MoveLeft(row-1);
                    MoveUp(col-1);
                }else{
                    MoveFindatlast(solvedBoard[row-1][i]);
                    MoveUp(col-1);
                    i--;
                }
                status_up = false;

            }else{
                char buffer = mixedUpBoard1[row-2][col-1];
                if (solvedBoard[row-1][i] == buffer){
                    MoveFindatlast(solvedBoard[row-1][i-1]);
                    MoveLeft(row-1);
                    MoveDown(col-1);
                }else{
                    MoveFindatlast(solvedBoard[row-1][i]);
                    MoveDown(col-1);
                    i--;
                }
                status_up = true;
            }


        }

        for (int i = 0; i < col ; i++) {
            if (mixedUpBoard1[row-1][i] != solvedBoard[row-1][i]){
                return false;
            }
        }

        if (!status_up){
            if (col % 2 == 0 ){
                return FixEvenLastRow(solvedBoard);
            }
            return FixEvenLastCol(solvedBoard);

        }
        return true;
    }

    public static boolean FixEvenLastCol(char[][] solvedBoard){
        System.out.println("--------------------------------Initial last col fixer--------------------------------");
        MoveDown(col-1);MoveDown(col-1);
        MoveLeft(row-1);

        boolean statusLeft = true;
        for (int i = 1; i < row ; i++) {
            System.out.println("=========" + i);
            if (statusLeft){
                System.out.println("inLeft");
                char buffer1 = mixedUpBoard1[row-1][col-2];
                if (buffer1 == solvedBoard[i][col-1]){
                    MoveFindatlast1(solvedBoard[i-1][col-1]);
                    MoveUp(col-1);
                    MoveRight(row-1);
                }else{
                    MoveFindatlast(solvedBoard[i][col-1]);
                    MoveRight(row-1);
                    i--;
                }
                statusLeft = false;
            }else{
                System.out.println("inRight");
                char buffer1 = mixedUpBoard1[row-1][0];
                if (buffer1 == solvedBoard[i][col-1]){
                    MoveFindatlast1(solvedBoard[i-1][col-1]);
                    MoveUp(col-1);
                    MoveLeft(row-1);
                }else{
                    MoveFindatlast1(solvedBoard[i][col-1]);
                    MoveLeft(row-1);
                    i--;
                }
                statusLeft = true;
            }
        }
        System.out.println("--------------------------------Initial last col shufler--------------------------------");

        if (statusLeft && row%2 == 0){
            MoveLeft(row-1);
            statusLeft = true;

            for (int i = 0; i < row ; i++) {
                MoveDown(col-1);
                if (statusLeft){
                    MoveRight(row-1);
                }else {
                    MoveLeft(row-1);
                }
                statusLeft = !(statusLeft);
            }
        }

        return Arrays.deepToString(mixedUpBoard1).equals(Arrays.deepToString(solvedBoard));
    }

    public static boolean FixEvenLastRow(char[][] solvedBoard){
        System.out.println("*******************************Initial row shufler*************************************");
        MoveDown(col-1);
        boolean status_up = true;
        for (int i = 0; i < col ; i++) {
            MoveLeft(row-1);
            if (status_up){
                MoveUp(col-1);
                status_up = false;
            }else{
                MoveDown(col-1);
                status_up = true;
            }
            System.out.println("statusup = " + status_up);
        }
        MoveLeft(row-1);

        return Arrays.deepToString(mixedUpBoard1).equals(Arrays.deepToString(solvedBoard));
    }

    public static char[][] buildarr(String a, int row, int col){
        char[][] test = new char[row][col];
        for (int i = 0; i < a.length() ; i++) {
            test[i/col][i%col] = a.charAt(i);
        }
        System.out.println(Arrays.deepToString(test));


        return test;
    }

    public static void main(String[] args) {
        List<String> ss = Loopover.solve(new char[][]{{'W', 'C', 'M', 'D', 'J', '0'},
                                                      {'O', 'R', 'F', 'B', 'A', '1'},
                                                      {'K', 'N', 'G', 'L', 'Y', '2'},
                                                      {'P', 'H', 'V', 'S', 'E', '3'},
                                                      {'T', 'X', 'Q', 'U', 'I', '4'},
                                                      {'Z', '5', '6', '7', '8', '9'}},
                new char[][]{{'A', 'B', 'C', 'D', 'E', 'F'},
                            {'G', 'H', 'I', 'J', 'K', 'L'},
                            {'M', 'N', 'O', 'P', 'Q', 'R'},
                            {'S', 'T', 'U', 'V', 'W', 'X'},
                            {'Y', 'Z', '0', '1', '2', '3'},
                            {'4', '5', '6', '7', '8', '9'}}
                );
        System.out.println(ss);

        System.out.println("-----------------------------------------------------------------------");

        List<String> ss1 = Loopover.solve( buildarr("IBJDc" +
                "TGd5S" +
                "ZV37K" +
                "1RF26" +
                "H8UXP" +
                "ONQYE" +
                "M0bC4" +
                "WAa9L",8,5), buildarr("ABCDE" +
                "FGHIJ" +
                "KLMNO" +
                "PQRST" +
                "UVWXY" +
                "Z0123" +
                "45678" +
                "9abcd",8,5) );
        System.out.println(ss1);
            //IBJDc
        //TGd5S
        //ZV37K
        //1RF26
        //H8UXP
        //ONQYE
        //M0bC4
        //WAa9L
        //
        //to turn into:
        //ABCDE
        //FGHIJ
        //KLMNO
        //PQRST
        //UVWXY
        //Z0123
        //45678
        //9abcd
    }

}
