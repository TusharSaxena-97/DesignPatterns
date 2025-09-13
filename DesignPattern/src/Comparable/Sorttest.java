package Comparable;


// there are two interface Comparable and Comparator

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class student implements Comparable<student>{
    int id;
    String name;
    int marks;

    student(int id, String name, int marks)
    {
        this.id = id;
        this.name=  name;
        this.marks = marks;
    }

    @Override
    public int compareTo(student o) {
       return o.id - this.id;
    }
}

class nameSortComparator implements Comparator<student>
{
    @Override
    public int compare(student o1, student o2) {
        return o1.name.length() - o2.name.length();
    }
}

class idSortComparator implements Comparator<student>{

    @Override
    public int compare( student s1 , student s2 )
    {
       return s2.id - s1.id;
    }
}

public class Sorttest {
    public static void main( String a[]){

        student s1 = new student(1 ,"a",90);
        student s2 = new student(3 ,"jnc",90);
        student s3 = new student(2 ,"bb jmn ",90);

        student[] arr = new student[3];
        arr[0] = s1;
        arr[1] = s2;
        arr[2] = s3;

//        Arrays.sort( arr , new nameSortComparator() );
        Arrays.sort( arr );
        System.out.println(" Sorted by name length");
        display(arr);

        Arrays.sort( arr , new idSortComparator() );
        System.out.println(" Sorted by id");
        display( arr );
    }

    public static void display(student[] arr)
    {
        for( student s : arr)
            System.out.println( s.id + " - " + s.name + "  - " + s.marks );
    }
}
