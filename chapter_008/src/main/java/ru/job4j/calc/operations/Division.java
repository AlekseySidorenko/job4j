package ru.job4j.calc.operations;

public class Division implements Operation {
    @Override
    public String getOperationName() {
        return "/";
    }

    @Override
    public Double calculate(Double first, Double second) {
        if (second == 0) {
            throw new ArithmeticException();
        }
        return first / second;
    }
}