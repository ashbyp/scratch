package com.ashbyp.scratch.lottery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InsertionSortArrayLottery extends AbstractLottery<int[]> {

    public InsertionSortArrayLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        super(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public int[] createAllNumbers(int highNumber) {
        return IntStream.range(1, highNumber + 1).toArray();
    }

    @Override
    public int[] drawNumbers(int[] allNumbers, int numbersPerTicket, RandomNumberProvider rn) {
        int[] picked = new int[numbersPerTicket];
        Set<Integer> used = new HashSet<>();
        while (used.size() < numbersPerTicket) {
            int slot = rn.nextInt(allNumbers.length);
            if (!used.contains(slot)) {
                Utils.insertSorted(picked, used.size(), allNumbers[slot]);
                used.add(slot);
            }
        }
        return picked;
    }

    @Override
    public List<int[]> pickTickets(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        List<Set<Integer>> tickets = Utils.pickTickets(highNumber, numbersPerTicket, numTickets, rn);
        return tickets.stream().map(Utils::setToSortedIntArray).collect(Collectors.toList());
    }

    @Override
    public boolean match(int[] numbersDrawn, List<int[]> tickets) {
        for (int[] ticket : tickets) {
            if (Arrays.equals(numbersDrawn, ticket)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String format(int[] tickets) {
        return Arrays.toString(tickets);
    }
}
