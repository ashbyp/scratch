package com.ashbyp.scratch.interview;

import java.util.stream.Stream;

public class Palindrome {

    public static boolean isPalindrome_1(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static boolean isPalindrome_2(String s) {
        int s1 = 0;
        int s2 = s.length() - 1;

        while (s1 < s2) {
            if (s.charAt(s1) != s.charAt(s2)) {
                return false;
            }
            s1++;
            s2--;
        }
        return true;
    }

    public static boolean isPalindrome_3(String s) {
        for (int s1 = 0, s2 = s.length() - 1; s1 < s2; s1++, s2--) {
            if (s.charAt(s1) != s.charAt(s2)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = { "radar", "noon", "green" };
        Stream.of(words).forEach(x -> System.out.printf("%s = %b\n", x, isPalindrome_1(x)));
        Stream.of(words).forEach(x -> System.out.printf("%s = %b\n", x, isPalindrome_2(x)));
        Stream.of(words).forEach(x -> System.out.printf("%s = %b\n", x, isPalindrome_3(x)));
    }
}
