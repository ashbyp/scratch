package com.ashbyp.scratch.lottery;

import java.lang.reflect.Method;
import java.util.Random;

@SuppressWarnings("serial")
public class RandomNumberProvider extends Random {
    private Object inst;
    private Method nextInt;
    
    public RandomNumberProvider(String className, long seed) throws Exception {
        Class<?>[] longArg = { long.class };
        inst = Class.forName(className).getDeclaredConstructor(longArg).newInstance(seed);
        nextInt = inst.getClass().getMethod("nextInt", int.class);
    }
    
    @Override
    public int nextInt(int bound) {
        try {
            return (int) nextInt.invoke(inst, bound);
        } catch (Exception ex) {
            System.exit(-1);
            return -1;
        }   
    }
    
    public String getName() {
        return inst.getClass().getSimpleName();
    }
    
    public static void main(String[] args) {
        Random r = new Random();
        r.ints(1, 100).distinct().limit(10).forEach(System.out::println);
    }
}
