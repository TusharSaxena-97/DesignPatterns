package OOPS;

class Square{

}
class Rectangle extends Square{

}

class Parent{
    Parent()
    {

    }

    public int x = 10;
    public int getX() {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }

    protected Rectangle func() {
        System.out.println("func parent ");
        return new Rectangle();
    }
}

public class Test  {
    public static void main( String args[])
    {
       Parent p = new child();
       
       p.func();
       System.out.println(p.x);
       
    }
}

class child extends Parent {

    public int x = 2000;

    child()
    {
        //System.out.println( " child constructor");
        super();
    }

    @Override
    public Rectangle func()
    {
        return super.func();
    }
}
