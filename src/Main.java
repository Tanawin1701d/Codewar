import Codewar.*;
import Test.*;
public class Main {
    public static void main(String[] args){
        //Lcs.subsetrev("","");
        //Matrix_test.hr();
        //staticTest.test();
        //staticTest.test();
//        int[][]x = Matrix.minor(new int[][] {{1,2,3,5,6},{4,5,6,7,8},{7,8,9,10,11},{15,16,17,18,19},{20,21,22,23,24}},2,1);
//        int[][] a = {{1,2,3},{4,5,6},{7,8,9}};
//        for (int i = 0;i<4;i++) {
//            for(int j = 0 ; j<4; j++){
//                System.out.print(x[i][j]);
//            }
//            System.out.println();
//        }
//          Matrix.determinant(new int[][]{{0}});
       //System.out.println(Magnets.doubles(10,10000));
//        System.out.println(1/Math.pow(1000,10));
//        System.out.println(Magnets1.doubles(10,10000));
//        String d = "..........\n" + ".........." ;
//        d = d.replace("\n","");
//        System.out.println(d.length());
//        System.out.println(d);
//        Finder.pathFinder("......\n"+
//                "......\n"+
//                "......\n"+
//                "......\n"+
//                ".....W\n"+
//                "....W.");
        Maze.escape(new char[][] {
                "#########################################".toCharArray(),
                "#<    #       #     #         # #   #   #".toCharArray(),
                "##### # ##### # ### # # ##### # # # ### #".toCharArray(),
                "# #   #   #   #   #   # #     #   #   # #".toCharArray(),
                "# # # ### # ########### # ####### # # # #".toCharArray(),
                "#   #   # # #       #   # #   #   # #   #".toCharArray(),
                "####### # # # ##### # ### # # # #########".toCharArray(),
                "#   #     # #     # #   #   # # #       #".toCharArray(),
                "# # ####### ### ### ##### ### # ####### #".toCharArray(),
                "# #             #   #     #   #   #   # #".toCharArray(),
                "# ############### ### ##### ##### # # # #".toCharArray(),
                "#               #     #   #   #   # #   #".toCharArray(),
                "##### ####### # ######### # # # ### #####".toCharArray(),
                "#   # #   #   # #         # # # #       #".toCharArray(),
                "# # # # # # ### # # ####### # # ### ### #".toCharArray(),
                "# # #   # # #     #   #     # #     #   #".toCharArray(),
                "# # ##### # # ####### # ##### ####### # #".toCharArray(),
                "# #     # # # #   # # #     # #       # #".toCharArray(),
                "# ##### ### # ### # # ##### # # ### ### #".toCharArray(),
                "#     #     #     #   #     #   #   #    ".toCharArray(),
                "#########################################".toCharArray()
        }).toArray();

    }
}
