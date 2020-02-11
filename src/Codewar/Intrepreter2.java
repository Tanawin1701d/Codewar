package Codewar;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Intrepreter2 {

    static Map<String, funccompiler> storage = new HashMap<>();
    static Map<String, String>   globals_var = new HashMap<>();
    static List<String> global_operator = Arrays.asList("+","-","*","/","%","(", ")","=");

    static Stack<String> que_to_stack(Deque<String> buffer){
        Stack<String> re = new Stack<>();
        while (!buffer.isEmpty()){
            re.push(buffer.pollLast());
        }
        return re;
    }

    public static boolean isnumberic(String a){
        Pattern number = Pattern.compile("[+-]?[0-9]+(\\.[0-9]+)?");
        Matcher test = number.matcher(a);
        return test.matches();
    }

    private static void savefunc(Stack<String> tokens){
        tokens.pop();
        ArrayList<String> check   = new ArrayList<>();
        Deque<String>     exp     = new ArrayDeque<>();
        String            name    = tokens.pop();
        String            iterate = tokens.pop();
        while ( !iterate.equals("=>") ){
            check.add(iterate);
            iterate = tokens.pop();
        }
        while ( !tokens.isEmpty()){
            String incheck = tokens.pop();
            if (global_operator.contains(incheck) || check.contains(incheck) || isnumberic(incheck) ) {
                exp.add(incheck);
            }else{
                throw new Error("ERROR: Invalid identifier '" + incheck + "' in function body.");
            }
        }
        funccompiler fn = new funccompiler(que_to_stack(exp), check);
        storage.put(name, fn);
    }

    private static Double manager(Deque<String> tokens){
        if (tokens.peek().equals("fn")){
            savefunc( que_to_stack(tokens) );
            return null;
        }else{
            return new compiler(que_to_stack(tokens)).compile();
        }

    }

    public Double input(String input) {
        Deque<String> tokens = tokenize(input);
        return manager(tokens) ;
    }

    private static Deque<String> tokenize(String input) {
        Deque<String> tokens = new LinkedList<>();
        Pattern pattern = Pattern.compile("=>|[-+*/%=\\(\\)]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)");
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }

}

class compiler{

    Stack<String>                     exp ;
    Map<String, String>               var = new HashMap<>();
    private Map<String, funccompiler> storage  = Intrepreter2.storage;
    private List<String>              operator = Intrepreter2.global_operator;

    compiler(){}

    compiler(Stack<String> exp){
        var = Intrepreter2.globals_var;
        posfix(exp);
    }

    public boolean seperate(String a, String b ){
        if ((var.containsKey(a)||a.equals(")") || a.equals("|") || Intrepreter2.isnumberic(a)) && (!b.equals(")") && !b.equals("|") &&  !operator.contains(b)))
            return true;
        else if (storage.containsKey(a)){
            if (storage.get(a).getRq_var() == 0 && (!b.equals(")") && !b.equals("|") &&  !operator.contains(b)))
                return true;
        }
        return false;
    }

    public void seperate_f(Stack<String> exp, tuple a){
        int bracket_op = 0;
        Stack<String> buffer = new Stack<>();
        String iterate = exp.pop();
        while (true){
            if (exp.isEmpty()){
                buffer.add(iterate);
                break;
            }else if (seperate(iterate, exp.peek())){
                buffer.add(iterate);
                break;
            }else if (iterate.equals("(")){
                bracket_op++;
            }else if (iterate.equals(")")){
                if (--bracket_op == 0){
                    buffer.add(iterate);
                    break;
                }else if (bracket_op < 0 ) {
                    exp.push(iterate);
                    break;
                }
            }else if (iterate.equals("|")){
                exp.push(iterate);
                break;
            }else if(operator.contains(exp.peek()) && !exp.peek().equals("(") && bracket_op <=0){
                buffer.add(iterate);
                break;
            }
            buffer.add(iterate);
            iterate = exp.pop();
        }
        exp.add("|");
        while (!buffer.isEmpty()){
            exp.add(buffer.pop());
        }
        a.stop();

    }

    public static int inpri(String a, boolean in){
        List<String> o1 = Arrays.asList("+", "-");
        List<String> o2 = Arrays.asList("*", "/", "%", "=");
        if (o1.contains(a))
            return 1;
        if (o2.contains(a))
            return 3;
        if (a.equals("("))
            return in ? 0 : 5;
        return Integer.MIN_VALUE;
    }

