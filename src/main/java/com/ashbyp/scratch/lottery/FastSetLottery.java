package com.ashbyp.scratch.lottery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FastSetLottery extends AbstractLottery<Set<Integer>> {

    public FastSetLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        super(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public Set<Integer> createAllNumbers(int highNumber) {
        return IntStream.range(1, highNumber + 1).boxed().collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> drawNumbers(Set<Integer> allNumbers, int numbersPerTicket, RandomNumberProvider rn) {
        Set<Integer> picked = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        Integer[] all = allNumbers.toArray(new Integer[1]);
        while (used.size() < numbersPerTicket) {
            int slot = rn.nextInt(allNumbers.size());
            if (!used.contains(slot)) {
                picked.add(all[slot]);
                used.add(slot);
            }
        }
        return picked;
    }

    @Override
    public List<Set<Integer>> pickTickets(int highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        return Utils.pickTickets(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public boolean match(Set<Integer> numbersDrawn, List<Set<Integer>> tickets) {
        for (Set<Integer> ticket : tickets) {
            if (ticket.equals(numbersDrawn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String format(Set<Integer> tickets) {
        return tickets.toString();
    }
}
