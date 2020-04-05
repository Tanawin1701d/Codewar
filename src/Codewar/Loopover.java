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
        c_at = 0 ; r_at = row -1;
        find(mixedUpBoard, solvedBoard);
        while (c_cu != 0){
            c_cu--;
            MoveLeft(row-1);
        }
        System.out.println("------------------ This is init  -----------------------");
        MoveLeft(row-1);
        MoveLeft(row-1);
        /////////////////////// search 'seccond'
        System.out.println("Fecting");
        MoveUp(col-1);
        int c_now = 1;


        while (c_now < col){


            // findwe
            c_at = c_now;
            try {
                find(mixedUpBoard, solvedBoard);
            }catch (NullPointerException e){
                return null;
            }


            // go and push down
            int time = 0;

            while(c_cu != col-1) {
                MoveRight(row - 1);
                time++;
                c_cu++;
            }

            MoveDown(col-1);

            // goto their pos
            c_at = 0;
            find(mixedUpBoard, solvedBoard);

            while(c_cu != col-1) {
                MoveRight(row - 1);
                time++;
                c_cu++;
            }
            for (int i = 0; i < c_now ; i++) {
                MoveLeft(row-1);
            }


            // pushup
            MoveUp(col-1);
            c_now++;
        }




//        while(c_now != row -1  ){
//
//            System.out.println("startttttttttttttttttttttttttttttttttttttttt");
//            // find
//            System.out.println("we will use    -----------------------------------"+solvedBoard[row-1][(col+c_now+1)%col]);
//            c_at = (col+c_now +1)%col;
//            find(mixedUpBoard1, solvedBoard);
//            while(c_cu != col-1){
//                MoveRight(row-1);
//                c_cu++;
//            }
//
//            // up
//
//            MoveUp(col-1);
//
//            // find friend
//            c_at = (0)%col;
//            find(mixedUpBoard, solvedBoard);
//            c_now++;
//
//            while(c_cu != col-1){
//                MoveRight(row-1);
//                c_cu++;
//            }
//
//            MoveLeft(row-1);
//
//            /*
//            if (mixedUpBoard1[row-1][col-1] == solvedBoard[0][col-1]){
//                MoveLeft(row -1);
//            }
//             */
//            MoveDown(col-1);
//
//            if (mixedUpBoard1[0][col-1] != solvedBoard[0][col-1]) {
//                MoveLeft(row - 1);
//                if (mixedUpBoard1[row - 1][col - 1] == solvedBoard[0][col - 1]) {
//                    MoveLeft(row - 1);
//                }
//                MoveUp(col - 1);
//
//
//                for (int i = 0; i < col; i++) {
//                    if (mixedUpBoard1[row - 1][i] == solvedBoard[0][col - 1]) {
//                        c_cu = i;
//                        break;
//                    }
//                }
//
//                while (c_cu != col - 1) {
//                    MoveRight(row - 1);
//                    c_cu++;
//                }
//
//                MoveDown(col - 1);
//            }
//        }
//
////        for (int i = 0; i < col ; i++) {
////            if (Arrays.toString(mixedUpBoard1[row - 1]).equals(Arrays.toString(solvedBoard[row - 1]))){
////                System.out.println(Arrays.deepToString(mixedUpBoard1));
////                return reccord;
////            }
////            MoveLeft(row-1);
////        }
////
////        MoveUp(col-1);
////        MoveLeft(row-1);
////        MoveLeft(row-1);
////        boolean up = true;
////
////        for (int i = 0; i < col -1 ; i++) {
////            if (up){
////                MoveDown(col-1);
////                up = false;
////            }else{
////                MoveUp(col-1);
////                up = true;
////            }
////            MoveLeft(row-1);
////        }
////
////        for (int i = 0; i < col ; i++) {
////            if (Arrays.deepEquals(mixedUpBoard1, solvedBoard)){
////                System.out.println(Arrays.deepToString(mixedUpBoard1));
////                return reccord;
////            }
////            MoveLeft(row-1);
////        }

        System.out.println("------------------finish   new algoritm");


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
    }

}
