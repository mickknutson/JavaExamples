package com.baselogic.tutorials.reference.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExamples {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierExamples.class);


    private static class MyTask1 implements Runnable {

        CyclicBarrier firstBarrier;
        CyclicBarrier secondBarrier;

        Random delay = new Random(5_000);

        MyTask1(CyclicBarrier firstBarrier, CyclicBarrier secondBarrier) {
            this.firstBarrier = firstBarrier;
            this.secondBarrier = secondBarrier;
        }

        @Override
        public void run() {



            // p1()
            // p1Barrier.await()

            // p2()
            // p2Barrier.await()

            // p3()
            // p3Barrier.await()
            // some cleanup



            // put Future into p2()
            //...
            // put Future into p3()


            System.out.println(Thread.currentThread().getName() + " Starting my first task ...");
            try {
                Thread.sleep(2_000);
                firstBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2_000);
//                System.out.println("...");
                System.out.println(Thread.currentThread().getName() + " Starting my second task ...");
                secondBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("...");
            System.out.println(Thread.currentThread().getName() + " Completing task ...");
        }
    }

    private static class MyTask2 implements Runnable {
        CyclicBarrier firstBarrier;
        CyclicBarrier secondBarrier;

        MyTask2(CyclicBarrier firstBarrier, CyclicBarrier secondBarrier) {
            this.firstBarrier = this.firstBarrier;
            this.secondBarrier = this.secondBarrier;
        }

        @Override
        public void run() {
            System.out.println("In MyTask2 ...");
            try {
                Thread.sleep(10_000);
                firstBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("Completing MyTask2 ...");
        }
    }




    public static void main(String[] args) {

        CyclicBarrier firstBarrier = new CyclicBarrier(2);
        CyclicBarrier secondBarrier = new CyclicBarrier(2);

        Thread t1 = new Thread(new MyTask1(firstBarrier, secondBarrier));
        Thread t2 = new Thread(new MyTask1(firstBarrier, secondBarrier));
//        Thread t2 = new Thread(new MyTask2(firstBarrier, secondBarrier));
        t1.start();
        t2.start();
    }

} // The End...
