package com.baselogic.tutorials.reference.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by mickknutson on 4/28/15.
 */
public class WaitingThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(YieldingRunnable.class);

    Queue q = new PriorityQueue();

    public void run() {

        for (int i = 1; i < 5; i++) {

            try {
                this.wait(500);

                Object obj = q.peek();

                if (obj != null){
                    //do something
                }

            } catch (InterruptedException e) {
                logger.info("***** {} *****", e);
            }
            logger.info("{}", i);
        }

    } // the thread dies here...


    public void run2() {

        while(true) {

            try {
                this.wait(100); // in milliseconds

                logger.info("keep polling for some result");
                Object obj = q.peek();

                if (obj != null){
                    logger.info("do something with obj...");
                }

            } catch (InterruptedException e) {
                logger.info("***** {} *****", e);
            }
            q.peek();


        }
    }
}
