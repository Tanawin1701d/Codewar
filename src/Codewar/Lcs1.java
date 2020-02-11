package Codewar;

import java.util.Arrays;
import java.util.Comparator;

public class Lcs1 {
    public static String subsetrev(String x , String y ){
        x = "abcde";
        y = "defg";
        int lim = x.length();
        int lim1 = y.length();
        String[] save  = new String[(1<<lim)];
        String[] save1 = new String[1<<lim1];

        for (int i = 0; i < (1<<lim)  ; i++) {
            save[i] = "";
        }
        for (int i = 0; i < (1<<lim1) ; i++) {
            save1[i] = "" ;
        }

        for (int i = (1<<lim)-1; i >=0 ; i--) {
            for(int j=0;j<lim;j++){
                if((i & (1<<j)) > 0)
                    save[i] = save[i] + x.charAt(j);
            }
            System.out.println(save[i]);
        }
        System.out.println("----------------------------2---------------------");
        for (int i = (1<<lim1)-1; i >=0 ; i--) {
            for(int j=0;j<lim1;j++){
                if((i & (1<<j)) > 0)
                    save1[i] = save1[i] + y.charAt(j);
            }
            System.out.println(save1[i]);
        }
        System.out.println("----------------sorted-------------------------");


        Arrays.sort(save,digit());
        Arrays.sort(save1,digit());

        for (int i = 0; i < (1<<lim)  ; i++) {
            System.out.println(save[i]);
        }
        System.out.println("---------------sorted2----------------------");
        for (int i = 0; i < (1<<lim1)  ; i++) {
            System.out.println(save1[i]);
        }

        int i=0 ;
        boolean stop = false;
        for ( i = 0; i < (1<<lim)  ; i++) {
            for (int j = 0; j < (1<<lim1) ; j++) {
                System.out.println(i+"    "+j);
                if(save[i].equals(save1[j])){
                    System.out.println("intp stop");
                    stop = true;
                    break;
                }
            }
        if (stop == true){
            break;
        }
        }

        return stop==true ? save[i] : "" ;


    }

    public static Comparator digit() {
        Comparator cmp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()<=o2.length() ? 1 : -1 ;
            }
        };
        return cmp ;
    }
    //public static Collector digit
}

