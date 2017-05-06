package com.ashbyp.scratch.lottery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class UtilsTest {
    @Test
    public void test_mapFromArrays() {
        Map<String, Integer> m = Utils.mapFromArrays(
                new String[] { "ten", "three", "five" }, new Integer[] { 10, 3, 5 });

        assertEquals(m.size(), 3);
        assertEquals(m.get("three"), Integer.valueOf(3));
        assertEquals(m.get("five"), Integer.valueOf(5));
        assertEquals(m.get("ten"), Integer.valueOf(10));
    }

    @Test
    public void test_sortByValue() {
        Map<String, Integer> m = Utils.sortByValue(Utils.mapFromArrays(
                new String[] { "ten", "three", "five" }, new Integer[] { 10, 3, 5 }));

        Iterator<Map.Entry<String, Integer>> it = m.entrySet().iterator();
        assertEquals(it.next().getValue(), Integer.valueOf(3));
        assertEquals(it.next().getValue(), Integer.valueOf(5));
        assertEquals(it.next().getValue(), Integer.valueOf(10));
    }
    
    @Test
    public void test_insertSorted() {
        int[] a = new int[5];
        Utils.insertSorted(a, 0, 10);
        assertTrue(Arrays.equals(a, new int[] {10, 0, 0, 0, 0}));
        Utils.insertSorted(a, 1, 12);
        assertTrue(Arrays.equals(a, new int[] {10, 12,  0, 0, 0}));
        Utils.insertSorted(a, 2, 5);
        assertTrue(Arrays.equals(a, new int[] {5, 10, 12,  0, 0}));
        Utils.insertSorted(a, 3, 11);
        assertTrue(Arrays.equals(a, new int[] {5, 10, 11, 12, 0}));
        Utils.insertSorted(a, 4, 100);
        assertTrue(Arrays.equals(a, new int[] {5, 10, 11, 12, 100}));
    }
}







