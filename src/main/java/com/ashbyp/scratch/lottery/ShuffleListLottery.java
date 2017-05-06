package com.ashbyp.scratch.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleListLottery extends AbstractLottery<List<Integer>> {

    private final boolean COPY_LIST = true;

    public ShuffleListLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        super(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public List<Integer> createAllNumbers(int highNumber) {
        return IntStream.range(1, highNumber + 1).boxed().collect(Collectors.toList());
    }

    @Override
    public List<Integer> drawNumbers(List<Integer> allNumbers, int numbersPerTicket, RandomNumberProvider rn) {
        List<Integer> l = allNumbers;
        if (COPY_LIST) {
            l = new ArrayList<>();
            l.addAll(allNumbers);
        }
        Collections.shuffle(l, rn);
        List<Integer> s = l.subList(0, numbersPerTicket);
        Collections.sort(s);
        return s;
    }

    @Override
    public List<List<Integer>> pickTickets(int highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        List<Set<Integer>> tickets = Utils.pickTickets(highNumber, numbersPerTicket, numTickets, rn);
        return tickets.stream().map(x -> Utils.setToSortedIntList(x)).collect(Collectors.toList());
    }

    @Override
    public boolean match(List<Integer> numbersDrawn, List<List<Integer>> tickets) {
        for (List<Integer> ticket : tickets) {
            if (ticket.equals(numbersDrawn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String format(List<Integer> tickets) {
        return tickets.toString();
    }

}
