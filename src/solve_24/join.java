package solve_24;

public class join {
    public static String joiner(int[] number ){
        StringBuilder re = new StringBuilder("") ;
        for (int e:number) {
            re.append(e).append(",");
        }
        return re.substring(0,re.length()-1);
    }
}
