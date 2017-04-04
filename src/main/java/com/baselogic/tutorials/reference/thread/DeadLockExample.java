package com.baselogic.tutorials.reference.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mickknutson on 4/29/15.
 */
public class DeadLockExample {

    private static final Logger logger = LoggerFactory.getLogger(DeadLockExample.class);

    public static ClassA a;
    public static ClassB b;

    public static void main(String[] args) {
        a = new ClassA();
        b = new ClassB();
        a.start();
        b.start();
    }
}

class ClassA extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ClassA.class);

    public synchronized void method1() {
        logger.info(this.getName() + " acquired A's lock");
        Thread.yield(); // force deadlock
        logger.info(this.getName() + " calling B's method");
        DeadLockExample.b.method4();
        logger.info(this.getName() + " released A's lock");
    }

    public synchronized void method2() {
    }

    public void run() {
        this.method1();
    }
}

class ClassB extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ClassB.class);

    public synchronized void method3() {
        logger.info(this.getName() + " acquired B's lock");
        logger.info(this.getName() + " calling A's method");
        DeadLockExample.a.method2();
        logger.info(this.getName() + " released B's lock");
    }

    public synchronized void method4() {}

    public void run() {
        this.method3();
    }
}
