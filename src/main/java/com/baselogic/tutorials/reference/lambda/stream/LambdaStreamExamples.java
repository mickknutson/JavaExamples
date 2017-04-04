package com.baselogic.tutorials.reference.lambda.stream;

import com.baselogic.tutorials.reference.lambda.predicate.PredicateExamples;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileFilter;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


/**
 * Lambda Expression Syntax:
 *
 * (int x, int y)  ->  {
 *      int max = x  > y  ?  x  : y;
 *      return max;
 *  }
 *
 *  http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class LambdaStreamExamples {

    private static final Logger logger = LoggerFactory.getLogger(LambdaStreamExamples.class);


    static FileFilter getFilter(final String ext) {
        return (pathname) -> pathname.toString().endsWith(ext);
    }

    /**
     * this::print
     * LambdaExamples::print
     */
    public static void print(String label, String message) {
        logger.info("{} --> {}", label, message);
    }


    /**
     * Set of Examples
     * @throws Exception
     *
     * TODO: summaryStatistics
     * @apiNote summaryStatistics
     */
    public static IntSummaryStatistics summaryStatistics(final List<Integer> input) throws Exception {

        IntSummaryStatistics intSummaryStatistics = input
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        logger.info("- Here is a integerList List: {}", input);
        logger.info("Highest Prime # {}", intSummaryStatistics.getMax());
        logger.info("Lowest Prime  # {}", intSummaryStatistics.getMin());
        logger.info("Sum of All: {}", intSummaryStatistics.getSum());
        logger.info("Average of all: {}", intSummaryStatistics.getAverage() + "\n");




        input.parallelStream().forEach(System.out::println);

        Statement st;

        return intSummaryStatistics;

    }


    /**
     * TODO: filter & count
     * @param input
     * @return
     */
    public static long printNumberOfEmptyStrings(final List<String> input){

        //-------------------------------------------------------------------//
        // Print only empty list count
        long emptyCount = input
                .stream() // open stream
                .filter(entry -> entry.isEmpty())
                .count();
        logger.info("Test1: # of Empty Strings: {}", emptyCount);
        return emptyCount;
    }

    /**
     * TODO: map
     * @param input
     * @return
     */
    public static List<Integer> createListOfCubes(final List<Integer> input){
        //-------------------------------------------------------------------//
        // Create List of Cubes
        List<Integer> cubes = input
                .stream()
                .map(i -> i * i * i)
                .distinct()
                .collect(Collectors.toList());
        logger.info("- Create cubes for 1,2,3,4,5: {}\n", cubes);
        return cubes;
    }

    /**
     * TODO: joining to String
     * @param input
     * @return
     */
    public static String uppercaseAndJoinStrings(final List<String> input){
        String joinList = input
                .stream()
                .map(i -> i.toUpperCase())
                .collect(joining(" "));
        logger.info("- Join All String with UPPERCASE: {}", joinList);
        return joinList;
    }

    /**
     * TODO: filter > length
     * @param input
     * @param length
     * @return
     */
    public static List<String> createListGreaterThanLength(final List<String> input, int length){
        //-------------------------------------------------------------------//
        // Create a List with String > [length] characters
        List<String> newList = input
                .stream()
                .filter(i -> i.length() > length)
                .collect(Collectors.toList());
        logger.info("New list which has total characters > {}: {}\n", length, newList);

        return newList;
    }

    /**
     * TODO: filter remove empty elements
     * @param input
     * @return
     */
    public static List<String> removeAllEmptyStrings(final List<String> input){
        // Remove all empty Strings from List
        List<String> removeEmptyStrings = input
                .stream()
                .filter(i -> !i.isEmpty())
                .collect(toList());

        return removeEmptyStrings;
    }

    /**
     * TODO: count
     * @param input
     * @param startsWith
     * @return
     */
    public static Long countEntriesThatBeginWith(final List<String> input, String startsWith){
        //-------------------------------------------------------------------//
        // Match the pattern which starts with letter 'T' and print count
        Long startCount = input
                .stream()
                .filter(i -> i.startsWith(startsWith))
                .count();

        return startCount;
    }


    /**
     * TODO: reduce to String
     * @param input
     * @param initialValue
     * @return
     */
    public static String reduceEntriesWithInitialValue(final List<String> input, String initialValue){
        //-------------------------------------------------------------------//
        // Reduce
        String result = input
                .stream()
                // intermediate step to filter List only 10 items
                .reduce(initialValue, (a, b) -> a + b);

        return result;
    }

    /**
     * TODO: reduce to Double
     * @param input
     * @param initialValue
     * @return
     */
    public static Double reduceNumericEntriesByAddition(final List<Double> input, double initialValue){
        //-------------------------------------------------------------------//
        // Reduce
        Double result = input
                .stream()
                .reduce(initialValue, (a, b) -> a + b);

        return result;
    }

    /**
     * TODO: Joining to String
     * @param input
     * @param separator
     * @param prefix
     * @param suffix
     * @return
     */
    public static String joiningEntries(final List<String> input,
                                        final String separator,
                                        final String prefix,
                                        final String suffix){
        //-------------------------------------------------------------------//
        // Joinging

        String result = input
                .stream()
                .collect(joining(separator, prefix, suffix));

        return result;
    }

    /**
     * TODO: partitioningBy
     * @param input
     * @param prefix
     * @return
     */
    public static Map<Boolean, List<String>> partitioningByEntries(final List<String> input,
                                                                   final String prefix){
        //-------------------------------------------------------------------//
        // Reduce

        Map<Boolean, List<String>> result = input
                .stream()
                .collect(
                        Collectors.partitioningBy(
                            PredicateExamples.startsWithT(prefix))
                );

        return result;
    }

    /**
     * TODO: partitioningBy
     * @param input
     * @param prefix
     * @return
     */
    public static Map<Boolean, List<String>> parallelStreamPartitioningByEntries(final List<String> input,
                                                                                 final String prefix){
        //-------------------------------------------------------------------//
        // parallelStream

        Map<Boolean, List<String>> result = input
                .parallelStream()
                .collect(Collectors.partitioningBy(PredicateExamples.startsWithT(prefix)));

        return result;
    }














    //FIXME: TODO: Add flatMap()



    /*
     * Long form:
    public static List<String> uppercaseFirstChar(final List<String> words) {
        return words.stream()
                .map(value -> {
                    char firstChar = value.charAt(0);
                    firstChar = LambdaExamples.toUpperCase(firstChar);
                    return firstChar + value.substring(1);
                })
                .collect(Collectors.toList());
    }*/
    /*
     * Short form:
     */
    public static List<String> uppercaseFirstChar(final List<String> words) {
        return words.stream()
                .map(LambdaStreamExamples::firstToUppercase)
                .collect(Collectors.toList());
    }

    public static char toUpperCase(final char value) {
        return Character.toUpperCase(value);
    }

    public static String toUppercase(final String value) {
        return value.toUpperCase();
    }

    public static String firstToUppercase(final String value) {
        char firstChar = value.charAt(0);
        firstChar = toUpperCase(firstChar);
        return firstChar + value.substring(1);
    }


    /**
     * convert all characters to upper case.
     * @param words
     * @return
     */
    public static List<String> allToUpperCase(final List<String> words) {
        return words.stream()
                .map(string -> string.toLowerCase())
                .map(LambdaStreamExamples::toUppercase)
                .collect(toList());
    }


    /**
     *
     */
    public static void streamGenerate(){
        Stream.generate(()-> "Elsa")
                .filter(n -> n.length() == 4)
                .peek(n -> System.out.println("p."+n))
                .limit(5)
                .sorted()
                .forEach(System.out::println);
    }



} // the end...
