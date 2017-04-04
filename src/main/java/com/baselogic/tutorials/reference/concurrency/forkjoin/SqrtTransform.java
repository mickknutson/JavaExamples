package com.baselogic.tutorials.reference.concurrency.forkjoin;


import java.util.concurrent.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * A simple example of the basic divide-and-conquer strategy.
 * In this case, RecursiveAction is used.
 *
 *
 * A ForkJoinTask (via RecursiveAction) that transforms
 * the elements in an array of doubles into their square roots.
 */
class SqrtTransform extends RecursiveAction {

    // The threshold value is arbitrarily set at 1,000 in this example.
    // In real-world code, its optimal value can be determined by
    // profiling and experimentation.
    final int seqThreshold = 100_000;

    // Array to be accessed.
    double[] data;

    // Determines what part of data to process.
    int start, end;

    SqrtTransform(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }

    /**
     * This is the method in which parallel computation will occur.
     */
    @Override
    protected void compute() {

        System.out.println("Current Thread: " +Thread.currentThread().getName());

        // If number of elements is below the sequential threshold,
        // then process sequentially.
        if ((end - start) < seqThreshold) {

            // Transform each element into its square root.
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {

            // Otherwise, continue to break the data into smaller pieces.
            // Find the midpoint.
            int middle = (start + end) / 2;

            // Invoke new tasks, using the subdivided data.
            invokeAll(new SqrtTransform(data, start, middle),
                    new SqrtTransform(data, middle, end));
        }
//        System.out.println(); // just add a blank line...
    }
}

/**
 * Demonstrate parallel execution.
 */
class ForkJoinDemo {
    public static void main(String args[]) {

        // Create a task pool.
        ForkJoinPool fjp = new ForkJoinPool();
        double[] nums = new double[100_000];

        // Give nums some values.
        for (int i = 0; i < nums.length; i++)
            nums[i] = (double) i;


//        System.out.println("A portion of the original sequence:");
//        for (int i = 0; i < 10; i++)
//            System.out.print(nums[i] + " ");
//        System.out.println("");


        System.out.println("A portion of the original sequence:");
        IntStream is = IntStream.range(0, 10);
        is.forEach(j -> System.out.print(nums[j] + " "));

        System.out.println("\n");
        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        // Start the main ForkJoinTask.
        fjp.invoke(task);

//        System.out.println(); // just add a blank line...
        System.out.println("A portion of the transformed sequence" +
                " (to four decimal places):");

        for (int i = 0; i < 10; i++)
            System.out.format("%.4f ", nums[i]);

        System.out.println();
    }
}

