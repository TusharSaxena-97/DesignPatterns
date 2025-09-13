package Singleton;

public class Singleton {
    public static void main(String[] args) {
        int n = 20; // Number of threads
        for (int i = 0; i < n; i++) {
            Thread object = new Thread(new Application());
            object.start();
        }
    }
}

class Application implements Runnable
{
    Application()
    {
        System.out.println("Singleton.Application Constructor");
    }
    @Override
    public void run() {
        DataBase obj2 = DataBase.GetInstance();
        System.out.println( obj2.hashCode() );
    }
}

class DataBase{
    private static DataBase Instance = new DataBase();
    private DataBase() {
        System.out.println("Database Constructor");
    }
    public static DataBase GetInstance() {
        if ( Instance == null )
            synchronized (DataBase.class) {
//             If both thread enter this section they create multiple objects , which breaks the Singleton.Singleton pattern
                if( Instance == null )
                    Instance = new DataBase();
                Instance.notify();
            }
        return Instance;
    }

}
