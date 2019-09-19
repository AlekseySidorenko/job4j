package ru.job4j.calc.operations;

public class Substract implements Operation {
    @Override
    public String getOperationName() {
        return "-";
    }

    @Override
    public Double calculate(Double first, Double second) {
        return first - second;
    }
}