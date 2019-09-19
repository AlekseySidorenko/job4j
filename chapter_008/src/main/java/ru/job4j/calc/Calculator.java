package ru.job4j.calc;

import ru.job4j.calc.operations.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Calculator | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 17.06.2019
 */
public class Calculator {

    protected Map<String, Operation> operations = new HashMap<>();
    private double result;
    private boolean hasPreviousResult = false;

    /**
     * Getter.
     * @return True If there was at least one calculation result.
     */
    public boolean isHasPreviousResult() {
        return hasPreviousResult;
    }

    /**
     * Add operation.
     * @param operation Operation.
     */
    public void addOperation(Operation operation) {
        operations.put(operation.getOperationName(), operation);
    }

    /**
     * Getter.
     * @return Available operations.
     */
    public Map<String, Operation> getOperations() {
        return operations;
    }

    /**
     * Getter.
     * @return Calculation result.
     */
    public double getResult() {
        return result;
    }

    /**
     * Calculate.
     * @param first First operand.
     * @param second Second operand.
     * @param operationName Operation name.
     */
    public void calculate(double first, double second, String operationName) {
        result = operations.get(operationName).calculate(first, second);
    }
}