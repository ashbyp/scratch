package com.ashbyp.scratch.lottery;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LotteryRunnerParams {
    @JsonProperty("high_num")
    private int highNum;
    @JsonProperty("num_picks")
    private int numPicks;
    @JsonProperty("num_tickets")
    private int numTickets;
    @JsonProperty("num_threads")
    private int numThreads;
    @JsonProperty("num_tests")
    private int numTests;
    @JsonProperty("seeded_tickets")
    private boolean seededTickets;
    @JsonProperty("seeded_numbers")
    private boolean seededNumbers;
    @JsonProperty("lotteries")
    private List<String> lotteries;
    @JsonProperty("random_classes")
    private List<String> randomClasses;
    @JsonProperty("ticket_cost")
    private int ticketCost;
    @JsonProperty("games_per_year")
    private int gamesPerYear;
    
    public int getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(int ticketCost) {
        this.ticketCost = ticketCost;
    }

    public int getGamesPerYear() {
        return gamesPerYear;
    }

    public void setGamesPerYear(int gamesPerYear) {
        this.gamesPerYear = gamesPerYear;
    }

    public List<String> getRandomClasses() {
        return randomClasses;
    }

    public void setRandomClasses(List<String> randomClasses) {
        this.randomClasses = randomClasses;
    }

    public List<String> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<String> lotteries) {
        this.lotteries = lotteries;
    }

    public int getHighNum() {
        return highNum;
    }

    public void setHighNum(int highNum) {
        this.highNum = highNum;
    }

    public int getNumPicks() {
        return numPicks;
    }

    public void setNumPicks(int numPicks) {
        this.numPicks = numPicks;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(int numThreads) {
        this.numThreads = numThreads;
    }

    public int getNumTests() {
        return numTests;
    }

    public void setNumTests(int numTests) {
        this.numTests = numTests;
    }

    public boolean isSeededTickets() {
        return seededTickets;
    }

    public void setSeededTickets(boolean seededTickets) {
        this.seededTickets = seededTickets;
    }

    public boolean isSeededNumbers() {
        return seededNumbers;
    }

    public void setSeededNumbers(boolean seededNumbers) {
        this.seededNumbers = seededNumbers;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).toString();
    }
}
