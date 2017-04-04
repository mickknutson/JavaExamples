package com.baselogic.tutorials.reference.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@RunWith(JUnit4.class)
public class SimpleRunnableTests {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRunnableTests.class);


    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void threadPriority() throws Exception {

        // Create the instances of the runnable class
        YieldingRunnable st1 = new YieldingRunnable();
        YieldingRunnable st2 = new YieldingRunnable();

        // Create the instances of the runnable class, and give each a
        // unique name
        Thread t1 = new Thread(st1, "SimpleRunnable 1");
        Thread t2 = new Thread(st2, "SimpleRunnable 2");

        // Set each thread to a different priority (do this later in this
        // exercise)

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        // Start each of the threads you've just created
        t1.start();
        t2.start();

    }

    @Test
    public void threadSleepTest() throws Exception {

//        synchronized (lockedObject) {
//            Thread.sleep(1000); // It does not release the lock on lockedObject.
//            // So either after 1000 miliseconds, current thread will wake up, or after we call
//            //t. interrupt() method.
//
//        }
    }

    @Test
    public void threadWaitTest() throws Exception {

//        synchronized(lockedObject) {
//            lockedObject.wait(); // It releases the lock on lockedObject.
//            // So until we call notify() or notifyAll() from other thread,It will
//            // not wake up
//
//        }
    }

}
