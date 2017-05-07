package com.ashbyp.scratch.lottery;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class LotteryRunner {
    private static double run(ExecutorService executor, AbstractLottery<?, ?> lottery, int numThreads, int numTests,
            RandomNumberProvider rn, int ticketCost, int gamesPerYear) throws Exception {

        System.out.printf("Will run %d tests with %d threads\n", numTests, numThreads);

        long startTime = System.currentTimeMillis();
        long tries = lottery.runTests(executor, numTests, rn, ticketCost, gamesPerYear);
        long endTime = System.currentTimeMillis();
        long timeTakenSeconds = (endTime - startTime) / 1000;
        double timePerOneMillionTries = timeTakenSeconds / (tries / 1000000.0);

        System.out.printf("%s: %d thread(s) took %d seconds, time per 1 million tries %.3f\n\n", lottery.getName(),
                numThreads, timeTakenSeconds, timePerOneMillionTries);

        return timePerOneMillionTries;
    }

    public static void main(String[] args) throws Exception {
        LotteryRunnerParams params = new ObjectMapper(new YAMLFactory()).readValue(
                LotteryRunnerParams.class.getResourceAsStream("/lottery_runner_params.yaml"),
                LotteryRunnerParams.class);

        System.out.println(params);

        long ticketSeed = params.isSeededTickets() ? System.currentTimeMillis() % 123 : new Random().nextLong();
        long numberSeed = params.isSeededNumbers() ? System.currentTimeMillis() % 321 : new Random().nextLong();

        ExecutorService executor = Executors.newFixedThreadPool(params.getNumThreads());

        Map<String, Double> timeMap = new HashMap<>();

        for (String randomClass : params.getRandomClasses()) {
            for (String lotteryClass : params.getLotteries()) {
                RandomNumberProvider ticketRand = new RandomNumberProvider(randomClass, ticketSeed);
                RandomNumberProvider numberRand = new RandomNumberProvider(randomClass, numberSeed);

                Class<?>[] argTypes = { int.class, int.class, int.class, RandomNumberProvider.class };
                Constructor<?> c = Class.forName(lotteryClass).getDeclaredConstructor(argTypes);
                AbstractLottery<?, ?> l = (AbstractLottery<?, ?>) c.newInstance(params.getHighNum(), params.getNumPicks(),
                        params.getNumTickets(), ticketRand);
                timeMap.put(String.format("%-25s %-15s\t", l.getName(), ticketRand.getName()),
                        run(executor, l, params.getNumThreads(), params.getNumTests(), numberRand,
                                params.getTicketCost(), params.getGamesPerYear()));
            }
        }
        executor.shutdownNow();

        Utils.sortByValue(timeMap).forEach((k, v) -> System.out.printf("%-40s\t %.3f\n", k, v));
    }
}
