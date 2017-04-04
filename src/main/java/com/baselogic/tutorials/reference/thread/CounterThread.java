package com.baselogic.tutorials.reference.thread;

import com.baselogic.tutorials.domain.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mickknutson on 2/17/15.
 */
public class CounterThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(CounterThread.class);

    private Counter counter;

    public CounterThread(Counter counter, String name) {
        this.counter = counter;
        this.setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            logger.info(this.getName());

            counter.add(i);
            counter.addCrumb(this.getName() + "-"+ i);
        }
    }

}
