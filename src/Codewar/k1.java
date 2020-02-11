package Codewar;

public class k1 {
    public int k(int a,int b) {
        if (a == b || a == 1) {
            System.out.println("return1");
            return 1;
        } else if (b > a) {
            System.out.println("return0");
            return 0;
        } else {
            int ret = b * k(a - b, b) ;
            System.out.println("return ret" + ret);
            int ret1 = (a - b + 1) * k(a - b + 1, b - 1);
            System.out.println("return ret" + ret1);
            System.out.println("return ret++" + ret+ret1 );
            return ret+ret1;
        }
    }

    public static void sigma () {
        System.out.println(new k1().k(3, 2));
    }
}
