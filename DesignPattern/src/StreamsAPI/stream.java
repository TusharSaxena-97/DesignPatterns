package StreamsAPI;

import javax.lang.model.type.ArrayType;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class maxComp implements Comparator<Integer>
        {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        }

public class stream {

    public static void main( String args[] )
    {
        List<Integer> ll = Arrays.asList(1, 2,3,4,5 ,6, 7,8, 9 );

        // methods to revise
        // map() , filter() ,reduce() , distinct() , partioningBy(), limit() , findAny() , findFirst()

        int lar5 = ll.stream().distinct().reduce( (a , b ) -> a + b).get();

        // Find sum
        int sum = ll.stream().mapToInt( x -> x ).sum();
        System.out.println( lar5 );

        // Find max
        int max = ll.stream().reduce( ( a , b) -> a > b ? a : b ).get();
        System.out.println( max );

        // Find max using limit
        int maxl = ll.stream().sorted( ( a , b) -> b - a ).limit(1).findFirst().get();
        System.out.println( maxl );

        int maxll = ll.stream().max( new maxComp() ).get();

        // remove the odd elemnts
        List<Integer> oddList = ll.stream().filter( n -> n % 2 == 0 ).collect(Collectors.toList());
        System.out.println( oddList );

//        Given a list of integers, find the sum of all even numbers using streams.
//        Given a list of strings, find the longest string.
//            Given a list of employees, group them by department and count employees in each department.
//            Sort a list of employees by salary in descending order using streams.
//            Convert a List<String> to a Map<String, Integer> where key = string, value = length of string.
//        Given a list of words, find the frequency of each word (word count problem).
//            Given a list of transactions, filter only those of type "GROCERY" and sum the amounts.
//        How do you remove duplicates from a list using streams?
//        How do you flatten a List<List<Integer>> into a List<Integer>?
//        Given a list of people, partition them into adults vs minors using Collectors.partitioningBy.
//            Implement distinctByKey() using streams to filter unique elements by a property.
        
        List<String> ss = Arrays.asList( "aaaa" , "bbb" , "cc" , "d" , "d" , "bbb");

        String ls = ss.stream().min( (a , b ) -> b.length() - a.length() ).get();
        System.out.println( ls );

        List<String> lls = Arrays.asList("Anand" , "bibhuti", "rakesh ", "hoshiyar");
        Set<String> s = new HashSet<>();
        s.addAll( lls );
        Map<Boolean , List<String> > sls = s.stream().collect( Collectors.groupingBy( (a) -> a.length() > 7 ? true : false));
        System.out.println( sls );

        Map<String, Integer> mp = lls.stream().collect( Collectors.toMap( a -> a , a -> a.length()));
        System.out.println( mp );

        // find count of each string in the list of string
        Map<String , Long> mpp = ss.stream().collect( Collectors.groupingBy( a ->  a , Collectors.counting() ));
        Map<String, Long> mpps = ss.stream().collect( Collectors.toMap ( a -> a, a -> 1L , (a ,b ) -> a + b ));
        System.out.println( mpps );

        // partition by requires a predicate i.e. it returns either true or false.
        Map<Boolean , List<String>> mmpp = ss.stream().collect( Collectors.partitioningBy( a -> a.length() > 7  ));
        System.out.println( mmpp );

        List<Character> cl = lls.parallelStream().map( a -> a.charAt(0) ).filter( a -> a=='a').collect(Collectors.toList());
        System.out.println( cl );

        // Code example when not to use parallelStream()
        List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<Integer> results = new ArrayList<>();  // shared mutable state ❌

        numbers.parallelStream()
                .map(n -> {
                    numbers.add(n * n);   // modifying shared list
                    return n;
                })
                .forEach(System.out::println);

        System.out.println("Squares collected: " + results);


        // One more example

        int[] counter = {0}; // shared mutable state ❌

        IntStream.rangeClosed(1, 1000).parallel().forEach(i -> {
            counter[0]++;  // multiple threads modifying same variable
        });

        System.out.println("Counter = " + counter[0]);

        List<Set<Integer>> llls = new ArrayList<>();
        Set<Integer> s1 = new HashSet<>(); s1.addAll(Arrays.asList(1, 2, 3));
        Set<Integer> s2 = new HashSet<>(); s2.addAll(Arrays.asList(1, 2, 3));
        llls.add( s1 );
        llls.add( s2 );

        System.out.println( llls.stream().map( a -> a.stream() ).collect(Collectors.toSet()));
        llls.parallelStream().flatMap( a -> a.stream() ).forEach( a -> System.out.println( a ) );

        String str = "asscdcrcdvcfdvcrvscervfvs";
//      char[] cc = str.toCharArray();
//        str.chars().mapToObj(c -> (char) c).filter( o -> cs.conatains(o) ).findFirst();

        str.chars()
                .boxed()
                .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum, LinkedHashMap::new))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
