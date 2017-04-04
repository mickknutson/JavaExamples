package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by mickknutson on 2/17/15.
 */
public class ExecuterExample {

    private static final Logger logger = LoggerFactory.getLogger(ExecuterExample.class);

    public static void main(String[] args) throws Exception {

        // Do commands 1-2

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<String> result = service.submit(
                new Callable<String>() {

                    @Override
                    public String call() throws Exception {
                        // call commands 3-5
                        return "Foo Bar says what????";
                    }
                }
        );

        logger.info("Callable running");

        while(!result.isDone()) Thread.sleep(1_000);

        logger.info("looking for Callable result");


        /**
         * Now block to get a result.
         */
        try {

            String response = result.get(42, TimeUnit.MILLISECONDS); // TimeUnit.SECONDS
            logger.info(response);

        } catch (InterruptedException | ExecutionException e) {
            throw new Exception();
        }

        service.shutdown();

        // Do commands 6-10
    }

} // The End..
