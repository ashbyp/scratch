package com.ashbyp.scratch.lottery;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;

public class Utils {
    private static BigInteger fact(BigInteger n) {
        if (n.equals(BigInteger.valueOf(0))) {
            return BigInteger.valueOf(1);
        } else {
            return (n.multiply(fact(n.subtract(BigInteger.valueOf(1)))));
        }
    }

    // 49!/(6!*(49-6)!)
    public static int combinations(int setSize, int selectionSize) {
        BigInteger n = BigInteger.valueOf(setSize);
        BigInteger m = BigInteger.valueOf(selectionSize);
        BigInteger c = fact(n).divide((fact(m).multiply(fact(BigInteger.valueOf(setSize - selectionSize)))));
        return c.intValue();
    }

    public static int[] setToSortedIntArray(Set<Integer> set) {
        int[] arr = new int[set.size()];
        int idx = 0;
        for (Integer n : set) {
            arr[idx++] = n;
        }
        Arrays.sort(arr);
        return arr;
    }

    public static short[] setToSortedShortArray(Set<Short> set) {
        short[] arr = new short[set.size()];
        int idx = 0;
        for (Short n : set) {
            arr[idx++] = n;
        }
        Arrays.sort(arr);
        return arr;
    }

    public static byte[] setToSortedByteArray(Set<Byte> set) {
        byte[] arr = new byte[set.size()];
        int idx = 0;
        for (Byte n : set) {
            arr[idx++] = n;
        }
        Arrays.sort(arr);
        return arr;
    }

    public static List<Integer> setToSortedIntList(Set<Integer> set) {
        return set.stream().sorted().collect(Collectors.toList());
    }

    public static List<Set<Integer>> pickTickets(int highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        List<Set<Integer>> tickets = new ArrayList<>();
        for (int i = 0; i < numTickets; i++) {
            Set<Integer> ticket = new HashSet<>();
            while (ticket.size() < numbersPerTicket) {
                ticket.add(rn.nextInt(highNumber) + 1);
            }
            tickets.add(ticket);
        }
        return tickets;
    }

    public static List<TIntSet> pickTicketsTrove(int highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        List<TIntSet> tickets = new ArrayList<>();
        for (int i = 0; i < numTickets; i++) {
            TIntSet ticket = new TIntHashSet();
            while (ticket.size() < numbersPerTicket) {
                ticket.add(rn.nextInt(highNumber) + 1);
            }
            tickets.add(ticket);
        }
        return tickets;
    }

    public static List<Set<Short>> pickTickets(short highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        List<Set<Short>> tickets = new ArrayList<>();
        for (int i = 0; i < numTickets; i++) {
            Set<Short> ticket = new HashSet<>();
            while (ticket.size() < numbersPerTicket) {
                ticket.add((short) (rn.nextInt(highNumber) + 1));
            }
            tickets.add(ticket);
        }
        return tickets;
    }

    public static List<Set<Byte>> pickTickets(byte highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        List<Set<Byte>> tickets = new ArrayList<>();
        for (int i = 0; i < numTickets; i++) {
            Set<Byte> ticket = new HashSet<>();
            while (ticket.size() < numbersPerTicket) {
                ticket.add((byte) (rn.nextInt(highNumber) + 1));
            }
            tickets.add(ticket);
        }
        return tickets;
    }

    public static int[] doInsertionSort(int[] input, int n) {
        int temp;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    public static void insertSorted(int[] input, int n, int num) {
        int i = 0;
        for (; i < n; i++) {
            if (num < input[i]) {
                for (int j = n; j > i; j--) {
                    input[j] = input[j - 1];
                }
                break;
            }
        }
        input[i] = num;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Entry<K, V>>() {
            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            };
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static <K, V> Map<K, V> mapFromArrays(K[] keys, V[] values) {
        HashMap<K, V> result = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            result.put(keys[i], values[i]);
        }
        return result;
    }

    public static boolean fastArrayEquals(int[] a, int[] a2, int length) {
        for (int i = 0; i < length; i++)
            if (a[i] != a2[i])
                return false;
        return true;
    }
}
