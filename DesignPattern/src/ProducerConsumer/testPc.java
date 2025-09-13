package ProducerConsumer;

import java.util.Stack;

public class testPc {
    public static void main( String a[]) throws Exception
    {
        Buffer buffer = new Buffer();

        Thread t1 = new Thread( new producer(buffer) );
        Thread t2 = new Thread( new consumer(buffer) );

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class producer implements Runnable{

    Buffer Buffer;

    producer(Buffer buffer)
    {
        this.Buffer = buffer;
    }

    @Override
    public void run() {
        try {
            Buffer.produce();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

class consumer implements Runnable{

    Buffer buffer;

    consumer( Buffer buffer ){
        this.buffer = buffer;
    }

    @Override
    public void run()
    {
       try{
           buffer.consume();
       }
       catch( Exception e )
       {
           System.out.println(e.getMessage());
       }
    }
}



class Buffer{
    final int capacity = 10;
    Stack<Integer> buffer = new Stack<>();

    void produce() throws InterruptedException {
        int value = 1;
        while( true ) {
            synchronized ( this ) {

                if( buffer.size() == capacity )
                {
                    System.out.println(" The buffer is full , producer is waiting ");

                    notifyAll();
                    wait();
                }

                while (buffer.size() < capacity)
                {
                    buffer.push(value++);
                    System.out.println(" Added  " + (value - 1 )) ;
                }

                Thread.sleep(2000);
                notifyAll();
            }
        }
    }

    void consume() throws InterruptedException
    {
        while (true) {
            synchronized (this) {
                if( buffer.size() == 0 )
                {
                    System.out.println(" the buffer is empty now, consumer is waiting");

                    notifyAll();
                    wait();
                }

                while (buffer.size() > 0) {
                    System.out.println(" Consuming " +  buffer.pop() );
                }

                Thread.sleep(2000);
                notifyAll();
            }
        }
    }
}
