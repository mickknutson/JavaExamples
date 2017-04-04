package com.baselogic.tutorials.reference.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mickknutson on 4/28/15.
 */
public class SleeperThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(YieldingRunnable.class);

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            try {
                Thread.sleep(5_000_000);
            } catch (InterruptedException e) {
                logger.info("***** {} *****", e.getMessage(), e);
            }
            logger.info("{}", i);
        }
    }
}
