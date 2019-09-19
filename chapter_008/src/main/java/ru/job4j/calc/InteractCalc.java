package ru.job4j.calc;

import ru.job4j.calc.input.ConsoleInput;
import ru.job4j.calc.input.Input;
import ru.job4j.calc.input.InputGetter;
import ru.job4j.calc.operations.Add;
import ru.job4j.calc.operations.Operation;

/**
 * Class InteractCalc | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.06.2019
 */
public class InteractCalc {

    protected final Calculator calculator;
    protected final InputGetter getter;
    protected final Input input;

    /**
     * Constructor.
     * @param calculator Calculator type.
     * @param input Input type.
     */
    public InteractCalc(Input input, Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
        this.getter = new InputGetter(calculator, input);
    }

    /**
     * Getter.
     */
    public double getResult() {
        return this.calculator.getResult();
    }

    /**
     * Add operation to calculator.
     * @param operation Operation.
     */
    public void addOperation(Operation operation) {
        calculator.operations.put(operation.getOperationName(), operation);
    }

    /**
     * Do calculation.
     */
    protected void doCalculation() {
        double first;
        double second;
        String operation;
        first = getter.getOperand(input.ask("Type left operand"));
        second = getter.getOperand(input.ask("Type right operand"));
        operation = getter.getOperation(input.ask("Type operation"));
        if (!"false".equalsIgnoreCase(operation)) {
            this.calculator.calculate(first, second, operation);
            System.out.println("Result: " + this.getResult());
        }
    }

    /**
     * Init calculation program.
     */
    public void init() {
        boolean nextCalculation = true;
        while (nextCalculation) {
            if (calculator.isHasPreviousResult()) {
                System.out.println("Type \"last\" to get previous operation result");
            }
            doCalculation();
            if ("exit".equalsIgnoreCase(input.ask("Type \"exit\" to exit program or any key to next calculation"))) {
                nextCalculation = false;
            }
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        InteractCalc calculator = new InteractCalc(new ConsoleInput(), new Calculator());
        calculator.addOperation(new Add());
        calculator.init();
    }
}