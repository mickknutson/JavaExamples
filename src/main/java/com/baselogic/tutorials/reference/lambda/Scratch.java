package com.baselogic.tutorials.reference.lambda;

import javax.sql.DataSource;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by mickknutson on 1/19/17.
 */
public class Scratch {

    public static void main(String ... args){

        Statement ds;

        List<Integer> values = Arrays.asList(2, 4, 6, 9);
        Predicate<Integer> check = (Integer i) -> {
            System.out.println("checking");
            return 4 == 4;
        };
        System.out.println("checked: " + check.test(4));
    }
}
