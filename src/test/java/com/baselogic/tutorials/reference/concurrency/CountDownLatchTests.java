package com.baselogic.tutorials.reference.concurrency;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

/**
 * https://dzone.com/articles/use-cases-of-countdownlatch
 */
public class CountDownLatchTests {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTests.class);

    /**
     * Use-case 1 :  Achieving Maximum Parallelism
     * @throws Exception
     */
    @Test
    public void test__shouldCreateOnlySingleInstanceOfAClassWhenTestedWithParallelThreads() throws Exception{

        final ObjectFactory factory = new ObjectFactory();
        final CountDownLatch startSignal = new CountDownLatch(1);

        class MyThread extends Thread {
            ObjectFactory.MyObject instance;
            @Override
            public void run() {
                try {
                    startSignal.await();
                    instance = factory.getInstance();
                } catch (InterruptedException e) {
                    logger.error("InterruptedException: {}", e);
                }
            }
        }

        int threadCount = 1_000;
        MyThread[] threads = new MyThread[threadCount];

        for (int i = 0;i< threadCount;i++) {
            threads[i] =  new MyThread();
            threads[i].start();
        }
        startSignal.countDown();

        for (MyThread myThread : threads) {
            myThread.join();
        }

        ObjectFactory.MyObject instance = factory.getInstance();

        for (MyThread myThread : threads) {
            assertEquals(instance, myThread.instance);
        }
    }

    /**
     * Use-case 2  : Wait for several threads to complete
     * @throws Exception
     */
    @Test
    public void shouldCreateOnlySingleInstanceOfAClassWhenTestedWithParallelThreads()
            throws Exception {

        int threadCount = 1_000;
        final ObjectFactory factory = new ObjectFactory();
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch stopSignal = new CountDownLatch(threadCount);

        class MyThread extends Thread {
            ObjectFactory.MyObject instance;

            @Override
            public void run() {
                try {
                    startSignal.await();
                    instance = factory.getInstance();
                } catch (InterruptedException e) {
                    // ignore
                    logger.error("InterruptedException: {}", e);
                } finally {
                    stopSignal.countDown();
                }
            }
        }

        MyThread[] threads = new MyThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread();
            threads[i].start();
        }

        startSignal.countDown();
        stopSignal.await();

        ObjectFactory.MyObject instance = factory.getInstance();
        for (MyThread myThread : threads) {
            assertEquals(instance, myThread.instance);
        }
    }

} // The End...


class ObjectFactory {

    private volatile MyObject object;

    public MyObject getInstance() {
        if (object == null) {
            synchronized (this) {
                if (object == null) {
                    object = new MyObject();
                }
            }
        }
        return object;
    }

    class MyObject {

    }
} // The End...