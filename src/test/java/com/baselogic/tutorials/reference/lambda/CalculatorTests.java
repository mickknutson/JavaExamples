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

import static com.baselogic.tutorials.reference.lambda.Calculator.*;

/**
 * Lambda Calculator Test
 */
@RunWith(JUnit4.class)
public class CalculatorTests {

    @Test
    public void test__addition(){

//        Integer result = Calculator.calculate(5, 5, Calculator.add);
        Integer result = calculate(5, 5, add);

//        assertEquals(result, 10);
        assertThat(result, is(10));

//        assertThat(result, is(not(greaterThan(20))));
    }


    @Test
    public void test__subtraction(){

        Integer result = Calculator.calculate(64, 46,
                Calculator.subract
        );

        assertThat(result, is(18));
    }


    @Test
    public void test__multiplication(){

        Integer result = Calculator.calculate(5, 5,
                Calculator.multiply
        );

        assertThat(result, is(25));
    }


    @Test
    public void test__division(){

        Integer result = Calculator.calculate(5, 5,
                Calculator.divide
        );

        assertThat(result, is(1));
    }


    @Test
    public void test__modulus(){

        Integer result = Calculator.calculate(10, 6,
                Calculator.modulus
        );

        assertThat(result, is(4));
    }

} // The End...
