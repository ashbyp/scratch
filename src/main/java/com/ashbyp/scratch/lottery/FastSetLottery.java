package com.ashbyp.scratch.lottery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class FastSetLottery extends AbstractLottery<int[], Set<Integer>> {

    public FastSetLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        super(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public int[] createAllNumbers(int highNumber) {
        return IntStream.range(1, highNumber + 1).toArray();
    }

    @Override
    public Set<Integer> drawNumbers(int[] allNumbers, int numbersPerTicket, RandomNumberProvider rn) {
        Set<Integer> picked = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        while (used.size() < numbersPerTicket) {
            int slot = rn.nextInt(allNumbers.length);
            if (!used.contains(slot)) {
                picked.add(allNumbers[slot]);
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
        return tickets.stream().anyMatch(x -> numbersDrawn.equals(x));
        
//        for (Set<Integer> ticket : tickets) {
//            if (ticket.equals(numbersDrawn)) {
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public String formatTicket(Set<Integer> ticket) {
        return ticket.toString();
    }

    @Override
    public String formatNumbers(int[] numbers) {
        return Arrays.toString(numbers);
    }
}
