package com.baselogic.tutorials.reference.collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mickknutson on 4/23/15.
 */
@RunWith(JUnit4.class)
public class BigONotationExamplesTests {
    private static final Logger logger = LoggerFactory.getLogger(BigONotationExamplesTests.class);

    private int[] data = new int[]{7, 6, 0, 1, 3, 2, 4, 9, 8};

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @Before
    public void beforeEachTest() throws Exception {
    }

    @After
    public void afterEachTest() throws Exception {
    }

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void sortTest() throws Exception {

        long startTime = System.currentTimeMillis();

        int[] result = new BigONotationExamples().sort(data);

        long endTime = System.currentTimeMillis();
        logger.info("BigONotationExamples.sort(data) execution time was {} ms.", (endTime - startTime));
        logger.info("BigONotationExamples.sort(data) result {}", result);
    }

    @Test
    public void findTheBlankTest() throws Exception {

        long startTime = System.currentTimeMillis();

        new BigONotationExamples().findTheBlank(data);

        long endTime = System.currentTimeMillis();
        logger.info("BigONotationExamples.findTheBlank(data) execution time was {} ms.", (endTime - startTime));
    }

}
