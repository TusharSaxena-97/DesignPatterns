package Equals;

import java.util.Objects;

class student extends Object{
    int rno;
    String name;
    int marks;

    student( int rno , String name, int marks)
    {
        this.rno = rno;
        this.marks = marks;
        this.name = name;
    }

    @Override
    public boolean equals( Object o )
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        student s = (student) o;

        // Compare if hashcodes are equal or not
        if( this.hashCode() == o.hashCode() )
            return true;

//        return false;

        // even if hashcodes are equal comapre the contents as well.
        return Objects.equals( this.name , s.name) &&
                Objects.equals(this.rno, s.rno) &&
                Objects.equals(this.marks, s.marks);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( rno , name , marks);
    }
}

public class equals {

    public static void main( String args[]) {

        student s1 = new student( 1 , "abc", 90);
        student s2 = new student( 1 , "abc", 90);

        System.out.println( s1 == s2 );
        System.out.println( s1.equals( s2 ) );

        System.out.println( s1.hashCode() );
        System.out.println( s2.hashCode() );

    }
}
