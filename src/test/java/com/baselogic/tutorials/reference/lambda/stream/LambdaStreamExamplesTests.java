package com.baselogic.tutorials.reference.lambda.stream;

import com.baselogic.tutorials.reference.lambda.LambdaExamples;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Testing Lambda Expressions
 */
@RunWith(JUnit4.class)
public class LambdaStreamExamplesTests {

    private static final Logger logger = LoggerFactory.getLogger(LambdaStreamExamplesTests.class);

    List<String> testList1;
    List<String> companyList;
    List<Double> numericList;

    @Before
    public void beforeClass() {
        testList1 = Arrays.asList("", "foo", "", "bar", "", "baz");
        logger.info("- Here is a Test List: {}", testList1);

        companyList = Arrays.asList("Tesla", "", "Uber", "Pandora", "Pied Piper", "Hooli", "Google", "Yahoo", "Facebook", "", "Twitter", "LinkedIn");
        logger.info("- Here is a Company List: {}", companyList);

        numericList = Arrays.asList(1.2, 2.3, 3.4, 4.5, 5.6, 6.7, 7.8, 8.9, 9.0);
        logger.info("- Here is a Double List: {}", numericList);
    }


    @Test
    public void summaryStatistics() throws Exception{
        List<Integer> input = Arrays.asList(98, 4, 7, 3, 2, 46, 21, 53, 17, 32, 63, 52);

        IntSummaryStatistics result = LambdaStreamExamples.summaryStatistics(input);
        assertThat(result.getMax(), is(98));
        assertThat(result.getMin(), is(2));
        assertThat(result.getSum(), is(398L));
        assertThat(result.getCount(), is(12L));
        assertThat(result.getAverage(), is(33.166666666666664));

    }

    @Test
    public void printNumberOfEmptyStrings() throws Exception{
        long result = LambdaStreamExamples.printNumberOfEmptyStrings(testList1);
        assertThat(result, is(3L));
    }

    @Test
    public void createListOfCubes() throws Exception{
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = LambdaStreamExamples.createListOfCubes(numbers);
        List<Integer> expected = Arrays.asList(1, 8, 27, 64, 125);
        assertThat(result, is(expected));
    }

    @Test
    public void uppercaseAndJoinStrings() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = Arrays.asList("lambda", "expressions", "are", "cool", "!");

