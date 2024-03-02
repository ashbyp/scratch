package com.ashbyp.scratch.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SortStr {

    static String[] words = {"cat", "act", "tac", "ate", "tea", "car"};

    public static void main(String[] args) {
        var m = new HashMap<String, HashSet<String>>();
        for (var w : words) {
            var sorted = sort(w);
            var set = m.computeIfAbsent(sorted, k -> new HashSet<>());
            set.add(w);
        }
        System.out.println(m.values());
    }


    public static String sort(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
}
