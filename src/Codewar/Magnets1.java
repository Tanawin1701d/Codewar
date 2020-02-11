package Codewar;

public class Magnets1 {
    public static double doubles(int maxk, int maxn) {
        double temp = 0;
        for (int k = 1; k <= maxk ; k++) {
            for (int n = 1; n <= maxn ; n++) {
                temp = temp + (double) (1/(k*Math.pow(n+1,2*k)));
            }
        }
    return temp;
    }
}