    public void posfix(Stack<String> exp){
        Deque<String> one                 = new ArrayDeque<>();
        Stack<String> two                 = new Stack<>();
        Stack<tuple> three                = new Stack<>();

        while (!exp.isEmpty()){

            String ob = exp.pop();

            if (operator.contains(ob)){
                if (!ob.equals(")")){
                    if (!two.isEmpty()) {
                        if (inpri(two.peek(), true) >= inpri(ob, false)) {
                            one.add(two.pop());
                        }

                    }
                    two.add(ob);
                }else{
                    String iterate = two.pop();
                    while (!iterate.equals("(")){
                        one.add(iterate);
                        iterate = two.pop();
                    }
                }
            }else if (var.containsKey(ob) || Intrepreter2.isnumberic(ob)){
                one.add(ob);
            }else if (storage.containsKey(ob)) {
                int req = storage.get(ob).getRq_var();
                if (req != 0) {
                    two.push("(");
                    three.push(new tuple(ob,req));
                }else{
                    one.push(ob);
                }
            }else if (ob.equals("|")){
                String iterate = two.pop();
                while (!iterate.equals("(")){
                    one.add(iterate);
                    iterate = two.pop();
                }
                one.add(three.pop().getA());
            }else one.add(ob);

            if (!three.isEmpty()) {
                if (three.peek().getB() == 1 ){
                    if (!three.peek().didstop()) {
                        exp.push(ob);
                        seperate_f(exp, three.peek());
                        exp.pop();
                    }
                }else{
                    if (seperate(ob, exp.peek())){
                        String iterate = two.pop();
                        while (!iterate.equals("(")){
                            one.add(iterate);
                            iterate = two.pop();
                        }
                        two.add("(");
                        three.peek().m1();
                    }
                }
            }

        }
        while (!two.isEmpty()){
            one.add(two.pop());
        }
        this.exp = Intrepreter2.que_to_stack(one);

    }

    public double compile(){
        Stack<String> buffer = new Stack<>();
        Stack<String> exp = (Stack<String>) this.exp.clone();
        while(!exp.isEmpty()){
            String pick = exp.pop();
            if (operator.contains(pick)){
                String ys = buffer.pop();
                String xs = buffer.pop();

                if (pick.equals("=")){
                    ys = find_val(ys);
                    var.put(xs, ys);
                    buffer.add(xs);
                }else{
                    ys = find_val(ys);
                    xs = find_val(xs);
                    if (pick.equals("+")){
                        buffer.add(String.valueOf(Double.parseDouble(xs) + Double.parseDouble(ys)));
                    }else if (pick.equals("-")){
                        buffer.add(String.valueOf(Double.parseDouble(xs) - Double.parseDouble(ys)));
                    }else if (pick.equals("*")) {
                        buffer.add(String.valueOf(Double.parseDouble(xs) * Double.parseDouble(ys)));
                    }
                    else if (pick.equals("/")) {
                        buffer.add(String.valueOf(Double.parseDouble(xs) / Double.parseDouble(ys)));
                    }else if (pick.equals("%")) {
                        buffer.add(String.valueOf(Double.parseDouble(xs) % Double.parseDouble(ys)));
                    }
                }
            }else if ( storage.containsKey(pick) ){
                int rq = storage.get(pick).getRq_var();
                String[] sent = new String[rq];
                for (int i = rq-1; i>=0; i--){
                    String check = buffer.pop();
                    sent[i] = find_val(check);
                }
                funccompiler fucclone = storage.get(pick).fclone();
                fucclone.init_var(sent);
                buffer.add(String.valueOf(fucclone.compile()));
            }else buffer.add(pick);
        }
        return Double.parseDouble(find_val(buffer.pop())) ;
    }

    public String find_val(String x){
         while (!Intrepreter2.isnumberic(x)){
             x = var.get(x);
         }
         return x;
    }
}

class funccompiler extends compiler{

    private List<String> varbuffer;
    private int          rq_var;


    funccompiler(Stack<String> posfix, int rq_var, List<String> local_var ){
        super.exp = posfix;
        this.rq_var = rq_var;
        varbuffer = local_var;

    }

    funccompiler(Stack<String> exp, List<String> local_var ){
        varbuffer = local_var;
        rq_var    = varbuffer.size();
        String[] initval = new String[varbuffer.size()];
        for (int i =0; i < rq_var; i++ ){
            initval[i] = "0";
        }
        init_var(initval);
        posfix(exp);
    }

    public void init_var(String[] input){
        Map<String, String> buffer = new HashMap<>();
        if (rq_var == input.length ){
            for(int i = 0; i < input.length; i++ ){
                buffer.put(varbuffer.get(i), input[i]);
            }
        }else{
            throw new Error("Function Overload");
        }
        super.var = buffer;
    }

    public int getRq_var(){
        return rq_var;
    }

    public Stack<String> getExp(){
        return super.exp;
    }

    public List<String> getVarbuffer(){
        return varbuffer;
    }

    public funccompiler fclone(){
        funccompiler buffer = new funccompiler(this.getExp(), this.getRq_var(), this.getVarbuffer());
        return buffer;
    }


}

class tuple {
    String a;
    int    b;
    boolean stop;
    tuple(String a, int b){
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }
    public int getB(){
        return b;
    }

    public boolean didstop(){
        return stop;
    }

    public void stop(){
        stop = true;
    }


    public int m1(){
        b -= 1;
        return b;
    }
}

class main{
    public static void main(String[] args) {
        Intrepreter2 test = new Intrepreter2();
        System.out.println(test.input("fn avg a b => ( a + b ) / 2 "));
        System.out.println(test.input("fn add a => a +1 "));
        System.out.println(test.input("add add add (6) + avg 6 6 "));
    }
}






