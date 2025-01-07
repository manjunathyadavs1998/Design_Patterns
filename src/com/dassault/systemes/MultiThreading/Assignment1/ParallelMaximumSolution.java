package src.com.dassault.systemes.MultiThreading.Assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelMaximumSolution {
    public static void main(String[] args) throws Exception {
        int max = 1000000000;  // Total number of elements
        int threadCount = 100; // Number of threads
        Random random = new Random();
        List<Integer> list = new ArrayList<>(max);

        // Generate 1 million random numbers
        for (int i = 0; i < max; i++) {
            list.add(random.nextInt());
        }

        // Start measuring time
        long startTime = System.currentTimeMillis();

        // Split the work between 100 threads
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int chunkSize = max / threadCount;

        // Create a list of Callable tasks
        List<Callable<Integer>> tasks = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int startIdx = i * chunkSize;
            final int endIdx = (i == threadCount - 1) ? max : (i + 1) * chunkSize;

            tasks.add(() -> {
                int maxInChunk = Integer.MIN_VALUE;
                for (int j = startIdx; j < endIdx; j++) {
                    maxInChunk = Math.max(maxInChunk, list.get(j));
                }
                return maxInChunk;
            });
        }

        // Submit the tasks to the executor and get the results
        List<Future<Integer>> results = executor.invokeAll(tasks);

        // Find the overall maximum from all the thread results
        int finalMax = Integer.MIN_VALUE;
        for (Future<Integer> result : results) {
            finalMax = Math.max(finalMax, result.get());
        }

        // Shutdown the executor
        executor.shutdown();

        // End measuring time
        long endTime = System.currentTimeMillis();

        System.out.println("Parallel Method Time: " + (endTime - startTime) + " ms");
        System.out.println("Maximum Value: " + finalMax);
    }
}
