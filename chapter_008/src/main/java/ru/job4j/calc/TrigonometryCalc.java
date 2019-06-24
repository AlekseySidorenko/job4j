package ru.job4j.calc;

import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.Operands;

/**
 * Class TrigonometryCalc | Implement engineering calculator using InteractCalculator [#851]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.06.2019
 */
public class TrigonometryCalc extends InteractCalc {

    /** Constructor. */
    public TrigonometryCalc(Input input, Calculator calculator, TrigonometryOperations operations) {
        super(input, calculator, operations);
    }

    /**
     * Do calculation.
     */
    @Override
    protected double doCalculation() {
        double operand;
        String operation;
        operand = this.getOperandValue(input.ask("Type operand"));
        operation = this.getOperation(input.ask("Type operation"));
        if (!"false".equalsIgnoreCase(operation)) {
            this.operations.get(operation).accept(new Operands(operand, 0));
            System.out.println("Result: " + this.getResult());
        }
        return this.getResult();
    }

    /**
     * Main.
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        InteractCalc calc = new TrigonometryCalc(new ConsoleInput(), calculator, new TrigonometryOperations(calculator));
        calc.init();
    }
}