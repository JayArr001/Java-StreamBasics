import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Push
{
    //this is a simple program that uses streams in different variations to demonstrate familiarity with
    //stream pipelines, such as initialization, utilization, terminal operations and functionality
    //in between. It also lightly incorporates various Collections.

    //There are some instances of duplicate code in this program.
    //Although duplicate code is discouraged, the purpose of this program is to illustrate
    //usage and familiarity of streams, plus the various ways to accomplish effectively the same outcome
    public static void main(String[] args)
    {
        //collection that holds the 75 elements for BINGO + iterative counter
        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;
        for (char c: "BINGO".toCharArray())
        {
            for(int i = start; i < (start + 15); i++)
            {
                bingoPool.add("" + c + i);
            }
            start += 15;
        }

        //we're getting 15 random elements from the BINGO collection just generated
        //can also pick and choose from the pool by generating random indices to grab
        Collections.shuffle(bingoPool);
        for(int i = 0; i < 15; i++)
        {
            System.out.println(bingoPool.get(i));
        }

        //separation lines to easily read the console output
        System.out.println("-------------------------------");

        //starting from the shuffled collection containing all elements, get 15 of them
        //then filter for bingo elements G or O, then sort
        //as an aside, the collection wasn't shuffled from the last For loop, so
        //the G and O elements should be the same. These are printed to the console
        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
        tempStream.forEach(s -> System.out.print(s + " "));

        System.out.println("\n-------------------------------");

        //Generates the first 20 prime numbers for all whole numbers
        //This is an infinite stream with a limit() operation
        IntStream.iterate(1, n -> n+1)
                .filter(Push::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println("\n-------------------------------");

        //Generates exactly the first 100 prime numbers using limit()
        //This is an infinite stream with a limit() operation
        IntStream.iterate(1, n -> n+1)
                .limit(100)
                .filter(Push::isPrime)
                .forEach(s -> System.out.print(s + " "));

        //Generates prime numbers for all whole numbers under 100
        //This is a finite stream because the limiting condition is in the second argument
        System.out.println("\n-------------------------------");
        IntStream.iterate(1, n -> n <= 100, n -> n+1)
                .filter(Push::isPrime)
                .forEach(s -> System.out.print(s + " "));

        //Generates prime numbers for all whole numbers under 100
        //This is a finite stream because it uses stream.range(x, y)
        //stream.range(x, y) goes from x -> y EXCLUSIVELY
        //stream.rangeClosed(x, y) goes from x -> and includes y
        System.out.println("\n-------------------------------");
        IntStream.range(1, 100)
                .filter(Push::isPrime)
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n-------------------------------");
        System.out.println("\n-------------------------------");

        //creating elements with streams in various different ways, B-I-N-G-O

        //creating B-elements using stream from an array
        String[] bStrings = {"B1", "B2", "B3", "B4", "B5", "B6",
                "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15"};
        var bStream = Arrays.stream(bStrings);

        //creating I-elements using a stream where the values are explicitly given
        var iStream = Stream.of("I16", "I17", "I18", "I19", "I20", "I21",
                "I22", "I23", "I24", "I25", "I26", "I27", "I28", "I29", "I30");

        //creating N-elements using Stream.iterate() plus conditions
        var nStream = Stream.iterate(31, n -> n+1)
                .limit(15)
                .map(e -> "N" + e);

        //creating G-elements using Stream.iterate() plus more complex conditions
        var gStream = Stream.iterate(46, n -> n <= 60, n -> n+1)
                .limit(15)
                .map(e -> "G" + e);

        //creating O-elements using IntStream.range(), then mapping to a stream of Strings
        var oStream = IntStream.range(61, 76)
                .mapToObj(e -> "N" + e);

        //combining all the respective streams
        var biStream = Stream.concat(bStream, iStream);
        var binStream = Stream.concat(biStream, nStream);
        var bingStream = Stream.concat(binStream, gStream);
        var bingoStream = Stream.concat(bingStream, oStream);

        bingoStream.forEach(System.out::println);
    }

    //Boolean that returns true/false if an int is prime
    //this code was written by me, but is a common solution, and others can be found online
    public static boolean isPrime(int inputN)
    {
        if(inputN <= 2)
        {
            return (inputN == 2);
        }
        for(int divisor = 2; divisor < inputN; divisor++)
        {
            if(inputN % divisor == 0)
            {
                return false;
            }
        }
        return true;
    }
}
