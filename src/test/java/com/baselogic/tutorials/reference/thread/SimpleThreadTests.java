package com.baselogic.tutorials.reference.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class SimpleThreadTests {

    private static final Logger logger = LoggerFactory.getLogger(SimpleThreadTests.class);


    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//

    @Test
    public void threadPriority() throws Exception {

        // Create the instances of the runnable class
        YieldingRunnable yr1 = new YieldingRunnable();
        YieldingRunnable yr2 = new YieldingRunnable();

        // Create the instances of the runnable class, and give each a
        // unique name
        Thread t1 = new Thread(yr1, "Yielding Runnable 1");
        Thread t2 = new Thread(yr2, "Yielding Runnable 2");



        // Set each thread to a different priority (do this later in this
        // exercise)
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        // Start each of the threads you've just created
        t1.start();
        t2.start();

    }

    @Test
    public void threadInterruption() throws Exception {

        // Create the instances of the runnable class
        SleeperThread st1 = new SleeperThread();
        SleeperThread st2 = new SleeperThread();

        // Create the instances of the runnable class, and give each a
        // unique name
        Thread t1 = new Thread(st1, "SleeperThread 1");
        Thread t2 = new Thread(st2, "SleeperThread 2");

        // Start each of the threads you've just created
        t1.start();
        t2.start();

        t1.interrupt();
//        t2.interrupt();

//        assertThat(t1.isInterrupted(), is(true));
        assertThat(t2.isInterrupted(), is(false));

    }


}
