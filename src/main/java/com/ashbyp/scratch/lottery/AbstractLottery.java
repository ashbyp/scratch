package com.ashbyp.scratch.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class AbstractLottery<T> {
    private final int numbersPerTicket;
    private final int numTickets;
    private final T allNumbers;
    private final List<T> tickets;

    abstract T createAllNumbers(int highNumber);

    abstract T drawNumbers(T allNumbers, int numbersPerTicket, RandomNumberProvider rn);

    abstract List<T> pickTickets(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn);

    abstract boolean match(T numbersDrawn, List<T> tickets);

    abstract String format(T ticket);

    protected String getName() {
        return getClass().getSimpleName();
    }

    public AbstractLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        System.out.printf("Running %s (%s) +++++++++++++++++++++\n", getName(), rn.getName());

        this.createAllNumbers(highNumber);
        this.numbersPerTicket = numbersPerTicket;
        this.numTickets = numTickets;
        this.allNumbers = createAllNumbers(highNumber);
        this.tickets = pickTickets(highNumber, this.numbersPerTicket, this.numTickets, rn);

        System.out.println("Numbers are  " + format(allNumbers));
        System.out.println("Selection is " + format(tickets));
        System.out.printf("There are %,d ways to choose %d from %d\n", Utils.combinations(highNumber, numbersPerTicket),
                numbersPerTicket, highNumber);
    }

    public long runTests(ExecutorService executor, int numTests, RandomNumberProvider rn, int ticketCost,
            int gamesPerYear) throws Exception {

        List<Future<Long>> results = new ArrayList<>();

        for (int i = 0; i < numTests; i++) {
            results.add(executor.submit(() -> {
                long tries = 0;
                T picked;

                do {
                    picked = drawNumbers(this.allNumbers, this.numbersPerTicket, rn);
                    ++tries;
                } while (!match(picked, this.tickets));
                return tries;
            }));
        }

        System.out.printf("Submitted %d jobs, waiting for results...\n", numTests);

        long totalTries = 0;
        long minTries = 0;
        long maxTries = 0;
        int testNum = 0;

        for (Future<Long> f : results) {
            long tries = f.get();
            totalTries += tries;
            if (minTries == 0 || tries < minTries) {
                minTries = tries;
            }
            if (tries > maxTries) {
                maxTries = tries;
            }
            System.out.printf("Test %2d: %,12d tries to match (years=%,d)\n", ++testNum, tries, tries / gamesPerYear);
        }

        System.out.printf("Over %d tests: min=%,d   max=%,d   avg=%,d   minCost=%,d   maxCost=%,d   avgCost=%,d\n",
                numTests, minTries, maxTries, totalTries / numTests, minTries * this.numTickets * ticketCost,
                maxTries * this.numTickets * ticketCost, (totalTries / numTests) * this.numTickets * ticketCost);

        return totalTries;
    }

    private String format(List<T> tickets) {
        StringBuilder sb = new StringBuilder();
        for (T ticket : tickets) {
            if (sb.length() > 0)
                sb.append(", ");
            sb.append(format(ticket));
        }
        return sb.toString();
    }
}
