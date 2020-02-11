package Link_list;

public class Node {
    Object data ;
    Node   next ;

    public Node(){
        next = null;
    }

    public Node(Object newdata){
        data = newdata;
    }

    public Node(Object newdata , Node newNode){
        data = newdata;
        next = newNode;
    }

    public void SetNext(Node newNode){
        next = newNode;
    }

    public void SetData(Object newdata){
        data = newdata;
    }

    public Object GetData(){
        return data;
    }

    public Node GetNext(){
        return next;
    }

}
