package com.baselogic.tutorials.reference.enums.calculator;

/**
 * Created by mickknutson on 1/9/17.
 */
@FunctionalInterface
public interface OperationStrategy<T> {

     T compute(T x, T y);
}