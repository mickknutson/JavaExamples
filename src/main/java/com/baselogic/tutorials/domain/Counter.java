package com.baselogic.tutorials.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by mickknutson on 2/17/15.
 */
public class Counter {

    private static final Logger logger = LoggerFactory.getLogger(Counter.class);

    private long count = 0;
    private String name;

    private AtomicLong atomicCount = new AtomicLong();

    private Collection<String> crumbs = new LinkedList<>();




    // Java Concurrent Locks
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();



    public Counter() {
        this.name = "default";
    }

    public Counter(String name) {
        this.name = name;
    }

    public Collection<String> getCrumbs() {
        return crumbs;
    }

    public void setCrumbs(Collection<String> crumbs) {
        this.crumbs = crumbs;
    }

    public void addCrumb(String crumb){
        this.crumbs.add(crumb);
    }



    /**
     * Un-synchronized add
     */
    public void add(){

        // Read Lock engaged
        readWriteLock.readLock().lock();

        logger.info("original value: {}", this.count);

        // Write Lock engaged
        readWriteLock.writeLock().lock();

        this.count++;

        readWriteLock.writeLock().unlock();

        // Remove Read Lock
        logger.info("{} increment to {}", name, this.count);

        readWriteLock.readLock().unlock();
    }

    public void setCount(long value){


        atomicCount.addAndGet(42);

        readWriteLock.writeLock().lock();

        try {
            this.count = value;
            logger.info("{} increment to {}", name, this.count);
        }
        finally
        {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Read Operation Only
     */
    public long getCount(){

        readWriteLock.readLock().lock();

        try {
            logger.info("Return Count {}", this.count);
            return this.count;
        }
        finally
        {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * Un-synchronized add
     */
    public void add(long value){
        logger.info("{} added {}", name, value);

        this.count += value;

        atomicCount.getAndAdd(1L);
    }

    /**
     * synchronized add
     */
    public synchronized void synchronizedAdd(long value){

        logger.info("{} added {}", name, value);
        this.count += value;

    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
