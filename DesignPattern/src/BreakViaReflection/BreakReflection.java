package BreakViaReflection;
import com.sun.org.apache.bcel.internal.generic.ClassGenException;

import java.lang.reflect.*;

public class BreakReflection {

    public static void main(String args[]) throws Exception
    {
        Class<?> c = Singleton.class;
        // here we are getting the declared constructor i.e. the private constructor
        Constructor cc = c.getDeclaredConstructor();

        // here we are setting it's accessibility as public
        cc.setAccessible(true);

        // here since we have made the constructor as public we can now simple create as many objects as we want
        Singleton obj1 = (Singleton) cc.newInstance();
        Singleton obj2 = (Singleton) cc.newInstance();
        Singleton obj3 = (Singleton) cc.newInstance();

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj3.hashCode());

        CounterReflection();
    }

    public static void CounterReflection() throws Exception
    {
        Class<?> c = Database.class;
        // here we are getting the declared constructor i.e. the private constructor
        Constructor cc = c.getDeclaredConstructor();

        // here we are setting it's accessibility as public
        cc.setAccessible(true);

        // here since we have made the constructor as public we can now simple create as many objects as we want
        Database obj = Database.getInstance();
        Database obj1 = (Database) cc.newInstance();
        Database obj2 = (Database) cc.newInstance();
        Database obj3 = (Database) cc.newInstance();

        System.out.println(obj.hashCode());
        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj3.hashCode());
    }
}

// Since it's a Singleton Class I should not be able to create a multiple duplicate object for this class
class Singleton{

    private static volatile Singleton Instance;

    private Singleton() {
        System.out.println("Singleton Instance being created");
    }

    public static Singleton getInstance() {
        if (Instance == null) {
            synchronized (Singleton.class) {
                if (Instance == null) {
                    Instance = new Singleton();
                }
            }
        }
        return Instance;
    }
}

class Database{
    private static volatile Database Instance;

    private Database() throws Exception
    {
        System.out.println("Database  Constructor is being called ");
        if( Instance != null )
            throw new Exception("Bhaiya Second object mat banao");
        Instance=this;
    }

    public static Database getInstance() throws Exception
    {
        if( Instance == null )
            synchronized( Database.class )
            {
                if( Instance == null )
                    Instance = new Database();
            }
        return Instance;
    }
}