package ru.job4j.calc.operations;

import static java.lang.StrictMath.sin;

public class Sin implements Operation {
    @Override
    public String getOperationName() {
        return "sin";
    }

    @Override
    public Double calculate(Double first, Double second) {
        return sin(first);
    }
}