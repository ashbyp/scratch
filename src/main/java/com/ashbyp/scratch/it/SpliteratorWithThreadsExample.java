package com.ashbyp.scratch.it;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SpliteratorWithThreadsExample {
    public static void main(String[] args) {
        // Create a list of elements
        List<String> elements = new ArrayList<>();
        for (char ch = 'a'; ch <= 'h'; ch++) {
            elements.add(String.valueOf(ch));
        }

        // Create a Spliterator for the list
        Spliterator<String> spliterator1 = elements.spliterator();

        // Split the Spliterator
        Spliterator<String> spliterator2 = spliterator1.trySplit();

        // Define a consumer to process elements
        Consumer<String> action = (element) -> System.out.println(Thread.currentThread().getName() + ": " + element);

        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Process elements using spliterator1 in one thread
        executorService.submit(() -> {
            System.out.println("Processing elements using spliterator1:");
            spliterator1.forEachRemaining(action);
        });

        // Process elements using spliterator2 in another thread (if it is not null)
        if (spliterator2 != null) {
            executorService.submit(() -> {
                System.out.println("Processing elements using spliterator2:");
                spliterator2.forEachRemaining(action);
            });
        }

        // Shutdown the ExecutorService and wait for tasks to complete
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Tasks interrupted");
        }
        System.out.println("All tasks completed.");
    }
}
