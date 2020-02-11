package Codewar;

import java.util.Arrays;
import java.util.List;

public class InfiniteDigitalString {

    private static boolean is_99(int select, StringBuilder test){
        int c = 0;
        for(int i = 0; i< test.length(); i++){
            if (test.charAt(i) != '9'){
                return false;
            }
            c++;
        }
        return c==select-1;
    }

    private static boolean check(StringBuilder pf, String back) {
        int start = 0;
        int bdigit = back.length();
        String buffer = pf.toString();
        while (start < back.length()) {
            buffer = String.valueOf(Long.parseLong(buffer.toString()) + 1);
            if (bdigit - start >= buffer.length()) {
                if (back.indexOf(buffer, start) != start) {
                    return false;
                }
            }else if (bdigit - start < buffer.length()){
                if (buffer.indexOf(back.substring(start)) != 0){
                    return false;
                }
            }
            start += buffer.length();
        }
        return true;
    }

    private static long digiter(long n, long d ){
        long sum = 0 ;
        int n_l  = String.valueOf(n).length();
        for (long i = 1; i < n_l; i++ ){
            sum += 9*(Math.pow(10,i-1))*i;
        }
        sum += n_l*(n-Math.pow(10,n_l-1))+d;
        return sum;
    }

    public static long findPosition(final String s) {
        List<Character> data = Arrays.asList('2','3','4','5','6','7','8','9');
        long[] view          = {Long.MAX_VALUE, Long.MAX_VALUE};
        long[] result        = {Long.MAX_VALUE, Long.MAX_VALUE};
        int digit            = s.length();
        int select;
        int raw_l ;

        for (select = 1; select <= digit+1; select++){
            for (int lost = 0; lost < select ; lost++) {
                StringBuilder pf     = new StringBuilder();
                pf.append(s, 0, select-lost);
                raw_l = pf.length();


                if (is_99(select,pf)){

                    if (digit >= select-lost+2){
                        if ( s.substring(select-lost,select-lost+2).equals("10") ){
                            pf.insert(0,"9");
                            if (check(pf,s.substring(select-lost))){
                                view[0] = Long.parseLong(pf.toString());
                                view[1] = pf.length() - raw_l;
                            }
                        }
                    }
                    if (digit > select-lost ){
                        if (data.contains(s.charAt(select-lost))){
                            pf.insert(0, Long.parseLong(s.substring(select - lost, select - lost + 1)) - 1);
                            if (check(pf,s.substring(select-lost))){
                                view[0] = Long.parseLong(pf.toString());
                                view[1] = pf.length() - raw_l;
                            }
                        }
                    }
                }else{
                    if (digit > select-lost){
                        if (lost <= digit - (select-lost) ){
                            if (s.charAt(select-lost) != '0' && !(lost == 0 && pf.charAt(0) == '0')){
                                String inserts = s.substring(select-lost, select-lost + lost);
                                if (is_99(pf.length()+1, pf) && lost > 0){         // ไม่กลัวลบเป็น 0 เหรอ   มันไปเข้าตัวบนก่อนเเล้ว
                                    inserts = String.valueOf(Long.parseLong(inserts)-1);
                                }
                                if (inserts.length() == lost){
                                    pf.insert(0,inserts);
                                    if (check(pf,s.substring(select-lost))){
                                        view[0] = Long.parseLong(pf.toString());
                                        view[1] = pf.length() - raw_l;
                                    }
                                }
                            }
                        }
                    }
                    else{
                        if (Long.parseLong(pf.toString()) == 0){
                            pf.insert(0,"1");
                        }
                        if (pf.charAt(0) != '0') {
                            view[0] = Long.parseLong(pf.toString());
                            view[1] = pf.length() - raw_l;
                        }
                    }
                }
                if (view[0] < result[0]){
                    result[0] = view[0];
                    result[1] = view[1];
                }
            }
            if (result[0]< Long.MAX_VALUE){
                break;
            }
        }
        System.out.println(result[0]);
        return digiter(result[0], result[1]) ;
    }

    public static void main(String[] args) {
        //System.out.println(check(new StringBuilder("120"), "1"));
        //System.out.println(is_99(2,new StringBuilder("9")));
        System.out.println(findPosition("555899959741198"));
        //System.out.println(digiter(45,0));
    }

}