package Codewar;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter  {
    
    private database ssh = new database();
    //initialize database

    Interpreter(){
        database.storage =  new HashMap<>() ;
    }

    public Double input(String input) {
        if (input.strip().equals("")){
            return null;
        }
        Deque<String> tokens = tokenize(input);

            try {
                if (ssh.add(tokens)) {
                    return ssh.get("#~buffer");
                }
            }catch ( MathException| ExpressionException| ConflictNameException e){
                throw new IllegalArgumentException();
            }
        return null;
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


class database {
    public static List<String> operator = Arrays.asList("+","-","*","/","%","(","=");
    public static List<String> group1 = Arrays.asList("+","-");
    public static List<String> group2 = Arrays.asList("*","/","%");
    public static String       group3 = "(";
    
    public static Map<String, exp> storage = new HashMap<>();



    public boolean add(Deque<String> raw ) throws ConflictNameException {
        if (raw.peek().equals("fn")){
            raw.poll();
            String name = raw.poll();
            if (storage.containsKey( "~" + name)){
                throw new ConflictNameException("Confliction exception");
            }
            exp data = new Fn(raw, this);
            storage.put(name, data);
            return false;
        }else{
            exp data = new Gi(raw, this);
            storage.put("#~buffer", data);
            return true;
        }


    }

    public double get(String a) throws MathException, ExpressionException, ConflictNameException{
        System.out.println(a);
        try {
            return storage.get(a).compile();
        }catch (ExpressionException | NullPointerException e){
            throw new ExpressionException(a + " isn't defined.");
        }

    }
}

abstract class exp  {
    /////// field
    protected database ssh;
    protected Deque<String> data;

    //////  main medthod;

    protected abstract double        call(String var)  throws MathException, ExpressionException, ConflictNameException;

    protected          Deque<String> posfix_gen(Deque<String> raw) throws ConflictNameException  {
       try {
           Deque<String> silo1 = new ArrayDeque<>();
           Stack<String> silo2 = new Stack<>();
           Stack<Tp> silo3 = new Stack<>();
           String for3 = null;

           while (!raw.isEmpty()) {
               String now = raw.poll();
               String next = "1";
               if (!raw.isEmpty()) {
                   next = raw.peek();
               }
               /////////////// our silo
               if (database.operator.contains(now)) {
                   if (!silo2.isEmpty()) {
                       if (priority(silo2.peek(), now)) {
                           silo1.add(silo2.pop());
                           //System.out.println("silo2 add"+(now));
                           silo2.add(now);
                       } else {
                           //System.out.println("silo2 add"+(now));
                           silo2.add(now);
                       }
                   } else {
                       //System.out.println("silo2 add"+(now));
                       silo2.add(now);
                   }
               } else if (now.equals(")")) {
                   String iter = silo2.pop();
                   while (!iter.equals("(")) {
                       silo1.add(iter);
                       iter = silo2.pop();
                   }
               } else if (now.equals("|")) {
                   String iter = silo2.pop();
                   while (!iter.equals("|")) {
                       silo1.add(iter);
                       iter = silo2.pop();
                   }
               } else if (database.storage.containsKey(now)) {
                   if (database.storage.get(now) instanceof Fn) {
                       if (((Fn) database.storage.get(now)).namevar.size() == 0){
                           silo1.add(""+database.storage.get(now).compile());
                       }else {
                           silo3.push(new Tp((Fn) database.storage.get(now), now, ssh, raw));
                           for (int i = 0; i < ((Fn) database.storage.get(now)).namevar.size(); i++) {
                               silo2.add("|");
                               //System.out.println("silo2 add |");
                           }
                       }
                   } else {
                       silo1.add(now);
                   }
               } else if (isnumberic(now)) {
                   silo1.add(now);
               } else {
                   if (!now.equals("!")) {
                       if (this instanceof Fn) {
                           if (!varcontain(now)) {
                               throw new ConflictNameException("now isn't defined");
                           }
                       }
                       silo1.add(now);
                   }// allow only for this time and will throw on compile
                   // TODO: 10/3/63 throw value error;
               }

               if (!now.equals("|")) {
                   if (!silo3.isEmpty()) {
                       if (silo3.peek().seperable(now, next)) {
                           String iter = silo2.pop();
                           while (!iter.equals("|")) {
                               silo1.add(iter);
                               iter = silo2.pop();
                           }
                           silo1.add(silo3.pop().getName());
                           raw.addFirst("!");
                       }
                   }
               }

           }
           while (!silo2.isEmpty()) {
               silo1.add(silo2.pop());
           }
           System.out.println(silo1);
           return silo1;
       }catch (Exception e){
           throw new ConflictNameException("UNknown");
       }
    }

    public             double        compile() throws MathException, ExpressionException, ConflictNameException{
        System.out.println(data);
        Deque<String> instruction = clone_data();
        Stack<String> silo1 = new Stack<>();

        while (!instruction.isEmpty()){
            String now = instruction.poll();
            if (database.storage.containsKey(now)){ //
                exp buffer1 = database.storage.get(now);
                if ( buffer1 instanceof Fn ){
                    int i = 0;
                    double[] x = new double[ ((Fn) buffer1).namevar.size() ];

                    while ( i < ((Fn) buffer1).namevar.size() && !silo1.isEmpty() ){
                        x[i] = this.call(silo1.pop());
                        i++;
                    }

                    ((Fn) buffer1).matvar(x);

                    silo1.add(String.valueOf(buffer1.compile()));
                }
            }else if (this.isnumberic(now)){
                    silo1.add(String.valueOf(this.call(now)));
            }else if (database.operator.contains(now)){
                if (!now.equals("=")) {
                    double b = this.call(silo1.pop());
                    double a = this.call(silo1.pop());
                    if (now.equals("+")) {
                        silo1.add(String.valueOf(a + b));
                    } else if (now.equals("-")) {
                        silo1.add(String.valueOf(a - b));
                    } else if (now.equals("*")) {
                        silo1.add(String.valueOf(a * b));
                    } else if (now.equals("/")) {
                        if (b != 0) {
                            silo1.add(String.valueOf(a / b));
                        }else{
                            throw new MathException("divider can't be 0");
                        }
                        // todo throw error /0
                    } else if (now.equals("%")) {
                        if (b != 0) {
                            silo1.add(String.valueOf(a % b));
                        }else{
                            throw new MathException("moduler can't be 0");
                        }
                        // todo throw error %0
                    } else {
                        // throw error
                    }
                }else{
                    //System.out.println("+++++++++++++++++++++++++++" + now);
                    double value = this.call(silo1.pop());
                    String var;
                    if (!this.isnumberic(silo1.peek())) {
                        var = silo1.pop();
                    }else{
                        throw new ConflictNameException("= error");
                    }
                    silo1.add(save(var, value));

                }
            } else {
                silo1.add(now);
            }
        }

        if (silo1.size() > 1  ){
            throw new ExpressionException("expresion invalid");
            // todo throw error
        }

        return this.call(silo1.pop());
    }
    
    ////// sub medthod

    public             boolean       isnumberic(String a) {
        Pattern m = Pattern.compile("[+-]?[0-9]+(\\.[0-9]+)?");
        Matcher test = m.matcher(a);
        return test.matches();
    }
    
    public             boolean       priority(String a /*down*/ , String b /* up */){
        List<String> group1 = database.group1;
        List<String> group2 = database.group2;
        String       group3 = database.group3;
        int na,nb;
        if (group1.contains(a)){
            na = 0;
        }else if(group2.contains(a)){
            na = 1;
        }else if (a.equals("(")){
            na = -3 ;
        }else if (a.equals("|")){
            na = -5;
        }else{
            na = -2; // mean equal
        }
        
        if (group1.contains(b)){
            nb = 0;
        }else if(group2.contains(b)){
            nb = 1;
        }else if ( b.equals("(") ){
            nb =  3;
        }else if (b.equals("|")) {
            nb = -5;
        }else{
            nb = -1; // mean equal
        }
        
        return na >= nb; 
        
    }

    public             Deque<String> clone_data(){
        Deque<String> data_buffer = new ArrayDeque<>();
        Deque<String> comp_buffer = new ArrayDeque<>();
        while (!this.data.isEmpty()){
            String save = data.poll();
            data_buffer.add(save);
            comp_buffer.add(save);
        }
        data = data_buffer;
        return comp_buffer;
    }

    public  abstract  String save(String x, double y) throws ConflictNameException;

    public abstract  boolean varcontain(String var);

}

class Fn extends exp{
    public  List<String>        namevar;
    private Map<String, Double> substorage ;

    public Fn(Deque<String> raw, database ssh)throws ConflictNameException{
        super.ssh = ssh;
        this.namevar = new ArrayList<>();
        super.data =   posfix_gen(raw);
    }

    public double call(String var) throws MathException, ExpressionException, ConflictNameException {
        if (isnumberic(var)){
            return Double.parseDouble(var);
        }
        return substorage.get(var);
    }

    public void matvar(double[] input){
        //todo
        // throw an error here
        substorage = new HashMap<String, Double>();
        for (int i = 0; i < namevar.size() ; i++) {
            substorage.put(namevar.get(i), input[i]);
        }
    }

    public String save(String x, double y) throws ConflictNameException {
        this.substorage.put(x, y);
        return ""+y;
    }

    public boolean varcontain(String var){
        return this.namevar.contains(var);
    }

    @Override
    public Deque<String> posfix_gen(Deque<String> raw) throws ConflictNameException{
        String c = raw.poll();
        while (!c.equals("=>")){
            if (namevar.contains(c)){
                throw new ConflictNameException("same var");
            }
            namevar.add(c);
            c =raw.poll();
        }
        return super.posfix_gen(raw);
    }

}

class Gi extends exp{

    public Gi(Deque<String> raw, database ssh) throws ConflictNameException{
        super.data = posfix_gen(raw);
        super.ssh = ssh;
    }
    public double call(String var) throws MathException, ExpressionException, ConflictNameException{
        if (isnumberic(var)){
            return Double.parseDouble(var);
        }
        return ssh.get("~" + var);
    }

    public String save(String x, double y) throws ConflictNameException{
        Deque<String> re = new ArrayDeque<>();
        re.add(String.valueOf(y));
        exp buffer1 = new Gi(re, ssh);
        if ( database.storage.containsKey(x)){
            throw new ConflictNameException("Confliction Exception");
        }
        database.storage.put("~"+x, buffer1);
        return  "" + y;
    }

    public boolean varcontain(String var){
        return database.storage.containsKey("~"+var);
    }
}

class Tp {
    private Deque<String> raw;
    private int remain ;
    private Fn  data;
    private String name;
    private Deque<String> buffer_silo = new ArrayDeque<>();
    private int open_bracket ;
    private database ssh ;

    public Tp(Fn data,String name, database ssh, Deque<String> raw){
        this.ssh  = ssh;
        this.name = name;
        this.data = data;
        this.remain = data.namevar.size();
        this.raw  = raw;
    }

    public Fn getData(){
        return data;
    }

    public String getName(){
        return name;
    }

    public boolean reduce(){
        remain --;
        return remain == 0;
    }

    public boolean seperable(String now, String next){
        buffer_silo.add(now);
        if (now.equals("(")){
            open_bracket++;
            return false;
        }else if (now.equals(")")){
            open_bracket--;
        }

        if ( open_bracket == 0 ){
           if ( remain == 1 && !now.equals(name) ){
               if (!next.equals("=") && !now.equals("=")) {
                   //raw.addFirst("|");
                   return true;
               }
           }else if ( remain > 1 ) {
                if (!database.operator.contains(now) && database.operator.contains(next)){

                }else if ( database.operator.contains(now) ){

                } else if (!now.equals(name)){
                    raw.addFirst("|");
                    reduce();
                }
               /*
               data.isnumberic(now);
                if ((now.equals(")") || data.isnumberic(now) ) && !database.operator.contains(next)){
                    reduce();
                }else if (ssh.storage.containsKey(now) || ssh.storage.containsKey("~"+now) ){
                    exp first = ssh.storage.get(now);
                    if (first instanceof Fn){
                        if ( ((Fn) first).namevar.size() == 0 ){
                            if (!database.operator.contains(next)){
                                reduce();
                            }
                        }
                    }else {
                        if (!database.operator.contains(next)){
                            reduce();
                        }
                    }
                }

                */

           }else if ( remain == 0 ){
               //raw.addFirst("|");
               return true;
           }else {
               // todo throw error expression wrong.
           }

        }else if ( open_bracket < 0 ){
            // todo throw error expression wrong.
        }

        return false;

    }
}

class ExpressionException extends Exception{
    public String message;
    public ExpressionException( String message){
        this.message = message;
    }
}

class MathException extends Exception{
    public String message;
    public MathException( String message){
        this.message = message;
    }
}

class ConflictNameException extends Exception{
    public String message;
    public ConflictNameException( String message){
        this.message = message;
    }
}

class test_Interpreter{
    public static void main(String[] args)throws MathException, ExpressionException, ConflictNameException  {
        Deque<String> news = new ArrayDeque<>();
        news.add("2");news.add("+");news.add("3");news.add("*");news.add("5");
        database ru = new database();
        exp example = new Gi( news , ru );
        System.out.println(example.isnumberic("-12"));
        System.out.println("--------------priority test------------------");
        System.out.println(example.priority("/","+"));
    }
}

class test_ALL{
    public static void main(String[] args) throws MathException, ExpressionException, ConflictNameException   {
        Interpreter test = new Interpreter();
       System.out.println(test.input("fn avg x y => x * y*2+ 1 "));
        //System.out.println(test.input("xa = (2+3)"));
        //System.out.println(test.input("ya = 2"));
        //System.out.println(test.input("x = 13 + (y = 3)"));
        //System.out.println(test.input("x"));
        //System.out.println(test.input("avg xa xb = 2 + ya +5"));
        //System.out.println(test.input("avg xa xb = 2 + ya +5"));
        //System.out.println(test.input("x = y = ( 1 + 6)"));
        //System.out.println(test.input("x = x + 3"));
        //System.out.println(test.input("fn add x => x + 1 "));
        //System.out.println(test.input("avg add (add 1 + (x = 2 + 3) ) 2 + 3 "));
        //System.out.println(test.input("10 / 0 "));
        //System.out.println(test.input("10 + 0 "));
        //int y = 1%0;
        //int x = 4/5;
        System.out.println(test.input("fn x => 0 "));
        System.out.println(test.input("x"));
    }
}

