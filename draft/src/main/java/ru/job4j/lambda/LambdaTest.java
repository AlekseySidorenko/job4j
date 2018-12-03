package ru.job4j.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LambdaTest {

    void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index <= finish; index++) {
            media.accept(op.apply(value, index));
        }
    }

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.multiple(1, 10, 2,
                MathUtil::add,
                System.out::println
        );
    }
}