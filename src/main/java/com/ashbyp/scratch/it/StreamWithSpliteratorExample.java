package com.ashbyp.scratch.it;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamWithSpliteratorExample {
    public static void main(String[] args) {
        // Create a list of elements
        List<String> elements = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            elements.add(String.valueOf(ch));
        }

        // Create a Spliterator for the list
        Spliterator<String> spliterator = elements.spliterator();

        // Create a parallel stream from the Spliterator
        Stream<String> parallelStream = StreamSupport.stream(spliterator, true);

        // Define an action to process elements
        parallelStream.forEach(element -> {
            System.out.println(Thread.currentThread().getName() + ": " + element);
        });
    }
}

