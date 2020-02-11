package Codewar;

public class Matrix {

    public static int[][] minor(int[][] m, int icut , int jcut ){
        int k = m.length-1;
        int h = 0 , u = 0;
        int[][] ret = new int[k][k];
        for (int i = 0; i < k+1; i++) {
            h = 0;
            for(int j = 0;j < k+1;j++){
                if (i != icut) {
                    if (j != jcut){
                        ret[i-u][j-h] = m[i][j];
                    }
                    else {
                        h = 1;
                    }
                }else {
                    u = 1;
                }
            }
        }
        return ret;
    }

    public static int det(int[][] m){
        int k = m.length;
        int det = 0;
        if(k>1) {
            for (int i = 0; i < k; i++) {
                    det = det + (int) Math.pow(-1, i) * m[i][0] * det(minor(m, i, 0));
            }
        }else det = m[0][0];

        return det;
    }


    public static int determinant(int[][] matrix) {
        System.out.println(det(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        return 0;
    }
}
