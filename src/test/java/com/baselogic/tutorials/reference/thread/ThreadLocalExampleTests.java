package com.baselogic.tutorials.reference.thread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Still need to do the following examples:
 *
 * getName(): It is used for Obtaining a thread’s name
 * getPriority(): Obtain a thread’s priority
 * isAlive(): Determine if a thread is still running
 * join(): Wait for a thread to terminate
 *** run(): Entry point for the thread
 * sleep(): suspend a thread for a period of time
 *** start(): start a thread by calling its run() method
 */
@RunWith(JUnit4.class)
public class ThreadLocalExampleTests {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalExampleTests.class);

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @Before
    public void beforeEachTest() throws Exception {}

    @After
    public void afterEachTest() throws Exception {}

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void testIt() throws Exception {
        ThreadLocalExample.main(null);
    }

}
