package com.baselogic.tutorials.reference.concurrency.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Hamcrest
//import static org.hamcrest.CoreMatchers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Matchers.any;

// PowerMock


/**
 * Created by mickknutson on 4/23/15.
 */
@RunWith(JUnit4.class)
public class PriorityQueueExampleTests {

    private static final Logger logger = LoggerFactory.getLogger(PriorityQueueExampleTests.class);

    private PriorityQueueExample pqe = new PriorityQueueExample();

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @Before
    public void beforeEachTest() throws Exception {
    }

    @After
    public void afterEachTest() throws Exception {
    }

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//

//    @Test
//    public void priorityQueueWithVowelComparator_ToArray_Test() throws Exception {
//
//        Queue<String> priorityQueue = pqe.priorityWithVowelComparator();
//
//        List<String> result = new ArrayList<>();
//
//        for(String s: priorityQueue){
//            result.add(s);
//        }
//
//        assertThat(result.size(), is(6));
//        Object[] arr = result.toArray();
//        assertThat(arr, is(new Object[]{"fig", "lemon", "pinapple", "orange", "lime", "watermelon"}));
//    }


    @Test
    public void priorityWithVowelComparator_ToArray_Test() throws Exception {

        Object[] result = pqe.priorityWithVowelComparator_ToArray();
        assertThat(result.length, is(6));
        assertThat(result, is(new Object[]{"fig", "lemon", "pinapple", "orange", "lime", "watermelon"}));
    }

    @Test
    public void priorityWithCustomerComparator() throws Exception {

        Object[] result = pqe.priorityWithCustomerComparator_ToArray();
        assertThat(result, is(new Object[]{}));
    }

    @Test(expected = ClassCastException.class)
    public void priorityWithoutCustomerComparator() throws Exception {

        Object[] result = pqe.priorityWithoutCustomerComparator_ToArray();
        assertThat(result, is(new Object[]{}));
    }

}
