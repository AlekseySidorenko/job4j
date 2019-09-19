package ru.job4j.calc.operations;

public class Add implements Operation {
    @Override
    public String getOperationName() {
        return "+";
    }

    @Override
    public Double calculate(Double first, Double second) {
        return first + second;
    }
}