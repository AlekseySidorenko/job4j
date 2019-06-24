package ru.job4j.calc;

import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.Operands;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Class InteractCalc | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.06.2019
 */
public class InteractCalc {

    private final Calculator calculator;

    /** Available operations in Calculator. */
    protected final Map<String, Consumer<Operands>> operations;

    /** Input method. */
    protected final Input input;

    /** Constructor. */
    public InteractCalc(Input input, Calculator calculator, Operations operations) {
        this.input = input;
        this.calculator = calculator;
        this.operations = operations.getOperations();
    }

    /** Getter. */
    public double getResult() {
        return calculator.getResult();
    }

    /**
     * Parse user input for operand value.
     * @param input Operand value in string format.
     * @return Operand value.
     */
    public double getOperandValue(final String input) {
        double operandValue = 0;
        if (validateInputOperand(input)) {
            operandValue = Double.parseDouble(input);
        } else if ("last".equalsIgnoreCase(input) & this.calculator.getResult() != 0) {
            operandValue = this.calculator.getResult();
        } else {
            System.out.println("Not valid number - setting input value to 0");
        }
        return operandValue;
    }

    /**
     * Validate operand value in user input.
     * @param input Operand value in string format.
     * @return true if input contains double value.
     */
    private boolean validateInputOperand(final String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Parse user input for operation type.
     * @param input Operation type.
     * @return Operation type.
     */
    public String getOperation(final String input) {
        String result;
        if (validateInputOperation(input)) {
            result = input;
        } else {
            System.out.println("Wrong operation type. Available operations: +, -, *, \\");
            result = "false";
        }
        return result;
    }

    /**
     * Validate operation type in user input.
     * @param input Operation type.
     * @return True if input contains valid operation.
     */
    private boolean validateInputOperation(final String input) {
        boolean result = false;
        if (this.operations.get(input) != null) {
            result = true;
        }
        return result;
    }

    /**
     * Init calculation program.
     */
    public void init() {
        double calculationResult = 0;
        boolean nextCalculation = true;
        while (nextCalculation) {
            if (!(calculationResult == 0)) {
                System.out.println("Type \"last\" to get previous operation result");
            }
            calculationResult = doCalculation();
            if ("exit".equalsIgnoreCase(input.ask("Type \"exit\" to exit program or any key to next calculation"))) {
                nextCalculation = false;
            }
        }
    }

    /**
     * Do calculation.
     */
    protected double doCalculation() {
        double left;
        double right;
        String operation;
        left = this.getOperandValue(input.ask("Type left operand"));
        right = this.getOperandValue(input.ask("Type right operand"));
        operation = this.getOperation(input.ask("Type operation"));
        if (!"false".equalsIgnoreCase(operation)) {
            this.operations.get(operation).accept(new Operands(left, right));
            System.out.println("Result: " + this.getResult());
        }
        return this.getResult();
    }


    /**
     * Main.
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        InteractCalc calc = new InteractCalc(new ConsoleInput(), calculator,  new Operations(calculator));
        calc.init();
    }
}