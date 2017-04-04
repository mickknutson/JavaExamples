package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

/**
 * CountDownLatch is basically a counter, designed to work in concurrent environment. It has only a handful of methods:
 *
 * - void await(): Waits until the latch has counted down to zero.
 * boolean await(long timeout, TimeUnit unit): Like above, but waits only for given time and returns false when not reached zero.
 * void countDown(): Decrements the number in the latch. Notifies awaiting threads when reached zero.
 * long getCount(): Returns current value of latch.
 *
 * The latch is created with some counter value, tasks decrease counter’s value down to zero, which signals some other thread that jobs have finished.
 *
 * Solution:
 *
 * In the following example we’re going to create two tasks that convert a
 * message concurrently and return the results.
 * A task calls latch.countDown() method after doing its work.
 * The main thread calls latch.await() hence waits until all
 * tasks (2 in our case) decrease the latch. When that happens,
 * the main thread is unblocked and continues its work – printing results.
 */
public class CountDownLatchExamples {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchExamples.class);

    private static abstract class ConverterTask implements Callable<String> {

        private final CountDownLatch latch;
        private String value;

        public ConverterTask(CountDownLatch latch, String value) {
            this.latch = latch;
            this.value = value;
        }

        @Override
        public String call() throws Exception {
            value = convert(value);
            latch.countDown();
            return value;
        }

        protected abstract String convert(String value);
    }

    private static class Lowerer extends ConverterTask {

        public Lowerer(CountDownLatch latch, String value) {
            super(latch, value);
        }

        @Override
        public String convert(String value) {
            return value.toLowerCase();
        }
    }

    private static class Upperer extends ConverterTask {

        public Upperer(CountDownLatch latch, String value) {
            super(latch, value);
        }

        @Override
        public String convert(String value) {
            return value.toUpperCase();
        }
    }

    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executor = Executors.newCachedThreadPool();

        logger.info("Check point {}", LocalDateTime.now());

        String message = "Hello, World: ";
        List<Callable<String>> tasks = asList(
                new Lowerer(latch, message+"1"),
                new Upperer(latch, message+"2"),
                new Lowerer(latch, message+"3"),
                new Upperer(latch, message+"4")
        );

        logger.info("Check point {}", LocalDateTime.now());

        List<Future<String>> results = new LinkedList<>();
        for (Callable<String> task : tasks) {
            logger.info("new task added: {}", task.getClass());
            results.add(executor.submit(task));
        }

        logger.info("sleep...");
        Thread.sleep(5_000);

        // wait for tasks to finish:
        latch.await();

        logger.info("Check point {}", LocalDateTime.now());
        logger.info("Shutting down the executor.");
        executor.shutdown();

        logger.info("Check point {}", LocalDateTime.now());
        logger.info("Getting converted results...");
        for (Future<String> result : results) {
//            System.out.printf("Converted message: %s%n", result.get());
            logger.info("converted results: {}", result.get());
        }
        logger.info("Check point {}", LocalDateTime.now());
    }
} // The End...
