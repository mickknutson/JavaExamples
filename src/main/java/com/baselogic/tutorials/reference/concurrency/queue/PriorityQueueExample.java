package com.baselogic.tutorials.reference.concurrency.queue;


import com.baselogic.tutorials.comparable.VowelComparator;
import com.baselogic.tutorials.comparable.CustomerComparator;
import com.baselogic.tutorials.domain.Customer;
import com.baselogic.tutorials.util.CustomerFixture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *
 * An unbounded priority {@linkplain Queue queue} based on a priority heap.
 * The elements of the priority queue are ordered according to their
 * {@linkplain Comparable natural ordering}, or by a {@link Comparator}
 * provided at queue construction time, depending on which constructor is
 * used.  A priority queue does not permit {@code null} elements.
 * A priority queue relying on natural ordering also does not permit
 * insertion of non-comparable objects (doing so may result in
 * {@code ClassCastException}).
 *
 * <p>The <em>head</em> of this queue is the <em>least</em> element
 * with respect to the specified ordering.  If multiple elements are
 * tied for least value, the head is one of those elements -- ties are
 * broken arbitrarily.  The queue retrieval operations {@code poll},
 * {@code remove}, {@code peek}, and {@code element} access the
 * element at the head of the queue.
 *
 */
public class PriorityQueueExample {

    private static final Logger logger = LoggerFactory.getLogger(PriorityQueueExample.class);

    /**
     *
     * @return
     */
    public PriorityQueue priorityWithVowelComparator() {

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(10, new VowelComparator());

        priorityQueue.add("orange");
        priorityQueue.add("fig");
        priorityQueue.add("watermelon");
        priorityQueue.add("lemon");
        priorityQueue.add("lime");
        priorityQueue.add("pinapple");

        return priorityQueue;
    }

    public Object[] priorityWithVowelComparator_ToArray() {

        return priorityWithVowelComparator().toArray();
    }

    /**
     *
     * @return
     */
    public PriorityQueue priorityWithCustomerComparator() {

        Comparator<Customer> customerComparator = new CustomerComparator();

        PriorityQueue<Customer> priorityQueue = new PriorityQueue<>(10, customerComparator);
        priorityQueue.addAll(
                CustomerFixture.listOfCustomers(10)
        );

        while (priorityQueue.size() != 0) {
            logger.info(priorityQueue.remove().toString());
        }

        return priorityQueue;
    }


    /**
     *
     * @return
     */
    public Object[] priorityWithCustomerComparator_ToArray() {

        return priorityWithCustomerComparator().toArray();
    }

    /**
     * Throws ClassCastException as Customer does not implement Comparable
     */
    public PriorityQueue priorityWithoutCustomerComparator() {

        PriorityQueue<Customer> priorityQueue = new PriorityQueue<>(10);
        priorityQueue.addAll(
                CustomerFixture.listOfCustomers(10)
        );

        while (priorityQueue.size() != 0) {
            logger.info(priorityQueue.remove().toString());
        }

        return priorityQueue;
    }

    /**
     *
     * @return
     */
    public Object[] priorityWithoutCustomerComparator_ToArray() {

        return priorityWithoutCustomerComparator().toArray();
    }
}
