package ru.job4j.calc.operations;

import static java.lang.Math.cos;

public class Cos implements Operation {
    @Override
    public String getOperationName() {
        return "cos";
    }

    @Override
    public Double calculate(Double first, Double second) {
        return cos(first);
    }
}