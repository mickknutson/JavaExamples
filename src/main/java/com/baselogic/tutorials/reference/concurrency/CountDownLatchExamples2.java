package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mickknutson on 1/5/17.
 */
public class CountDownLatchExamples2 implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchExamples2.class);

    static int NUM_THREADS = 5;

    CountDownLatch cdl1 = new CountDownLatch(NUM_THREADS);

    int myValue = 0;

    AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        CountDownLatchExamples2 main = new CountDownLatchExamples2();

        for (int i = 0; i < NUM_THREADS; i++)
            new Thread(main, "Thread-"+i).start();
    }

    @Override
    public void run() {

        String currentThread = Thread.currentThread().getName();

        logger.info("starting: {}", currentThread);

        myValue = count.incrementAndGet();

        cdl1.countDown();

        try {
            logger.info("{} waiting... latch count: {}", currentThread, cdl1.getCount());

            cdl1.await();

            logger.info("{} after... latch count: {}", currentThread, cdl1.getCount());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

//        if (myValue != i)
//            logger.info("{} not equal to {}", currentThread, i);
//        else
//            logger.info("{} EQUAL to {}", currentThread, i);

        logger.info("end: {}", Thread.currentThread().getName());

    }
} // The end...
