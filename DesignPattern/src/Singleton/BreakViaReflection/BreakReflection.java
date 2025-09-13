package Singleton.BreakViaReflection;

import java.io.*;
import java.lang.reflect.*;

public class BreakReflection {

    public static void main(String args[]) throws Exception
    {
        Class<?> c = Singleton.class;
        Constructor<?> cc = c.getDeclaredConstructor();

        if( !cc.isAccessible() )
            cc.setAccessible(true);

        Singleton obj = (Singleton)cc.newInstance();

       // Class<?> c = Singleton.class;
        // here we are getting the declared constructor i.e. the private constructor
       // Constructor cc = c.getDeclaredConstructor();
        Method m = c.getDeclaredMethod("method_name");

        if( !m.isAccessible() )
            m.setAccessible(true);

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

// Since it's a Singleton.Singleton Class I should not be able to create a multiple duplicate object for this class
class Singleton{

    private static volatile Singleton Instance;

    private Singleton() {
        System.out.println("Singleton.Singleton Instance being created");
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

    // solution to imporve the breaking of Singleton.Singleton DP in Cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
//          return Instance;  // or throw Exception to avoid copied ocjects of Singleton.Singleton class
    }

    // solution to imporve the breaking of Singleton.Singleton DP in Serialisation and deserialisation
    protected Object readResolve() throws CloneNotSupportedException {
        System.out.println("Read resolve mei aaagye ho hehe");
        //return new String("Gol mal hai bhia sab gol maal hai");
        return this;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // can alter some logic for writeObject Over here.
        System.out.println("hehe write Object mei aagye ho");
        oos.defaultWriteObject();
    }

    private void readObject( ObjectInputStream ois ) throws IOException , ClassNotFoundException
    {
        // can alter some logic for readObject Over here.
        System.out.println("hehe read Object mei aagye ho");
        ois.defaultReadObject();
    }
}