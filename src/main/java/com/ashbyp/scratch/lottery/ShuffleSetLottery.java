package com.ashbyp.scratch.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleSetLottery extends CommonTypeLottery<Set<Integer>> {

    public ShuffleSetLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        super(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public Set<Integer> createAllNumbers(int highNumber) {
        return IntStream.range(1, highNumber + 1).boxed().collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> drawNumbers(Set<Integer> allNumbers, int numbersPerTicket, RandomNumberProvider rn) {
        List<Integer> copy = new ArrayList<>();
        copy.addAll(allNumbers);
        Collections.shuffle(copy, rn);
        return new HashSet<>(copy.subList(0, numbersPerTicket));
    }

    @Override
    public List<Set<Integer>> pickTickets(int highNumber, int numbersPerTicket, int numTickets,
            RandomNumberProvider rn) {
        return Utils.pickTickets(highNumber, numbersPerTicket, numTickets, rn);
    }

    @Override
    public String format(Set<Integer> t) {
        return t.toString();
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
}
