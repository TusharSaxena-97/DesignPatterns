package Singleton.BillPugh;

public class BillPugh {
    public static void main( String args[])
    {
        int n = 100000;
        for( int i = 0; i < n ; i++ )
        {
            Thread t = new Thread(new Application());
            t.start();
        }
    }
}

class Application implements Runnable
{
    @Override
    public void run() {
        DataBase obj = DataBase.getInstance();
        System.out.println( obj.hashCode() );
    }
}

class DataBase{
    private static volatile DataBase Instance;

    private DataBase() {
      System.out.println("DataBase Object is being Created");
    }
    public static DataBase getInstance()
    {
        if( Instance == null ) {
            synchronized (DataBase.class) {
                if( Instance == null )
                    Instance = new DataBase();
            }
        }
        return Instance;
    }
}
