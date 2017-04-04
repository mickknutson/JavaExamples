package com.baselogic.tutorials.reference.thread;

import com.baselogic.tutorials.domain.Counter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@RunWith(JUnit4.class)
public class CounterThreadTests {

    private static final Logger logger = LoggerFactory.getLogger(CounterThreadTests.class);

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//

    @Test
    public void counterTest() throws Exception {

        Counter counterA = new Counter("counterA");
        Counter counterB = new Counter("counterB");
        Thread  threadA = new CounterThread(counterA, "counterThreadA");
        Thread  threadB = new CounterThread(counterB, "counterThreadB");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        logger.info("counterA: {} ", counterA);
        logger.info("counterB: {} ", counterB);

    }

    @Test
    public void singleCounterTest() throws Exception {

        Counter counter = new Counter("counter");
        Thread  threadA = new CounterThread(counter, "counterThreadA");
        Thread  threadB = new CounterThread(counter, "counterThreadB");

        threadA.start();
        threadB.start();

        threadA.join();
        // all operations have stopped

        threadB.join();

        logger.info("counter: {} ", counter);

    }

    /**
     * Shared Object
     */
    Counter lockedObject = new Counter("lockedObject");

    /**
     * Example Thread.sleep()
     * @throws Exception
     */
    @Test
    public void threadSleepTest() throws Exception {

        // Perform operations many threads at a time...

        synchronized (lockedObject) {

            // some operation one thread at a time...
            lockedObject.add(1000);
        }

        // Perform operations many threads at a time...



        synchronized (lockedObject) {

            Thread.sleep(500); // It does not release the lock on lockedObject.
            // So either after 1000 milliseconds, current thread will wake up, or after we call
            //t. interrupt() method.

        }
    }

    /**
     * Example for Thread.wait()
     * @throws Exception
     */
    @Test
    public void threadWaitTest() throws Exception {

        synchronized(lockedObject) {
            lockedObject.wait(500); // It releases the lock on lockedObject.

            // So until we call notify() or notifyAll() from other thread, It will
            // not wake up if there are no parameters on wait()
            // if we set a timeout, then it will automatically wake up.
            // such as wait(500) //in ms.

        }
    }

}
