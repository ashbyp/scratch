package com.ashbyp.scratch.stream;

import java.util.ArrayList;
import java.util.List;

public class FlatMap {   
    public static void main(String[] args) {
//        Integer[] i1 = {9,2};
//        Integer[] i2 = {3,3};
//        Integer[] i3 = {5,6};
//        
//        Stream.of(i1, i2, i3).
//                flatMap(Arrays::stream).
//                distinct().
//                sorted().
//                filter(x -> x > 4).
//                forEach(System.out::println);
        
        List<List<Integer>> in = new ArrayList<List<Integer>>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        l1.add(1);
        l1.add(6);
        List<Integer> l2 = new ArrayList<>();
        l2.add(100);
        l2.add(90);
        l2.add(20);
        in.add(l1);
        in.add(l2);
        
        //in.stream().map(x -> x.stream().sorted()).collect(Collectors.)
        
    } 
}
