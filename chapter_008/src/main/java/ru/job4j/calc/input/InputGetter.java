package ru.job4j.calc.input;

import ru.job4j.calc.Calculator;

/**
 * Class InputGetter | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.07.2019
 */
public class InputGetter {

    protected final Input input;
    InputValidator validator;
    Calculator calculator;

    /**
     * Constructor.
     * @param calculator Calculator type.
     * @param input Input type.
     */
    public InputGetter(Calculator calculator, Input input) {
        this.calculator = calculator;
        this.input = input;
        this.validator = new InputValidator(calculator);
    }

    /**
     * Parse user input for operand value.
     * @param input Operand value in string format.
     * @return Operand value.
     */
    public double getOperand(final String input) {
        double operandValue = 0;
        if (validator.validateInputOperand(input)) {
            operandValue = Double.parseDouble(input);
        } else if ("last".equalsIgnoreCase(input) & this.calculator.getResult() != 0) {
            operandValue = this.calculator.getResult();
        } else {
            System.out.println("Not valid number - setting input value to 0");
        }
        return operandValue;
    }

    /**
     * Parse user input for operation type.
     * @param input Operation type.
     * @return Operation type.
     */
    public String getOperation(final String input) {
        String result;
        if (validator.validateInputOperation(input)) {
            result = input;
        } else {
            System.out.println("Wrong operation type");
            result = "false";
        }
        return result;
    }
}