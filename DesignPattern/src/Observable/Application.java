package Observable;

public class Application {
    public static void main(String args[])
    {
        Channel mb = new Channel("MrBeast");
        Channel pc = new Channel("YesTheory");

        Subscriber s1 = new Subscriber("John Cena");
        Subscriber s2 = new Subscriber("Ray Mysterio");
        Subscriber s3 = new Subscriber("Brock Lesnar");
        Subscriber s4 = new Subscriber("Triple H");

        mb.Subscribe(s1);
        mb.Subscribe(s2);
        mb.Subscribe(s3);
        mb.Subscribe(s4);

        s1.subscribe(mb);
        s2.subscribe(mb);
        s3.subscribe(mb);
        s4.subscribe(mb);

//        s2.subscribe(pc);
//        s4.subscribe(pc);

        mb.addVideo("100 million Give Away !!!!");
        //pc.addVideo("Travelling to Iceland in 1000 Dollars");
    }
}