        String result = LambdaStreamExamples.uppercaseAndJoinStrings(input);
        String expected = "LAMBDA EXPRESSIONS ARE COOL !";
        assertThat(result, is(expected));
    }

    @Test
    public void createListGreaterThanLength() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = companyList;

        List<String> result = LambdaStreamExamples.createListGreaterThanLength(input, 6);
        List<String> expected = Arrays.asList("Pandora", "Pied Piper", "Facebook", "Twitter", "LinkedIn");
        assertThat(result, is(expected));
    }

    @Test
    public void removeAllEmptyStrings() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = companyList;

        List<String> result = LambdaStreamExamples.removeAllEmptyStrings(input);
        List<String> expected = Arrays.asList("Tesla", "Uber", "Pandora", "Pied Piper", "Hooli", "Google", "Yahoo", "Facebook", "Twitter", "LinkedIn");
        assertThat(result, is(expected));
    }

    @Test
    public void countEntriesThatBeginWith() throws Exception{
        //-------------------------------------------------------------------//
        // Convert String to UPPERCASE and join them using space
        List<String> input = companyList;

        Long result = LambdaStreamExamples.countEntriesThatBeginWith(input, "P");
        Long expected = 2L;
        assertThat(result, is(expected));
    }


    @Test
    public void reduceEntriesWithInitialValue() throws Exception{
        //-------------------------------------------------------------------//
        // Reduce the List<String> into a single String.
        List<String> input = companyList;

        String result = LambdaStreamExamples.reduceEntriesWithInitialValue(input, "|");
        String expected = "|TeslaUberPandoraPied PiperHooliGoogleYahooFacebookTwitterLinkedIn";
        assertThat(result, is(expected));
    }


    @Test
    public void reduceNumericEntries() throws Exception{
        //-------------------------------------------------------------------//
        // Reduce the List<String> into a single Value.
        List<Double> input =  Arrays.asList(1.2, 2.3, 3.4, 4.5, 5.6, 6.7, 7.8, 8.9, 9.0);

        Double result = LambdaStreamExamples.reduceNumericEntriesByAddition(input, 0.0);
        Double expected = 49.4D;
        assertThat(result, is(expected));
    }



    @Test
    public void joiningEntries() throws Exception{
        //-------------------------------------------------------------------//
        // Reduce the List<String> into a single Value.
        List<String> input = companyList;

        String result = LambdaStreamExamples.joiningEntries(input, "|", "[", "]");
        String expected = "[Tesla||Uber|Pandora|Pied Piper|Hooli|Google|Yahoo|Facebook||Twitter|LinkedIn]";
        assertThat(result, is(expected));
    }



    @Test
    public void partitioningByEntries() throws Exception{
        //-------------------------------------------------------------------//
        // Reduce the List<String> into a single Value.
        List<String> input = companyList;

        Map<Boolean, List<String>> result = LambdaStreamExamples.partitioningByEntries(input, "T");

        String expected = "<{false=[, Uber, Pandora, Pied Piper, Hooli, Google, Yahoo, Facebook, , LinkedIn], true=[Tesla, Twitter]}>";

        logger.info("Complete List ({} elements): {}", input.size(), input);
        logger.info("Group of {} elements: {}", result.get(Boolean.TRUE).size(), result.get(Boolean.TRUE));
        assertThat(result.get(Boolean.TRUE), is(Arrays.asList("Tesla", "Twitter")));
        assertThat(result.get(Boolean.TRUE).size(), is(2));

        logger.info("Group of {} elements: {}", result.get(Boolean.FALSE).size(), result.get(Boolean.FALSE));
        assertThat(result.get(Boolean.FALSE).size(), is(10));
    }




    @Test
    public void parallelStreamPartitioningByEntries() throws Exception{
        //-------------------------------------------------------------------//
        // Reduce the List<String> into a single Value.
        List<String> input = companyList;

        Map<Boolean, List<String>> result = LambdaStreamExamples.parallelStreamPartitioningByEntries(input, "T");

        String expected = "<{false=[, Uber, Pandora, Pied Piper, Hooli, Google, Yahoo, Facebook, , LinkedIn], true=[Tesla, Twitter]}>";

        logger.info("Complete List ({} elements): {}", input.size(), input);
        logger.info("Group of {} elements: {}", result.get(Boolean.TRUE).size(), result.get(Boolean.TRUE));
        assertThat(result.get(Boolean.TRUE), is(Arrays.asList("Tesla", "Twitter")));
        assertThat(result.get(Boolean.TRUE).size(), is(2));

        logger.info("Group of {} elements: {}", result.get(Boolean.FALSE).size(), result.get(Boolean.FALSE));
        assertThat(result.get(Boolean.FALSE).size(), is(10));
    }





    @Test
    public void twoLetterStringConvertedToUppercaseLambdas() {
        List<String> input = Arrays.asList("ab");

        List<String> result = LambdaStreamExamples.uppercaseFirstChar(input);

        assertThat(Arrays.asList("Ab"), is(result));
    }

    @Test
    public void twoLetterStringConvertedToUppercase() {
        String input = "ab";

        String result = LambdaStreamExamples.firstToUppercase(input);

        assertThat("Ab", is(result));
    }


    @Test
    public void multipleWordsToUppercase() {
        List<String> input = Arrays.asList("a", "b", "hello");

        List<String> result = LambdaStreamExamples.allToUpperCase(input);

        assertThat(Arrays.asList("A", "B", "HELLO"), is(result));
    }


    @Test
    public void test__streamGenerate(){
        LambdaStreamExamples.streamGenerate();
    }


} // The End...
