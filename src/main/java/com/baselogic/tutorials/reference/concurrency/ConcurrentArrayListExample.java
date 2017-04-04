package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class ConcurrentArrayListExample {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentArrayListExample.class);


    static int numWrites = 100;
    List<String> list;

    public ConcurrentArrayListExample() {
        list = new ArrayList<>(100_000);
        for (int i = 0; i < 100_000; i++) {
            list.add("Item" + i);
        }
    }

    public static void main(String[] args) {
        if (args.length == 1)
            numWrites = Integer.parseInt(args[0]);

        ConcurrentArrayListExample ex = new ConcurrentArrayListExample();
        ex.useConcurrentArrayList();
        ex.useSynchronizedArrayList();
    }

    public void useConcurrentArrayList() {

        List<String> localList = new CopyOnWriteArrayList<>(list);

        long start = System.currentTimeMillis();
        // lots of reads
        for (int i = 0; i < localList.size(); i++) {
            String s = localList.get(i);
        }
        // some writes
        for (int i = 0; i < numWrites; i++) {
            localList.add("New String");
        }

        long end = System.currentTimeMillis();

        logger.info("Time for concurrent: {} ms.", (end - start));
    }

    public void useSynchronizedArrayList() {
        List<String> localList = Collections.synchronizedList(list);

        long start = System.currentTimeMillis();

        // lots of reads
        for (int i = 0; i < localList.size(); i++) {
            String s = localList.get(i);
        }

        // some writes
        for (int i = 0; i < numWrites; i++) {
            localList.add("New String");
        }

        long end = System.currentTimeMillis();

        logger.info("Time for synchronized: {} ms.", (end - start));

    }
}
