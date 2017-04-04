package com.baselogic.tutorials.reference.lambda;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Testing Lambda Expressions
 */
@RunWith(JUnit4.class)
public class LambdaExamplesTests {

    private static final Logger logger = LoggerFactory.getLogger(LambdaExamples.class);

    public LambdaExamples.MathOperation division;

    @Test
    public void test__MathOperation(){

        LambdaExamples tester = new LambdaExamples();

        //with type declaration
        LambdaExamples.MathOperation addition = (int a, int b) -> a + b;

        //with out type declaration
        LambdaExamples.MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curly braces
        LambdaExamples.MathOperation multiplication = (a, b) -> {
            return a * b; };

        //without return statement and without curly braces
        division = (a, b) -> a / b;

        LambdaExamples.MathOperation division2 = (a, b) -> a / b;

        LambdaExamples.MathOperation modulus = (a, b) -> a % b;


        logger.info("***************************** 10 % 6 = {}",
                tester.operate(10, 6, modulus)
        );
        assertThat(tester.operate(10, 6, modulus), is(4));

        logger.info("10 + 5 = {}",
                tester.operate(10, 5, addition)
        );
        assertThat(tester.operate(10, 5, addition), is(15));

        logger.info("10 - 5 = {}", tester.operate(10, 5, subtraction));
        assertThat(tester.operate(10, 5, subtraction), is(5));

        logger.info("10 x 5 = {}", tester.operate(10, 5, multiplication));
        assertThat(tester.operate(10, 5, multiplication), is(50));

        logger.info("10 / 5 = {}", tester.operate(10, 5, division));
        assertThat(tester.operate(10, 5, division), is(2));

    }

    @Test
    public void test__GreetingService(){

        // lambda argument with parenthesis
        LambdaExamples.GreetingService greetService1 = message ->
                logger.info("Hello " + message);

        // lambda argument without parenthesis
        LambdaExamples.GreetingService greetService2 = (message) ->
                {logger.info("Hello " + message);};

        greetService1.sayMessage("Mick");
        greetService2.sayMessage("Bob");
    }











    @Test
    public void example2() throws Exception{
        LambdaExamples.example2();

    }


    @Test
    public void example4() throws Exception{
        LambdaExamples.example2();

    }



    public class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return this.name; }

        public Integer getAge() { return this.age; }

        public String toString() { return this.name + "|" + this.age; }
    }

    @Test
    public void example5(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", 28));
        persons.add(new Person("Jane", 32));
        persons.add(new Person("Jack", 19));
        persons.add(new Person("Jill", 18));

        Collections.sort(persons, (Person p1, Person p2) -> p1.getName().compareTo(p2.getName()));
        logger.info("Persons: {} ", persons); //[Jack|19, Jane|32, Jill|18, John|28]

        Collections.sort(persons, (Person p1, Person p2) -> p1.getAge().compareTo(p2.getAge()));
        logger.info("Persons: {} ", persons); //[Jill|18, Jack|19, John|28, Jane|32]

        List<Person> olderPersons = persons.stream().filter(p -> p.age > 20).collect(Collectors.toList());
        logger.info("olderPersons: {} ", olderPersons); //[John|28, Jane|32]
    }

    @Test
    public void comparableExamples(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", 28));
        persons.add(new Person("Jane", 32));
        persons.add(new Person("Jack", 19));
        persons.add(new Person("Jill", 18));

        // (Person p1, Person p2) -> p1.getName().compareTo(p2.getName())

        Collections.sort(persons, (Person p1, Person p2) -> p1.getName().compareTo(p2.getName()));
        logger.info("Persons: {} ", persons); //[Jack|19, Jane|32, Jill|18, John|28]

        Collections.sort(persons, (Person p1, Person p2) -> p1.getAge().compareTo(p2.getAge()));
        logger.info("Persons: {} ", persons); //[Jill|18, Jack|19, John|28, Jane|32]

        List<Person> olderPersons = persons.stream().filter(p -> p.age > 20).collect(Collectors.toList());
        logger.info("olderPersons: {} ", olderPersons); //[John|28, Jane|32]
    }


} // The End...
