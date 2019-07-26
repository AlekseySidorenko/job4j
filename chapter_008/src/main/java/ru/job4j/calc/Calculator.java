package ru.job4j.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Class Calculator | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 17.06.2019
 */
public class Calculator {

    /** Key = operation type, value = operation implementation. */
    protected Map<String, Consumer<Operands>> operations = new HashMap<>();
    protected double result;
    private boolean hasPreviousResult = false;

    /**
     * Constructor.
     */
    public Calculator() {
        this.fillOperations();
    }

    /**
     * Fill map with available operations.
     */
    protected void fillOperations() {
        operations.put("+", this::add);
        operations.put("-", this::substract);
        operations.put("/", this::divide);
        operations.put("*", this::multiple);
    }

    /**
     * Getter.
     * @return Available operations.
     */
    public Map<String, Consumer<Operands>> getOperations() {
        return operations;
    }

    /**
     * Getter.
     * @return Calculation result.
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Getter.
     * @return True If there was at least one calculation result.
     */
    public boolean isHasPreviousResult() {
        return hasPreviousResult;
    }

    /**
     * Addition operation.
     * @param operands Two operands for calculate
     */
    public void add(Operands operands) {
        this.result = operands.getFirst() + operands.getSecond();
        hasPreviousResult = true;
    }

    /**
     * Subtraction operation.
     * @param operands Two operands for calculate
     */
    public void substract(Operands operands) {
        this.result = operands.getFirst() - operands.getSecond();
        hasPreviousResult = true;
    }

    /**
     * Division operation.
     * @param operands Two operands for calculate
     */
    public void divide(Operands operands) {
        this.result = operands.getFirst() / operands.getSecond();
        hasPreviousResult = true;
    }

    /**
     * Multiplication operation.
     * @param operands Two operands for calculate
     */
    public void multiple(Operands operands) {
        this.result = operands.getFirst() * operands.getSecond();
        hasPreviousResult = true;
    }
}