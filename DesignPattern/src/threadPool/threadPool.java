package threadPool;

import java.util.concurrent.*;

public class threadPool {
    public static void main( String args[] )
    {
        ExecutorService executor = new ThreadPoolExecutor(
                1 ,
                3 ,
                1,
                TimeUnit.MINUTES ,
                new ArrayBlockingQueue<Runnable>(5) ,
//              new customThreadFactory(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy() );

        for( int i = 0; i < 4 ; i++ )
        {
            int finalI = i;
            Future<?> obj = executor.submit( ( ) -> {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Running Task " + finalI + " by thread " + Thread.currentThread().getName() );
            } );
            try {
                System.out.println(obj.get());
            }
            catch (Exception e)
            {}
        }
        executor.shutdown();
    }
}

class customThreadFactory implements ThreadFactory
{
    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread( r );
       //th.setName("Tushar Thread ");
        return th;
    }
}

class customRejectHandler implements RejectedExecutionHandler
{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
    {
        System.out.println("Unable to print thread " + r.toString());
        r.run();
    }
}

class myThreadPool extends ThreadPoolExecutor
{
    public myThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if ( t == null && r instanceof Future<?>) {
            try {
                ((Future<?>) r).get();
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            } catch (CancellationException ce) {
                t = ce;
            }
        }
        if (t != null) {
            // Log the exception, send alert, etc.
            //logger.error("Task in thread pool failed", t);
        }
    }
}