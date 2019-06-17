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

    private final Calculator calculator = new Calculator();

    /** Available operations in Calculator. */
    private final Map<String, Consumer<Operands>> operations = new Operations(this.calculator).getOperations();

    /** Input method. */
    private final Input input;

    /** Number of calculations while program works. */
    private int calculatesCount = 0;

    /** Constructor. */
    public InteractCalc(Input input) {
        this.input = input;
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
        } else if ("last".equalsIgnoreCase(input) & calculatesCount > 0) {
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
        double left;
        double right;
        String operation;
        boolean nextCalculation = true;

        while (nextCalculation) {
            if (calculatesCount > 0) {
                System.out.println("Type \"last\" to get previous operation result");
            }
            left = this.getOperandValue(input.ask("Type left operand"));
            right = this.getOperandValue(input.ask("Type right operand"));
            operation = this.getOperation(input.ask("Type operation"));
            if (!"false".equalsIgnoreCase(operation)) {
                this.operations.get(operation).accept(new Operands(left, right));
                System.out.println("Result: " + this.getResult());
                calculatesCount++;
            }
            if ("exit".equalsIgnoreCase(input.ask("Type \"exit\" to exit program or any key to next calculation"))) {
                nextCalculation = false;
            }
        }
    }

    /**
     * Main.
     */
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc(new ConsoleInput());
        calc.init();
    }
}