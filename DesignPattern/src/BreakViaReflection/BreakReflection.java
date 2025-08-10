package BreakViaReflection;

import java.io.*;
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

        //CounterReflection();
        //BreakViaClone();
        BreakViaSerialization();
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

    public static void BreakViaClone() throws Exception ,CloneNotSupportedException
    {
        Database obj1 = Database.getInstance();
        Database obj2 = (Database)obj1.clone();

        System.out.println( obj1.hashCode() );
        System.out.println( obj2.hashCode() );

    }

    public static void BreakViaSerialization() throws Exception
    {
        Database obj = Database.getInstance();
        System.out.println( obj.hashCode() );

        FileOutputStream fos = new FileOutputStream("DbObject.txt");
        ObjectOutputStream oos = new ObjectOutputStream( fos );

        oos.writeObject( obj );
        oos.close(); fos.close();

        FileInputStream fis = new FileInputStream( "DbObject.txt");
        ObjectInputStream ois = new ObjectInputStream( fis );

        System.out.println( ois.readObject().hashCode() );
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

class Database implements Serializable , Cloneable {
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

    // solution to imporve the breaking of Singleton DP in Cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
          return Instance;  // or throw Exception to avoid copied ocjects of Singleton class
    }

    // solution to imporve the breaking of Singleton DP in Serialisation and deserialisation
    protected Object readResolve()
    {
        return Instance;
    }
}