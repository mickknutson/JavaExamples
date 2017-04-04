package com.baselogic.tutorials.reference.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mickknutson on 4/23/15.
 */
public class BigONotationExamples {

    private static final Logger logger = LoggerFactory.getLogger(BigONotationExamples.class);

    public int[] bigO1(final int[] toSort) {

        //tbd
        return toSort;
    }

    public int[] bigOLogN(final int[] toSort) {

        //tbd
        return toSort;
    }

    public int[] bigON(final int[] toSort) {

        //tbd
        return toSort;
    }

    public int[] bigONQuadratic2(final int[] toSort) {

        //tbd
        return toSort;
    }

    public int[] bigONExponentialN(final int[] toSort) {

        //tbd
        return toSort;
    }


    public int[] sort(final int[] toSort) {

        for (int i = 0; i < toSort.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < toSort.length - 1 - i; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    swapped = true;
                    int swap = toSort[j + 1];
                    toSort[j + 1] = toSort[j];
                    toSort[j] = swap;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return toSort;
    }


    public void findTheBlank(final int[] theNumbers) {
        int sumOfAllNumbers = 0;
        int sumOfNumbersPresent = 0;
        int blankSpace = 0;

        for (int i = 0; i < theNumbers.length; i++) {
            sumOfAllNumbers += i + 1;
            sumOfNumbersPresent += theNumbers[i];
            if (theNumbers[i] == 0)
                blankSpace = i;
        }
        logger.info("Missing number = {} at location {} of the array", (sumOfAllNumbers - sumOfNumbersPresent), blankSpace);
    }

//    new BubbleSort().findTheBlank(new int[]{7,6,0,1,3,2,4});

//Missing number = 5 at location 2 of the array
}
