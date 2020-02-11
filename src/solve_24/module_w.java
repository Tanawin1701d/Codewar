package solve_24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class module_w {
    private Map<Float, List<String>> val = new HashMap<>();


    public module_w(float value, String exp){
        List<String> adder = new ArrayList<>();
        adder.add(exp);
        val.put(value, adder);
    }

    public void add(float value,String exp){
        if (containv(value)){
            val.get(value).add(exp);
        }else{
            val.put(value, new ArrayList<>());
            val.get(value).add(exp);
        }
    }

    public float[] getval(){
        float[] re = new float[val.size()] ;
        int i = 0;
        for (float a :val.keySet()) {
            re[i++] = a;
        }
        return re;
    }

    public List<String> getexp(float value){
        return val.get(value) ;
    }


    private boolean containv(float value)
    {
        return val.containsKey(value);
    }

    public List<String> containr(float result){
        if (containv(result)){
            return val.get(result);
        }else {
            return null;
        }
    }


}
