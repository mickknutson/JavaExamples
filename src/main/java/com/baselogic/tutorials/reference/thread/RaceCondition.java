package com.baselogic.tutorials.reference.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mickknutson on 4/29/15.
 */
public class RaceCondition implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RaceCondition.class);

    static ArrayList<String> theList;

    public void run() {
        for (int i = 0; i < 20; i++) {
            theList.add(Thread.currentThread().getName() + i);
        }
    }

    public static void main(String[] args) throws Exception {

        RaceCondition race = new RaceCondition();

        theList = new ArrayList<String>();

        Thread thread1 = new Thread(race, "A");
        Thread thread2 = new Thread(race, "B");
        Thread thread3 = new Thread(race, "C");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        Iterator it = theList.iterator();
        while (it.hasNext()) {
            logger.info("{}", it.next());
        }

        logger.info("Size of the list {}", theList.size());
    }
}
