package com.baselogic.tutorials.reference.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see http://tutorials.jenkov.com/java-concurrency/threadlocal.html
 */
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(MyRunnable.class);

        private ThreadLocal<Integer> threadLocal =
                new ThreadLocal<Integer>();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.info("---> MyRunnable.e: {}", e);
            }

            logger.info("---> MyRunnable: {}", threadLocal.get().toString());
        }
    }


    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();
    }

}
