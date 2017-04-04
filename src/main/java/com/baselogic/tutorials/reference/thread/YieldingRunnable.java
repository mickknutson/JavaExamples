package com.baselogic.tutorials.reference.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mickknutson on 4/28/15.
 */
public class YieldingRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(YieldingRunnable.class);

    private int iterations = 5;

    public YieldingRunnable() {}

    public YieldingRunnable(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public void run() {

        for (int a = 0; a < iterations; a++) {
            logger.info(Thread.currentThread().getName() + ": yielding: " + a);

            Thread.yield();
        }
    }
}
