package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Math.*;

/**
 * Created by mickknutson on 4/29/15.
 */
public class PiCalculator implements Callable<Double> {

    private static final Logger logger = LoggerFactory.getLogger(PiCalculator.class);

    private String name;

    /**
     * call() method that returns a result
     * @return
     */
    public Double call() {
        // Machin's formula
        // pi/4 = 4 * arctan(1/5) - arctan(1/239);
        double pi = 16 * atan(1.0 / 5.0) - 4 * atan(1.0 / 239.0);
        return pi;
    }

    public static void main(String[] args) throws Exception {

        final int numberOfThreads = 300;

        long start = System.nanoTime();

        ExecutorService ex = Executors.newCachedThreadPool();
        List<Future<Double>> futures = new ArrayList<>(numberOfThreads);

        for(int i=0; i<numberOfThreads; i++) {
            futures.add(ex.submit(new PiCalculator()));
        }

        for(Future<Double> ft: futures) {

            Double pi = ft.get();
            logger.info("pi for thread is {}", pi);
        }

        ex.shutdown();

        long end = System.nanoTime();
        logger.info("Time taken: {} sec.", (end - start) / (1000 * 1000 * 1000.0));

    }


//    public PiCalculator(){}
//    public PiCalculator(String name){
//        this.name = name;
//    }
//
//    public String getName(){ return this.name;}
}
