package com.ashbyp.scratch.interview;

import java.util.HashSet;

public class Dupes {
    private static int[] nums = {1, 2, 3, 3, 4, 5, 5, 10};

    public static void main(String[] args) {
        var seen = new HashSet<Integer>();
        var dupe = new HashSet<Integer>();
        for (var n : nums) {
            if (seen.contains(n)) {
                dupe.add(n);
            }
            else {
                seen.add(n);
            }
        }
        System.out.println(dupe);
    }
}
