package com.ashbyp.scratch.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionQ {
    public static <T> List<T> maxOccurancesOf_1(List<T> input) {
        Map<T, Integer> counts = new HashMap<>();
        for (T t : input) {
            Integer c = counts.putIfAbsent(t, Integer.valueOf(1));
            if (c != null) {
                counts.put(t, c + 1);
            }
        }
        int max = Collections.max(counts.values());
        return input.stream().filter(x -> counts.get(x) == max).distinct().collect(Collectors.toList());
    }

    public static <T> List<T> maxOccurancesOf_2(List<T> input) {
        Map<T, Integer> counts = new HashMap<>();
        return input.stream().map(x -> {
            Integer c = counts.putIfAbsent(x, Integer.valueOf(1));
            if (c != null) {
                counts.put(x, c + 1);
            }
            return x;
        }).filter(x -> counts.get(x) == Collections.max(counts.values())).distinct().collect(Collectors.toList());
    }

    public static <T> List<T> maxOccurancesOf_3(List<T> input) {
        Map<T, Long> counts = input.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        long max = Collections.max(counts.values());
        return input.stream().filter(x -> counts.get(x) == max).distinct().collect(Collectors.toList());
    }

    public static <T> List<T> maxOccurancesOf_4(List<T> input) {
        Map<T, Long> counts = input.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        Optional<Long> max = counts.values().stream().collect(Collectors.maxBy(Long::compareTo));
        return input.stream().filter(x -> counts.get(x) == max.get()).distinct().collect(Collectors.toList());
    }

    public static <T> List<T> maxOccurancesOf_5(List<T> input) {
        Map<T, Integer> counts = new HashMap<>();
        int max = 0;
        for (T t : input) {
            int count = counts.merge(t, 1, Integer::sum);
            if (count > max) {
                max = count;
            }
        }
        int m = max;
        return input.stream().filter(x -> counts.get(x) == m).distinct().collect(Collectors.toList());
    }
    
    public static <T> List<T> maxOccurancesOf_9(List<T> input) {
        Map<T, Integer> counts = new HashMap<>();
        int max = 0;
        for (T t : input) {
            int count = counts.merge(t, 1, Integer::sum);
            if (count > max) {
                max = count;
            }
        }
        int m = max;
        return new ArrayList<>(input.stream().filter(x -> counts.get(x) == m).collect(Collectors.toSet()));
    }
    
    public static <T> List<T> maxOccurancesOf_6(List<T> input) {
        Map<T, Integer> counts = new HashMap<>();
        int max = 0;
        for (T t : input) {
            Integer c = counts.putIfAbsent(t, Integer.valueOf(1));
            if (c != null) {
                counts.put(t, c + 1);
                max = c + 1;
            }
        }
        Set<T> res = new HashSet<>();
        for (Map.Entry<T, Integer> entry: counts.entrySet()) {
            if (entry.getValue() == max) {
                res.add(entry.getKey());
            }
        }
        return new ArrayList<T>(res);
    }
    
    
    public static <T> List<T> maxOccurancesOf_8(List<T> input) {
        Map<T, Integer> counts = new HashMap<>();
        int max = 0;
        for (T t : input) {
            int count = counts.merge(t, 1, Integer::sum);
            if (count > max) {
                max = count;
            }
        }
        Set<T> res = new HashSet<>();
        for (Map.Entry<T, Integer> entry: counts.entrySet()) {
            if (entry.getValue() == max) {
                res.add(entry.getKey());
            }
        }
        return new ArrayList<T>(res);
    }
    
    public static <T> List<T> maxOccurancesOf_7(List<T> input) {
        Map<T, Integer> counts = new HashMap<>();
        int max = 0;
        for (T t : input) {
            int count = counts.merge(t, 1, Integer::sum);
            if (count > max) {
                max = count;
            }
        }
        int m = max;
        return new ArrayList<T>(input.stream().filter(x -> counts.get(x) == m).collect(Collectors.toSet()));
    }

    public static <T> long timeit(Function<List<T>, List<T>> f, List<T> input, int samples, List<T> expected) {
        long startTime = System.currentTimeMillis();
       if (!new HashSet<>(f.apply(input)).equals(new HashSet<>(expected))) {
            return 0;
        }
        for (int i = 0; i < samples; i++) {
            f.apply(input);
        }
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        List<String> l = Arrays.asList("B", "A", "B", "Z", "Z", "K", "H", "H");
        List<String> e = Arrays.asList("B", "Z", "H");
        
        int samples = 1000000;

        System.out.println("1: " + timeit(CollectionQ::maxOccurancesOf_1, l, samples, e));
        System.out.println("2: " + timeit(CollectionQ::maxOccurancesOf_2, l, samples, e));
        System.out.println("3: " + timeit(CollectionQ::maxOccurancesOf_3, l, samples, e));
        System.out.println("4: " + timeit(CollectionQ::maxOccurancesOf_4, l, samples, e));
        System.out.println("5: " + timeit(CollectionQ::maxOccurancesOf_5, l, samples, e));
        System.out.println("6: " + timeit(CollectionQ::maxOccurancesOf_6, l, samples, e));
        System.out.println("7: " + timeit(CollectionQ::maxOccurancesOf_7, l, samples, e));
        System.out.println("8: " + timeit(CollectionQ::maxOccurancesOf_8, l, samples, e));
        System.out.println("9: " + timeit(CollectionQ::maxOccurancesOf_9, l, samples, e));
    }
}
