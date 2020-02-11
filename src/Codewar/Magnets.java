package Codewar;
public class Magnets {
    private static int K,N ;
    private static int k,n;
    private static double temp = 0;
    private static void u() {
        if(n < N ) {
            n = n+1;
            Double check = 1/(k*Math.pow(n+1,2*k)) ;
            if (check > 10e-9) {
                temp = temp + check;
                System.out.println(n);
                System.out.println(temp);
                u();
            }
        }
    }

    private static double s(){
        if(k < K ) {
            temp = 0 ;
            n = 0;
            k = k +1;
            u();
            return temp + s();
        }
        return 0d;
    }
    public static double doubles(int maxk, int maxn) {
        K = maxk;
        N = maxn;
        k = 0 ;
        return s() ;
    }
}
