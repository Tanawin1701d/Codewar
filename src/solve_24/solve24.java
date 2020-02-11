package solve_24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class solve24 {
    private static Map<String, module_w> dynamic = new HashMap<>();

    public static void Mapadder(String name, float value, String oper){
        if (dynamic.containsKey(name)){
            dynamic.get(name).add(value,oper);
        }else{
            dynamic.put(name, new module_w(value, oper));
        }
    }

    public static void operation(int[] data, boolean last){
        String name = join.joiner(data);
        int start = 0;
        int stop  = data.length-1;
        if (start == stop){
            Mapadder(name, (float)data[start], name);
        }else {


            for (int i = 0; i < data.length - 1; i++) {
                int back  = i+1;
                String p1 = join.joiner(Arrays.copyOfRange(data,0,back));
                String p2 = join.joiner(Arrays.copyOfRange(data,back,stop+1));
                module_w p1m = dynamic.get(p1);
                module_w p2m = dynamic.get(p2);

                for (float a : p1m.getval()) {
                    for (float b : p2m.getval()){

                        // sum
                        float sum = a + b ;
                        for (String c : p1m.getexp(a)) {
                            for (String d : p2m.getexp(b)){
                                Mapadder(name, sum, "(" + c + "+" + d + ")");
                                if (last && sum == 24)
                                    System.out.println("(" + c + "+" + d + ")");
                            }
                        }

                        // minus

                        float minus = a - b ;
                        for (String c : p1m.getexp(a)) {
                            for (String d : p2m.getexp(b)){
                                Mapadder(name, minus, "(" + c + "-" + d + ")");
                                if (last && minus == 24)
                                    System.out.println("(" + c + "-" + d + ")");
                            }

                        }

                        // multiple

                        float mul = a * b ;
                        for (String c : p1m.getexp(a)) {
                            for (String d : p2m.getexp(b)){
                                Mapadder(name, mul, "(" + c + "x" + d + ")");
                                if (last && mul == 24)
                                    System.out.println("(" + c + "x" + d + ")");
                            }
                        }

                        // divide
                        if (b != 0) {
                            float div = a / b;
                            for (String c : p1m.getexp(a)) {
                                for (String d : p2m.getexp(b)) {
                                    Mapadder(name, div, "(" + c + "/" + d + ")");
                                    if (last && div == 24)
                                        System.out.println("(" + c + "/" + d + ")");
                                }
                            }
                        }
                    }
                }



            }
        }
    }
    public static void main(String[] args) {

//        for (int i = 1; i <= 4 ; i++) {
//            int[][] superbuffer = Permutator.permutation(new int[] {1,2,3,4,0}, i);
//            for (int j = 0; j < superbuffer.length ; j++) {
//                operation(superbuffer[j], i == 4);
//            }
//        }
        Permutator1.permutation(new int[]{1,2,3,4,0});
    }
}
