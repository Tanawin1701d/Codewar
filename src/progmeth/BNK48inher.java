package progmeth;

import java.time.LocalDateTime;

public class BNK48inher {
    public static void main(String[] args) {

        // only super class //
        Member oil_staff = new Member("ammriss", (short)22, "6/3/1997", "A", "Thee_line");
        System.out.println(oil_staff);
        System.out.println(oil_staff.salary());

        // interface is superclass
        Member view = new sembustu("View", (short)22, "6/3/1997", "A", "Thee_line", (short)1, 845000, 60000);
        System.out.println(view);
        System.out.println(view.salary());

        // interface is subclass
        sembustu jennis = new sembustu("jennis", (short)22, "6/3/2000", "A", "Thee_line", (short)1, 845000, 60000);
        System.out.println(jennis);
        System.out.println(jennis.salary());
        // interface is subclass     data is upperclass
        //  Eror  :     sembustu oom = new Member("Omm", (short)22, "6/3/2000", "A", "Thee_line");
        System.out.println(jennis);
        System.out.println(jennis.salary());
        System.out.println(jennis.hashCode());

    }
}

class Member{
    private String name;
    private short  age;
    private String born ;
    private String blood_group;
    private String address;
    protected int salary = 9000;

    public Member(String name, short age, String born, String blood_group, String address) {
        this.name = name;
        this.age = age;
        this.born = born;
        this.blood_group = blood_group;
        this.address = address;
    }

    public Member(String name) {
        this(name, (short) 0,"" ,"","");
    }

    public Member(){
        this("", (short) 0,"","","");
    }

    public int salary(){

        return salary;
    }

    public String toString(){
        return name + ":" + age + ":" + born + ":" + blood_group + ":" + address;
    }

}

class sembustu extends Member{
    private short pos;
    private int   follower;
    private int   sell_photoset;


    public sembustu(String name, short age, String born, String blood_group, String address
            , short pos, int follower, int sell_photoset) {
        super(name, age, born, blood_group, address);
        this.pos = pos;
        this.follower = follower;
        this.sell_photoset = sell_photoset;
    }

    public sembustu(String name) {
        this(name,(short) 0, "", "", "", (short) 32, 0, 1);
    }

    public sembustu() {
        this("");
    }

    @Override
    public int salary() {
        return (int)(super.salary * 0.9*sell_photoset*follower) ;
    }

    @Override
    public String toString(){
        return  "sem N0 : "+pos+" : " + super.toString();
    }

    public int getFollower(){
        return follower;
    }

}

