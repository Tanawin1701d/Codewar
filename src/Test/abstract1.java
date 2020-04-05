package Test;

abstract class abstract1 {

    public void say(){
        System.out.println("test");
    }

}


class children extends abstract1{
    public void say(){
        System.out.println("I'am children");
    }
}

class bnk extends abstract1{
    public void say(){
        System.out.println("bnk");
    }

    public void general_election(){
        System.out.println("Thank you");
    }
}

class children1 extends abstract1{
    public void say(){
        System.out.println("bye");
    }
}

class absmain{
    public static void main(String[] args) {
        children jennis =  new children();
        bnk      view   = new bnk();
        jennis.say();
        abstract1[] arr = new abstract1[12];
        arr[0] = jennis;
        arr[1] = view;
        view.general_election();

    }
}
