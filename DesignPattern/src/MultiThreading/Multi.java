package MultiThreading;

// Thread Class or Runnable interface

class makeFood extends Thread {

    public String foodName;

    makeFood( String foodName)
    {
        this.foodName = foodName;
    }

    @Override
    public void run()
    {
        for( int i=0 ; i < 100 ; i++ )
            System.out.println(" Cooking " + foodName + " by thread " + Thread.currentThread().getName() );
    }
}

class makeItalianFood implements Runnable {

    public String foodName;

    makeItalianFood(String foodName )
    {
        this.foodName = foodName;
    }

    public void run()
    {
        {
            for( int i = 0 ; i < 100 ; i++ )
                System.out.println(" Cooking " + foodName + " by thread " + Thread.currentThread().getName() );
        }

    }
}

public class Multi {
    public static void main(String a[])
    {
        Thread t1 = new makeFood("Samosa");
        Thread t2 = new makeFood("Kachori");
        Thread t3 = new makeFood("Jalebi");

//        t1.start();
//        t2.start();
//        t3.start();

        Thread t4 = new Thread( new makeItalianFood("Pasta"));
        Thread t5 = new Thread( new makeItalianFood("Rissoto"));
        Thread t6 = new Thread( new makeItalianFood("Pizza"));

        t4.start();
        t5.start();
        t6.start();


    }
}
