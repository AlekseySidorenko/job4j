package ru.job4j.calc;

import ru.job4j.calc.input.ConsoleInput;
import ru.job4j.calc.input.Input;
import ru.job4j.calc.operations.Sin;

/**
 * Class CalculatorTrigonometry | Implement engineering calculator using InteractCalculator [#851]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.06.2019
 */
public class InteractCalcTrigonometry extends InteractCalc {

    /**
     * Constructor.
     * @param calculator Calculator type.
     * @param input Input type.
     */
    public InteractCalcTrigonometry(Input input, Calculator calculator) {
        super(input, calculator);
    }

    /**
     * Do calculation.
     */
    @Override
    protected void doCalculation() {
        double first;
        String operation;
        first = getter.getOperand(input.ask("Type operand"));
        operation = getter.getOperation(input.ask("Type operation"));
        if (!"false".equalsIgnoreCase(operation)) {
            this.calculator.calculate(first, 0, operation);
            System.out.println("Result: " + this.getResult());
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        InteractCalc calculator = new InteractCalcTrigonometry(new ConsoleInput(), new Calculator());
        calculator.addOperation(new Sin());
        calculator.init();
    }
}