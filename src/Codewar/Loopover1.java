package Codewar;

import java.util.*;

public class Loopover1 {
    public static List<String> solve(char[][] mixedUpBoard, char[][] solvedBoard) {

        Set<String> ram = new HashSet<>();            // remember the pattern for protection same pattern
        Deque<board> operation = new ArrayDeque<>();  // for arrange the que to analysis
        board init = new board(mixedUpBoard);         // initialize
        board check = new board(solvedBoard);         // for checking
        operation.add(init);

        int Di = 0;
        if (init.equals(check)){
            return init.reccord;
        }
        while (!operation.isEmpty()){
            System.out.println("Fetch number : " + (Di++));
            board now = operation.poll();
                // must move to all direction if don't it miss.
            // move right
            for (int i = 0; i < now.row  ; i++) {
                board buffer1 = now.clone1();
                buffer1.MoveRight(i);
                String buffer1gen = buffer1.generator();
                System.out.println(buffer1gen);
                if ( buffer1.equals(check) ){
                    return buffer1.reccord;
                }
                if ( !ram.contains(buffer1gen) ){ // if it same it will discard.
                    ram.add(buffer1gen);
                    operation.add(buffer1);
                }
            }
            // move up
            for (int i = 0; i < now.col  ; i++) {
                board buffer1 = now.clone1();
                buffer1.MoveUp(i);
                String buffer1gen = buffer1.generator();
                System.out.println(buffer1gen);
                if ( buffer1.equals(check) ){
                    return buffer1.reccord;
                }
                if ( !ram.contains(buffer1gen) ){ // if it same it will discard.
                    ram.add(buffer1gen);
                    operation.add(buffer1);
                }
            }


        }



        return null;
    }
}


class board {
    char[][] data;
    List<String> reccord ;
    int row;
    int col;

    board( char[][] data  ){
        this.data = data.clone();
        this.row = data.length;
        this.col = data[0].length;
        reccord = new ArrayList<>();
    }

    public void MoveUp(int i){
        this.reccord.add("U" + i);
        if (row > 1) {
            char buffer1 = data[0][i];
            for (int j = 0; j < this.row - 1; j++) {
                data[j][i] = data[j + 1][i];
            }
            data[this.row - 1][i] = buffer1;
        }
    }

    public void MoveDown(int i){
        this.reccord.add("D" + i);
        if (row > 1) {
            char buffer1 = data[this.row-1][i];
            for (int j = this.row-1; j >= 1; j--) {
                data[j][i] = data[j - 1][i];
            }
            data[0][i] = buffer1;
        }
    }

    public void MoveLeft(int i){
        this.reccord.add("L" + i);
        if ( col > 1 ){
            char buffer1 = data[i][0];
            for (int j = 0; j < this.col - 1 ; j++) {
                data[i][j] = data[i][j+1];
            }
            data[i][this.col-1] = buffer1;
}
    }

public void MoveRight(int i){

        this.reccord.add("R" + i);
        if ( col > 1 ){
        char buffer1 = data[i][this.col-1];
        for (int j = this.col-1; j >= 1 ; j--) {
        data[i][j] = data[i][j-1];
        }
        data[i][0] = buffer1;
        }
        }

    @Override
    public boolean equals(Object obj) {
        return Arrays.deepToString(data).equals(Arrays.deepToString(((board) obj).data));
    }

    public String generator(){
        return Arrays.deepToString(data);
    }

    public void printer(){
        System.out.println(Arrays.deepToString(data).replace("], ", "]\n")
                .replace("[[", "[").replace("]]", "]"));
    }

    protected board clone1() {
        char[][] datacl = new char[row][col];
        for(int i = 0 ; i < row ; i++ ){
            for (int j = 0; j < col ; j++) {
                datacl [i][j] = data[i][j];
            }
        }
        board cl = new board(datacl);
        for (int i = 0; i < reccord.size() ; i++) {
            cl.reccord.add(this.reccord.get(i));
        }
        return cl;
    }
}

class loopover_test{
    public static void main(String[] args) {
        List<String> ss = Loopover.solve(new char[][]{{'A','C','D','B','E'},
                                                      {'A','C','D','B','E'},
                                                      },
                new char[][]{{'A','B'},
                        {'C','D'},
                        }  );
        System.out.println(ss);
    }
}