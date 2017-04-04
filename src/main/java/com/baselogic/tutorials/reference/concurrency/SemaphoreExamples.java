package com.baselogic.tutorials.reference.concurrency;

import java.util.concurrent.Semaphore;

/**
 * Created by mickknutson on 11/30/16.
 */
public class SemaphoreExamples {

    private int value = 0;

    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore available = new Semaphore(100);

    public int getNextValue() throws InterruptedException {
        try {
            mutex.acquire();
            return value++;
        } finally {
            mutex.release();
        }
    }
}
