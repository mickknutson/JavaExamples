package com.baselogic.tutorials.comparable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mickknutson on 1/10/17.
 */
public class CollectionExamples {

    public static void main(final String[] args){

        List<Number> numbers = new ArrayList<>(100);

        for(int i = 0; i < 3; i++){
            numbers.add(i);
        }
//        numbers.add(null);
//        numbers.add(null);
//        numbers.add(null);
//        numbers.add(null);

        System.out.println("List size: "+numbers.size());

        System.out.println("trimming");
        ArrayList al = ((ArrayList) numbers);
        al.ensureCapacity(100);
        al.remove(2);
        al.trimToSize();

        System.out.println("List size: "+al.size());
    }
}
