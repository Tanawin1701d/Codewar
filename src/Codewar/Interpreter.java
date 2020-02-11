//package Codewar;
//
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Interpreter {
//
//    // main module
//    class module{
//        String        name;
//        int           reqvar;
//        Queue<String> exp;
//        boolean       isimportant;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setIsimportant(boolean isimportant) {
//            this.isimportant = isimportant;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setReqvar(int reqvar) {
//            this.reqvar = reqvar;
//        }
//
//        public void setExp(Queue<String> exp) {
//            Queue<String> buffer = new ArrayDeque<>();
//            while (!exp.isEmpty()){
//                buffer.add(exp.poll());
//            }
//            this.exp = buffer;
//        }
//
//        public boolean equals(module y) {
//            return name.equals(y.getName());
//        }
//    }
//
//    // field //
//    private Set<module> func;
//    private static List<String> operator  = Arrays.asList("+", "-", "*", "/", "%", "(");
//    private static List<String> important = Arrays.asList("*", "/", "%");
//
//    // posfix converter //
//    private static Queue<String> posfix(Queue<String> a){
//        Queue<String> re     = new ArrayDeque<>();
//        Stack<String> main   = new Stack<>();
//        Stack<String> buffer = new Stack<>();
//        int           hold   = 0;
//
//        while (!a.isEmpty()){
//            String now = a.poll();
//            if (operator.contains(now)){
//                buffer.add(now);
//            }else if ( now.equals(")")){
//                String check = a.poll();
//                while (!check.equals("(")){
//                    main.add(check);
//                }
//            }else{
//                main.add(now);
//                if ( important.contains(a.peek()) ){
//                    main.add(a.poll());
//                }
//            }
//        }
//
//        while (!main.isEmpty()){
//            re.add(main.pop());
//        }
//        return re;
//
//    }
//
//    // save func //
//    private static Double savefunc(Queue<String> f){
//        module buffer = new module()
//        return 0d;
//    }
//
//    // compiler //
//    private static Double compiler(Queue<String> func, boolean mode /*local or func*/){
//
//
//        return null ;
//    }
//
//    // manager //
//    private static Double manager(Deque<String> tokens){
//        return 0d;
//    }
//
//    public Double input(String input) {
//        Deque<String> tokens = tokenize(input);
//        /*String[] p = (tokens.toArray(String[]::new));
//        System.out.println(Arrays.toString(p));*/
//        return null;
//    }
//
//    private static Deque<String> tokenize(String input) {
//        Deque<String> tokens = new LinkedList<>();
//        Pattern pattern = Pattern.compile("=>|[-+*/%=\\(\\)]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)");
//        Matcher m = pattern.matcher(input);
//        while (m.find()) {
//            tokens.add(m.group());
//        }
//        return tokens;
//    }
//
//    public static void main(String[] args) {
//        Interpreter view = new Interpreter();
//        view.input("fn x,y => (x + 76)");
//    }
//}
