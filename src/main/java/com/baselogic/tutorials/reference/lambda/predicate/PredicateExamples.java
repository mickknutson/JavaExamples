package com.baselogic.tutorials.reference.lambda.predicate;

import java.util.function.Predicate;

/**
 * Created by mickknutson on 8/19/15.
 */
public class PredicateExamples {


    //Predicate

//    @FunctionalInterface
//    interface Predicate<G> {
//        boolean test(G grouping);
//
//        //rest of the code goes here
////        Predicate addition = (int a, int b) -> a + b;
//
//    }

    public static Predicate<String> startsWithT(String prefix) {
        return c -> c.startsWith(prefix);
    }


} // The End...
