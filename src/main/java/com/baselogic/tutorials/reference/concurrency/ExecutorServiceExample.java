package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ExecutorServiceExample {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceExample.class);

    public static void main(String[] args) {
        Date start = new Date();
        logger.info("Start: {}", start);

        ExecutorService ex = Executors.newFixedThreadPool(20);

        ex.execute(new PrintNumbers(1, 2));
        ex.execute(new PrintNumbers(2, 2));

        ex.shutdown();

        logger.info("End: {} ms", (new Date().getTime()) - start.getTime());
        //ex.shutdownNow();
    }
}




class PrintNumbers implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PrintNumbers.class);

    private int start = 0;
    private int increment = 1;
    private Thread theThread = null;

    public PrintNumbers(int st, int inc) {
        start = st;
        increment = inc;
        theThread = new Thread(this);
    }

    public void print() {

        int i, j;

        for (i = start, j = 0; j < 20; j++, i += increment) {
            logger.info("'{}', {}", theThread.getName(), i);
            Thread.yield();
        }
    }

    public void run() {
        print();
    }
}
