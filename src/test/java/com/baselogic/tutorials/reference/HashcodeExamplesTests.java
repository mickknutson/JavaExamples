package com.baselogic.tutorials.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by mickknutson on 4/23/15.
 */
@RunWith(JUnit4.class)
public class HashcodeExamplesTests {

    private static final Logger logger = LoggerFactory.getLogger(HashcodeExamplesTests.class);

    private HashcodeExamples hce = new HashcodeExamples();

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
    public void stringHashcodeExampleTest() throws Exception {
        hce.stringHashcodeExample();

    }

}
