package Codewar;

import java.util.*;
import java.util.stream.Collector;

public class  Lcs {
    // public static String lcs (String x , String y){
    public static void subset(){
        char[] test = {'a','b','c','d','e'};
        int lim = test.length;
        for (int i = 0 ; i < (1 << lim  );i++){
            System.out.print("{ ");
            for(int j=0 ; j<lim;j++){
                if((i & (1<<j)) >0){
                    System.out.print(test[j]);
                }
            }
            System.out.println("}");
        }
    }

    public static void subsetrev(String x , String y ){

        x = "abcde";
        y = "defg";
        int lim = x.length();
        String[] save  = new String[(1<<lim)];
        for (int i = 0; i < (1<<lim)  ; i++) {
            save[i] = "";
        }

        System.out.println("-----------Unsorted--------------");
        for (int i = (1<<lim)-1; i >=0 ; i--) {
            for(int j=0;j<lim;j++){
                if((i & (1<<j)) > 0)
                    save[i] = save[i] + x.charAt(j);
            }
            System.out.println(save[i]);
        }


        System.out.println("----------------sorted-------------------------");


        Arrays.sort(save,digit());
        for (int i = 0; i < (1<<lim) ; i++) {
            System.out.println(save[i]);
        }

        System.out.println("----------------finding progresion------------------");


        int i = 0;
        int sa = -1;
        boolean select = false;
        while(select == false && i<(1<<lim)) {
            int temp = -1;
            boolean skip = false;
            for (int j = 0; j < save[i].length() ; j++) {
                System.out.println("---i="+i+"---j="+j+"");
                for (int k = temp+1; k < y.length() ; k++) {
                    System.out.println("---k="+k+"");
                    if(save[i].charAt(j)==y.charAt(k)){
                        temp = j;
                        System.out.println("temp = "+temp);
                        if (j==save[i].length()-1){
                            ;
                            select = true;
                            sa = i;
                        }
                        break ;
                    }
                    else if (save[i].charAt(j)!=y.charAt(k)  && k==y.length()-1){
                        System.out.println("into skip system");
                        skip = true;
                    }
                }
                if (skip == true){
                     break;
                }
            }
            i++;
        }

        String result = sa==-1 ? "":save[sa];
        System.out.println(result);



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