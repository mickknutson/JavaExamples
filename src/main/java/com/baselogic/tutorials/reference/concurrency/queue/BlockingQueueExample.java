package com.baselogic.tutorials.reference.concurrency.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by mickknutson on 2/17/15.
 */
public class BlockingQueueExample {

    // Send FINISHED down this queue when test completes.
    static final BlockingQueue<Object> finished = new ArrayBlockingQueue<>(1);
    // FINISHED cookie.
    static final Object FINISHED = new Object();


    public static void main(String[] args) throws Exception{
        Thread test = new Thread(new Runnable() {

            @Override
            public void run() {
                // Do your stuff.
                // ...
                // Signal we finished.
                finished.add(FINISHED);
            }
        });

        // Start the test in it's own thread.
        test.start();
        try {
            // Wait for your time.
            if (FINISHED == finished.poll(5, TimeUnit.MILLISECONDS)) {
                // It completed! No problems.
            } else {
                // It hasn't finished! Interrupt it.
                test.interrupt();
            };
        } catch (InterruptedException ex) {
            // We were interrupted! Do something.
            test.interrupt();
            // Rethrow it.
            throw(ex);
        }
    }

}
