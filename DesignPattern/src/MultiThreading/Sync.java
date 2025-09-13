package MultiThreading;

class generateId{
    public int c = 0;

    public synchronized int incrementId()
    {
        synchronized( this ){
            c += 1;
        }
        return c;
    }

    public int decrementId()
    {
        for( int i = 0; i < 1000 ; i++ )
            c--;

        return c--;
    }
}

public class Sync {
    public static void main( String a[]) throws Exception
    {
        generateId obj = new generateId();

        Thread t1 = new Thread( () -> { for( int i = 0; i < 10000 ; i++ ) { obj.incrementId(); }} );

        Thread t2 = new Thread( () -> { for( int i = 0; i < 10000 ; i++ ) { obj.incrementId(); }} );

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println( obj.c );
    }
}
