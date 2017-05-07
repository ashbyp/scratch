package com.ashbyp.scratch.lottery;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;

public class TroveSetLottery extends AbstractLottery<int[], TIntSet> {

    public TroveSetLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        super(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public  int[] createAllNumbers(int highNumber) {
        return IntStream.range(1, highNumber + 1).toArray();
    }

    @Override
    public TIntSet drawNumbers(int[] allNumbers, int numbersPerTicket, RandomNumberProvider rn) {
        TIntSet picked = new TIntHashSet();
        TIntSet used = new TIntHashSet();
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
    public List<TIntSet> pickTickets(int highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        return Utils.pickTicketsTrove(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public boolean match(TIntSet numbersDrawn, List<TIntSet> tickets) {
        for (TIntSet ticket : tickets) {
            if (ticket.equals(numbersDrawn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String formatTicket(TIntSet ticket) {
        return ticket.toString();
    }

    @Override
    public String formatNumbers(int[] numbers) {
        return Arrays.toString(numbers);
    }
}
