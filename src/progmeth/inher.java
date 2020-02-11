package progmeth;


class a{
    int b = 6;
    a(){
        System.out.println(b);
    }

}

class c extends a{
    private int d = 7;
    c(){
        System.out.println(d);
    }

    public void printer(){

    }
    public void test(){
        System.out.println("we are in c");
    }

}

class main {
    public static void main(String[] args) {
        a view = new c();
       // view.test();
    }
}

