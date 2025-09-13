import java.util.Comparator;

class counter{
    int c = 0;

    synchronized void  Increment()
    {
        {
        c++;}
    }

    synchronized void Decrement()
    {
        {
        c--;
        }
    }
}

class testComparator<counter> implements Comparator{

    @Override
    public int compare(Object a , Object b)
    {
        return compare(a ,b );
    }
}

public class Practise {
    public static void main( String args[] ) throws InterruptedException {
        // Thread Class  or Runnable Interface ( Functional Interface )
        counter obj = new counter();

        Thread t1 = new testThread(obj);
        Thread t2 = new Thread( () -> { try {
            for (int i = 0; i < 10000; i++)
                obj.Decrement();
        }
        catch ( Exception e)
        {

        } });


        t1.start();
        t2.start();

        // wait for both threads to end
        t1.join();
        t2.join();

        System.out.println(obj.c);
    }
}

class testThread extends Thread
        {
            counter obj;
            testThread(counter obj )
            {
                this.obj = obj;
            }

            @Override
            public void run()
            {
               for( int i = 0; i < 10000 ; i++ )
                    obj.Increment();
            }
        }