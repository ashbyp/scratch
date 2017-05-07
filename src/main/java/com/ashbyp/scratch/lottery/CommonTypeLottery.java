package com.ashbyp.scratch.lottery;

public abstract class CommonTypeLottery<T> extends AbstractLottery<T, T> {
    public CommonTypeLottery(int highNumber, int numbersPerTicket, int numTickets, RandomNumberProvider rn) {
        super(highNumber, numbersPerTicket, numTickets, rn);
    }

    public abstract String format(T t);

    @Override
    public String formatTicket(T ticket) {
        return format(ticket);
    }

    @Override
    public String formatNumbers(T numbers) {
        return format(numbers);
    }
}
