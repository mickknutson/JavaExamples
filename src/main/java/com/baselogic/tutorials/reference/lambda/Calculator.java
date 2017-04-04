package com.baselogic.tutorials.reference.lambda;

import com.baselogic.tutorials.reference.lambda.function.TriBinaryOperator;
import com.baselogic.tutorials.reference.lambda.function.TriFunction;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/**
 * Lambda Calculator
 */
public class Calculator {

    private static Consumer<String> sysout = System.out::println;



    public static <T> T calculate(final T op1,
                                  final T op2,
                                  final BinaryOperator<T> operator) {

        sysout.accept("foo");

        Consumer<T> sysoutT = System.out::println;

        sysoutT.accept(op1);

        sysout.accept("Operand 2:" + op2);

        // Perform calculation:
        return operator.apply(op1, op2);
    }












    public static Float addFloat2(Float a, Float b){
        return a + b;
    }
    public static BinaryOperator<Float> addFloat3 = Calculator::addFloat2;
    public static BinaryOperator<Float> addFloat = (Float a, Float b) -> a + b;




    public static BinaryOperator<Integer> add = (a, b) -> a + b;

    public static BinaryOperator<Integer> subract = (a, b) -> a - b;

    public static BinaryOperator<Integer> multiply = (a, b) -> a * b;

    public static BinaryOperator<Integer> divide = (a, b) -> a / b;

    public static BinaryOperator<Integer> modulus = (a, b) -> a % b;



    public static TriFunction<Integer, Integer, Integer, Integer> triModulus = (a, b, c) -> a * b % c;

//    public static TriBinaryOperator<Integer> triBinaryModulus = (x, y, z) -> x + y;


} // The End...
