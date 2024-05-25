package com.ashbyp.scratch.it;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SpliteratorExample {
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

        // Process elements using spliterator1
        System.out.println("Processing elements using spliterator1:");
        spliterator1.forEachRemaining(action);

        // Process elements using spliterator2 (if it is not null)
        if (spliterator2 != null) {
            System.out.println("Processing elements using spliterator2:");
            spliterator2.forEachRemaining(action);
        }
    }
}

